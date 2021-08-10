package br.com.mobile.controller.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {

	private static final long serialVersionUID = 1L;

	private List<FieldMessage> erros = new ArrayList<>();

	public ValidationError() {
		super();
	}

	public ValidationError(Integer status, String mensagem, Long timeStamp) {
		super(status, mensagem, timeStamp);
	}

	public List<FieldMessage> getErros() {
		return erros;
	}
	
	public void addErros(String fieldName, String mensagem) {
		erros.add(new FieldMessage(fieldName, mensagem));
	}

}
