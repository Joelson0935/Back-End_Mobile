package br.com.mobile.controller.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.mobile.service.exception.Mensagem;

@ControllerAdvice
public class ExceptionHandlerClasse {

	@ExceptionHandler(Mensagem.class)
	public ResponseEntity<StandardError> objetoNaoEncontrado(Mensagem m, HttpServletRequest request) {
		StandardError erro = new StandardError(HttpStatus.NOT_FOUND.value(), m.getMessage(),
				System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<StandardError> integridadeDados(DataIntegrityViolationException e,
			HttpServletRequest request) {
		StandardError erro = new StandardError(HttpStatus.BAD_REQUEST.value(), "Não pode excluir uma categoria que possua produtos",
				System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}

//	@ExceptionHandler(MethodArgumentNotValidException.class)
//	public ResponseEntity<StandardError> validacao(MethodArgumentNotValidException e, HttpServletRequest request) {
//		ValidationError erro = new ValidationError(HttpStatus.BAD_REQUEST.value(), "Erro de validação.",
//				System.currentTimeMillis());
//		for (FieldError f : e.getBindingResult().getFieldErrors()) {
//			erro.addErros(f.getField(), f.getDefaultMessage());
//		}
//		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
//	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<TestError> validacaoTest(MethodArgumentNotValidException e, HttpServletRequest request) {
		TestError erroTest = new TestError(HttpStatus.BAD_REQUEST.value(), "Erro de validação.",
				System.currentTimeMillis());
		for (FieldError f : e.getBindingResult().getFieldErrors()) {
			erroTest.setErros(f.getField(), f.getDefaultMessage());
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroTest);
	}

}
