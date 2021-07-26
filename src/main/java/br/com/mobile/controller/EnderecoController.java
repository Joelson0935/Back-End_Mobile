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

import br.com.mobile.domain.Endereco;
import br.com.mobile.service.EnderecoService;

@RestController
@RequestMapping("/Enderecos")
public class EnderecoController {

	@Autowired
	private EnderecoService enderecoServico;

	@GetMapping
	public ResponseEntity<List<Endereco>> buscarTodos() {
		List<Endereco> enderecos = enderecoServico.buscarTodos();
		return ResponseEntity.ok().body(enderecos);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Endereco> buscarPorId(@PathVariable Long id) {
		Endereco endereco = enderecoServico.buscarPorId(id);
		if (endereco != null) {
			return ResponseEntity.ok(endereco);
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<Endereco> adicionar(@RequestBody Endereco endereco) {
		endereco = enderecoServico.adicionar(endereco);
		return new ResponseEntity<Endereco>(endereco, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Endereco> atualizar(@PathVariable Long id, @RequestBody Endereco endereco) {
		Endereco end = enderecoServico.buscarPorId(id);
		if (end == null || !end.equals(endereco)) {
			return ResponseEntity.notFound().build();
		}
		endereco.setId(id);
		endereco = enderecoServico.adicionar(endereco);
		return new ResponseEntity<Endereco>(endereco, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {

		if (buscarPorId(id) == null) {
			return ResponseEntity.notFound().build();
		}
		enderecoServico.excluir(id);
		return ResponseEntity.noContent().build();
	}
}
