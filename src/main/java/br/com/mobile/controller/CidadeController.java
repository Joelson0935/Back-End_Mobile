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

import br.com.mobile.domain.Cidade;
import br.com.mobile.service.CidadeService;

@RestController
@RequestMapping("/Cidades")
public class CidadeController {

	@Autowired
	private CidadeService cidadeServico;

	@GetMapping
	public List<Cidade> buscarTodos() {
		return cidadeServico.buscarTodos();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Cidade> buscarPorId(@PathVariable Long id) {
		Cidade cidade = cidadeServico.buscarPorId(id);
		if (cidade != null) {
			return ResponseEntity.ok(cidade);
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<Cidade> adicionar(@RequestBody Cidade cidade) {
		cidade = cidadeServico.adicionar(cidade);
		return new ResponseEntity<Cidade>(cidade, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Cidade> atualizar(@PathVariable Long id, @RequestBody Cidade cidade) {
		Cidade cid = cidadeServico.buscarPorId(id);
		if (cid == null || !cid.equals(cidade)) {
			return ResponseEntity.notFound().build();
		}
		cidade.setId(id);
		cid = cidadeServico.adicionar(cidade);
		return new ResponseEntity<Cidade>(cid,  HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {

		if (buscarPorId(id) == null) {
			return ResponseEntity.notFound().build();
		}
		cidadeServico.excluir(id);
		return ResponseEntity.noContent().build();
	}
}
