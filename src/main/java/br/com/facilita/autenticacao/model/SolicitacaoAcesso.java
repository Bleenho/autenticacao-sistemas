package br.com.facilita.autenticacao.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SolicitacaoAcesso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idSolicitacaoACesso;
	
	private String dsToken;
	
	private Date dtToken;
	
	private Date dtTokenExpired;
	
	private Long vlMinutoValida;
	
	@ManyToOne
	@JoinColumn(name = "CD_SISTEMA")
	private Sistema sistema;
	
	@ManyToOne
	@JoinColumn(name = "CD_USUARIO")
	private Usuario usuario;
}
