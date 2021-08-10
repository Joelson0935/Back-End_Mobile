package br.com.mobile.controller.exception;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TestError implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer status;

	private String mensagem;

	private Long timeStamp;

	private List<Campos> Erros = new ArrayList<>();

	public TestError() {
		super();
	}

	public TestError(Integer status, String mensagem, Long timeStamp) {
		super();
		this.status = status;
		this.mensagem = mensagem;
		this.timeStamp = timeStamp;
	}

	public class Campos {

		private String fieldName;

		private String message;

		public Campos() {
			super();
		}

		public Campos(String fieldName, String message) {
			super();
			this.fieldName = fieldName;
			this.message = message;
		}

		public String getFieldName() {
			return fieldName;
		}

		public void setFieldName(String fieldName) {
			this.fieldName = fieldName;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
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

	public List<Campos> getErros() {
		return Erros;
	}

	public void setErros(String fieldName, String mensagem) {
		Erros.add(new Campos(fieldName, mensagem));
	}

}
