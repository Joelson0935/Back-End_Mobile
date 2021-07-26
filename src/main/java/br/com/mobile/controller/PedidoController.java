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

import br.com.mobile.domain.Pedido;
import br.com.mobile.service.PedidoService;

@RestController
@RequestMapping("/Pedidos")
public class PedidoController {

	@Autowired
	private PedidoService pedidoServico;

	@GetMapping
	public ResponseEntity<List<Pedido>> buscarTodos() {
		List<Pedido> pedidos = pedidoServico.buscarTodos();
		return ResponseEntity.ok().body(pedidos);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Pedido> buscarPorId(@PathVariable Long id) {
		Pedido pedido = pedidoServico.buscarPorId(id);
		if (pedido != null) {
			return ResponseEntity.ok(pedido);
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<Pedido> adicionar(@RequestBody Pedido pedido) {
		pedido = pedidoServico.adicionar(pedido);
		return new ResponseEntity<Pedido>(pedido, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Pedido> atualizar(@PathVariable Long id, @RequestBody Pedido pedido) {
		Pedido ped = pedidoServico.buscarPorId(id);
		if (ped == null || !ped.equals(pedido)) {
			return ResponseEntity.notFound().build();
		}
		pedido.setId(id);
		pedido = pedidoServico.adicionar(pedido);
		return new ResponseEntity<Pedido>(pedido, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {

		if (buscarPorId(id) == null) {
			return ResponseEntity.notFound().build();
		}
		pedidoServico.excluir(id);
		return ResponseEntity.noContent().build();
	}
}
