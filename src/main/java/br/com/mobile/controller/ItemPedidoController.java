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

import br.com.mobile.domain.ItemPedido;
import br.com.mobile.service.ItemPedidoService;

@RestController
@RequestMapping("/Itens")
public class ItemPedidoController {

	@Autowired
	private ItemPedidoService itemPedidoServico;

	@GetMapping
	public ResponseEntity<List<ItemPedido>> buscarTodos() {
		List<ItemPedido> itens = itemPedidoServico.buscarTodos();
		return ResponseEntity.ok().body(itens);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ItemPedido> buscarPorId(@PathVariable Long id) {

		ItemPedido item = itemPedidoServico.buscarPorId(id);
		if (item != null) {
			return ResponseEntity.ok(item);
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<ItemPedido> adicionar(@RequestBody ItemPedido item) {
		item = itemPedidoServico.adicionar(item);
		return new ResponseEntity<ItemPedido>(item, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ItemPedido> atualizar(@PathVariable Long id, @RequestBody ItemPedido item) {
		ItemPedido itens = itemPedidoServico.buscarPorId(id);
		if (itens == null || !itens.equals(item)) {
			return ResponseEntity.notFound().build();
		}

		item.setId(id);
		item = itemPedidoServico.adicionar(item);
		return new ResponseEntity<ItemPedido>(item, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {

		if (buscarPorId(id) == null) {
			return ResponseEntity.notFound().build();
		}
		itemPedidoServico.excluir(id);
		return ResponseEntity.noContent().build();
	}
}
