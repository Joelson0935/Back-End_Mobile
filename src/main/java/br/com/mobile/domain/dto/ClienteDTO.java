package br.com.mobile.domain.dto;

import java.io.Serializable;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.mobile.domain.enuns.TipoCliente;

public class ClienteDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	@Size(min = 5, max = 60, message = "Mínimo de 5 e máximo de 60 caracteres.")
	private String nome;
	@Email(message = "Email mal formado ou inexistente.")
	private String email;
	private String cpfOuCnpj;

	@Enumerated(EnumType.STRING)
	private TipoCliente tipoCliente;

	@NotBlank(message = "Este campo não pode ficar em branco.")
	private String logradouro;
	@NotBlank(message = "Este campo não pode ficar em branco.")
	private String numero;
	@NotBlank(message = "Este campo não pode ficar em branco.")
	private String complemento;
	@NotBlank(message = "Este campo não pode ficar em branco.")
	private String bairro;
	@NotBlank(message = "Este campo não pode ficar em branco.")
	@Size(min = 8, max = 9, message = "Este campo deve conter o mínimo de 8 caracteres.")
	private String cep;

	@Size(min = 11, message = "Deve conter o mínimo de 11 caracteres.")
	private String telefone1;
	private String telefone2;
	private String telefone3;

	private Long cidadeId;

	public ClienteDTO() {
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

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getTelefone1() {
		return telefone1;
	}

	public void setTelefone1(String telefone1) {
		this.telefone1 = telefone1;
	}

	public String getTelefone2() {
		return telefone2;
	}

	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}

	public String getTelefone3() {
		return telefone3;
	}

	public void setTelefone3(String telefone3) {
		this.telefone3 = telefone3;
	}

	public Long getCidadeId() {
		return cidadeId;
	}

	public void setCidadeId(Long cidadeId) {
		this.cidadeId = cidadeId;
	}

}
