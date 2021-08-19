package br.com.mobile.domain.dto;

import java.io.Serializable;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.mobile.domain.Cliente;
import br.com.mobile.domain.enuns.TipoCliente;

public class NovoClienteDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	@NotNull
	@Size(min = 5, max = 60, message = "Mínimo de 5 e máximo de 60 caracteres.")
	private String nome;
	@Email(message = "Email mal formado ou inexistente.")
	private String email;
	private String cpfOuCnpj;

	@Enumerated(EnumType.STRING)
	private TipoCliente tipoCliente;

	public NovoClienteDTO() {

	}

	public NovoClienteDTO(Cliente cliente) {
		this.id = cliente.getId();
		this.nome = cliente.getNome();
		this.email = cliente.getEmail();
		this.cpfOuCnpj = cliente.getCpfOuCnpj();
		this.tipoCliente = cliente.getTipoCliente();
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpfOuCnpj() {
		return cpfOuCnpj;
	}

	public void setCpfOuCnpj(String cpfOuCnpj) {
		this.cpfOuCnpj = cpfOuCnpj;
	}

	public TipoCliente getTipoCliente() {
		return tipoCliente;
	}

	public void setTipoCliente(TipoCliente tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

}
