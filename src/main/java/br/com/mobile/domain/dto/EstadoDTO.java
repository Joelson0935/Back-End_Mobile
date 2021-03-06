package br.com.mobile.domain.dto;

import java.io.Serializable;

import br.com.mobile.domain.Estado;

public class EstadoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String nome;

	public EstadoDTO() {
		super();
	}

	public EstadoDTO(Estado est) {
		id = est.getId();
		nome = est.getNome();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
