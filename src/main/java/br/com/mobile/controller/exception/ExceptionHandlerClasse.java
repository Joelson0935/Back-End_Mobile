package br.com.mobile.controller.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.mobile.service.exception.Mensagem;

@ControllerAdvice
public class ExceptionHandlerClasse {
	
	@ExceptionHandler(Mensagem.class)
	public ResponseEntity<StandardError> objetoNaoEncontrado(Mensagem m, HttpServletRequest request){
		StandardError erro = new StandardError(HttpStatus.NOT_FOUND.value(), m.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}

}
