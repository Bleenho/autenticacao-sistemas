package br.com.facilita.autenticacao.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import br.com.facilita.autenticacao.model.Perfil;
import br.com.facilita.autenticacao.model.Usuario;

@Repository
public class LoginRepository {
	
	private static final String SQL_LOGIN = "SELECT U.id_usuario, U.CD_USUARIO, U.DS_EMAIL, U.DT_ALTERACAO, U.FL_STATUS, U.NM_USUARIO, P.ID_PERFIL, P.DS_PERFIL " + 
			"	FROM autenticacao.modulo M " + 
			"	JOIN autenticacao.modulo_perfil MP 	ON (MP.CD_MODULO = M.ID_MODULO) " + 
			"	join autenticacao.perfil P 			ON (P.id_Perfil = MP.cd_perfil) " +  
			"	JOIN autenticacao.usuario U 		ON (U.CD_PERFIL = P.Id_PERFIL) " + 
			"		WHERE UPPER(U.cd_usuario) = UPPER(?) " + 
			"		AND U.DS_SENHA = ? " +
			"		AND M.CD_SISTEMA = ? LIMIT 1";

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public LoginRepository(@Qualifier("default") JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public Usuario realizarLogin(String userName, String passwordCript, Long idSistema) throws EmptyResultDataAccessException{
        return jdbcTemplate.queryForObject(SQL_LOGIN, userMapper, userName, passwordCript, idSistema);
    }
	
    private static final RowMapper<Usuario> userMapper = new RowMapper<Usuario>() {
        public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Usuario.builder()
                    .idUsuario(rs.getLong("id_usuario"))
                    .cdUsuario(rs.getString("CD_USUARIO"))
                    .dsEmail(rs.getString("DS_EMAIL"))
                    .dtAlteracao(rs.getDate("DT_ALTERACAO"))
                    .flStatus(rs.getString("FL_STATUS"))
                    .nmUsuario(rs.getString("NM_USUARIO"))
                    .perfil(Perfil.builder().idPerfil(rs.getLong("ID_PERFIL")).dsPerfil(rs.getString("DS_PERFIL")).build())
                    .build();
        }
    };
}
