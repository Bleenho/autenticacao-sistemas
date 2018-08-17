package br.com.facilita.autenticacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.facilita.autenticacao.model.SolicitacaoAcesso;

@Repository
public interface SolicitacaoAcessoRepository extends JpaRepository<SolicitacaoAcesso, Long>{

}
