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

import br.com.mobile.domain.Cliente;
import br.com.mobile.service.ClienteService;

@RestController
@RequestMapping("/Clientes")
public class ClienteController {

	@Autowired
	private ClienteService clienteServico;

	@GetMapping
	public List<Cliente> buscarTodos() {
		return clienteServico.buscarTodos();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Cliente> buscarPorId(@PathVariable Long id) {
		Cliente cliente = clienteServico.buscarPorId(id);
		if (cliente != null) {
			return ResponseEntity.ok(cliente);
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<Cliente> adicionar(@RequestBody Cliente cliente) {
		cliente = clienteServico.adicionar(cliente);
		return new ResponseEntity<Cliente>(cliente, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Cliente> atualizar(@PathVariable Long id, @RequestBody Cliente cliente) {
		Cliente cli = clienteServico.buscarPorId(id);
		if (cli == null || !cli.equals(cliente)) {
			return ResponseEntity.notFound().build();
		}
		cliente.setId(id);
		cli = clienteServico.adicionar(cliente);
		return new ResponseEntity<Cliente>(cli, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {

		if (buscarPorId(id) == null) {
			return ResponseEntity.notFound().build();
		}
		clienteServico.excluir(id);
		return ResponseEntity.noContent().build();
	}
}
