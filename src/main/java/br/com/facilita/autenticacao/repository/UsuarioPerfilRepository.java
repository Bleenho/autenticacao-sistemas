package br.com.facilita.autenticacao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.facilita.autenticacao.model.Usuario;
import br.com.facilita.autenticacao.model.UsuarioPerfil;

@Repository
public interface UsuarioPerfilRepository extends JpaRepository<UsuarioPerfil, Long>{

	List<UsuarioPerfil> findByUsuario(Usuario user);

}
