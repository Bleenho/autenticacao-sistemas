package br.com.facilita.autenticacao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.facilita.autenticacao.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	List<Usuario> findByDsSenhaAndCdUsuario(String cript, String userName);

}
