package br.com.facilita.autenticacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.facilita.autenticacao.model.Sistema;

public interface SistemRepository extends JpaRepository<Sistema, Long>{

}
