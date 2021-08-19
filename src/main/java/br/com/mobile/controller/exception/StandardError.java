package br.com.mobile.controller.exception;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StandardError implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer status;

	private String mensagem;

	private Long timeStamp;

	private List<Campo> campos = new ArrayList<>();

	public StandardError() {
	}

	public StandardError(Integer status, String mensagem, Long timeStamp) {
		super();
		this.status = status;
		this.mensagem = mensagem;
		this.timeStamp = timeStamp;
	}

	public class Campo {

		private String nome;
		private String mensagem;

		public Campo(String nome, String mensagem) {
			super();
			this.nome = nome;
			this.mensagem = mensagem;
		}

		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

		public String getMensagem() {
			return mensagem;
		}

		public void setMensagem(String mensagem) {
			this.mensagem = mensagem;
		}

	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
	}

	public List<Campo> getCampos() {
		return campos;
	}

	public void addCampos(String nome, String mensagem) {
		campos.add(new Campo(nome, mensagem));
	}

}
