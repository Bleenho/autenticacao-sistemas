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
public class ModuloPerfil {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idModuloPerfil;
	
	@ManyToOne
	@JoinColumn(name = "CD_MODULO")
	private Modulo modulo;
	
	@ManyToOne
	@JoinColumn(name = "CD_PERFIL")
	private Perfil perfil;
}
