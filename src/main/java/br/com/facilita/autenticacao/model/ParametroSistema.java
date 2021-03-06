package br.com.facilita.autenticacao.model;

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
public class ParametroSistema {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idParametroSistema;

	@ManyToOne
	@JoinColumn(name = "CD_SISTEMA")
	private Sistema sistema;
	
	private String vlParametro;
	
	private String dsParametro;
}
