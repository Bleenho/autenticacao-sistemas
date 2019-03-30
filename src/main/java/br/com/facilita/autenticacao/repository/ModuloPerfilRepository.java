package br.com.facilita.autenticacao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.facilita.autenticacao.model.ModuloPerfil;
import br.com.facilita.autenticacao.model.Perfil;

@Repository
public interface ModuloPerfilRepository extends JpaRepository<ModuloPerfil, Long>{
	
	public List<ModuloPerfil> findDistinctModuloPerfilByPerfil(Perfil perfil);

}
