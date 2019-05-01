package br.com.facilita.autenticacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.facilita.autenticacao.model.Perfil;

public interface PerfilRepository extends JpaRepository<Perfil, Long>{

}
