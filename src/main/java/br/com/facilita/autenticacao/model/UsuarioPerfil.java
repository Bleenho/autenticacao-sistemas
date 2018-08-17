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
public class UsuarioPerfil {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idUsuarioPerfil;
	
	@ManyToOne
	@JoinColumn(name = "CD_USUARIO")
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name = "CD_PERFIL")
	private Perfil perfil;
}
