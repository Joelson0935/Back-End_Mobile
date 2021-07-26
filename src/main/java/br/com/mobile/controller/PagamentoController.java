package br.com.mobile.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mobile.domain.Pagamento;
import br.com.mobile.service.PagamentoService;

@RestController
@RequestMapping("/Pagamentos")
public class PagamentoController {

	@Autowired
	private PagamentoService pagamentoServico;

	@GetMapping
	public ResponseEntity<List<Pagamento>> buscarTodos() {
		List<Pagamento> pagamentos = pagamentoServico.buscarTodos();
		return ResponseEntity.ok().body(pagamentos);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Pagamento> buscarPorId(@PathVariable Long id) {
		Pagamento pagamento = pagamentoServico.buscarPorId(id);
		if (pagamento != null) {
			return ResponseEntity.ok(pagamento);
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<Pagamento> adicionar(@RequestBody Pagamento pagamento) {
		pagamento = pagamentoServico.adicionar(pagamento);
		return new ResponseEntity<Pagamento>(pagamento, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Pagamento> atualizar(@PathVariable Long id, @RequestBody Pagamento pagamento) {
		Pagamento pag = pagamentoServico.buscarPorId(id);
		if (pag == null || !pag.equals(pagamento)) {
			return ResponseEntity.notFound().build();
		}
		pagamento.setId(id);
		pagamento = pagamentoServico.adicionar(pagamento);
		return new ResponseEntity<Pagamento>(pagamento,  HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {

		if (buscarPorId(id) == null) {
			return ResponseEntity.notFound().build();
		}
		pagamentoServico.excluir(id);
		return ResponseEntity.noContent().build();
	}
}
