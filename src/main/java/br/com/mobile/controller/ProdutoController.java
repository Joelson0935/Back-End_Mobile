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

import br.com.mobile.domain.Produto;
import br.com.mobile.service.ProdutoService;

@RestController
@RequestMapping("/Produtos")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoServico;

	@GetMapping
	public List<Produto> buscar() {
		return produtoServico.buscarTodos();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Produto> buscarPorId(@PathVariable Long id) {
		Produto prod = produtoServico.buscarPorId(id);
		if (prod != null) {
			return ResponseEntity.ok(prod);
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<Produto> adicionar(@RequestBody Produto produto) {
		produto = produtoServico.adicionar(produto);
		return new ResponseEntity<Produto>(produto, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Produto> atualizar(@PathVariable Long id, @RequestBody Produto produto) {
		Produto prod = produtoServico.buscarPorId(id);
		if (prod == null || !prod.equals(produto)) {
			return ResponseEntity.notFound().build();
		}
		produto.setId(id);
		prod = produtoServico.adicionar(produto);
		return new ResponseEntity<Produto>(prod, HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {

		if (buscarPorId(id) == null) {
			return ResponseEntity.notFound().build();
		}
		produtoServico.excluir(id);
		return ResponseEntity.noContent().build();
	}
}
