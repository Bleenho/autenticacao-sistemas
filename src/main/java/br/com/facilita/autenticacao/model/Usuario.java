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
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idUsuario;
	private String nmUsuario;
	private String cdUsuario;
	private String dsSenha;
	private String dsEmail;
	private String flStatus;
	private Date dtAlteracao;
	@ManyToOne
	@JoinColumn(name = "CD_PERFIL")
	private Perfil perfil;

}
