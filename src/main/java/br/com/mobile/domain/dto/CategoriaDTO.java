package br.com.mobile.domain.dto;

import java.io.Serializable;

import br.com.mobile.domain.Categoria;

public class CategoriaDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String nome;

	public CategoriaDTO() {
		super();
	}

	public CategoriaDTO(Categoria cat) {
	this.id = cat.getId();
	this.nome = cat.getNome();
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
