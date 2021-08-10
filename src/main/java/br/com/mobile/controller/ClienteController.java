package br.com.mobile.controller;

import java.util.List;

import javax.validation.Valid;

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
import br.com.mobile.domain.dto.ClienteDTO;
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

	@PostMapping // POST USANDO O PADRÃO DTO
	public ResponseEntity<Cliente> adicionarDto(@Valid @RequestBody ClienteDTO clienteDto) {
		Cliente cliente = clienteServico.adicionarDto(clienteDto);
		cliente = clienteServico.adicionar(cliente);
		return new ResponseEntity<Cliente>(cliente, HttpStatus.CREATED);
	}

//	@PostMapping
//	public ResponseEntity<Cliente> adicionar(@Valid @RequestBody Cliente cliente) {
//		cliente = clienteServico.adicionar(cliente);
//		return new ResponseEntity<Cliente>(cliente, HttpStatus.CREATED);
//	}

	@PutMapping("/{id}")
	public ResponseEntity<Cliente> atualizar(@Valid @PathVariable Long id, @RequestBody ClienteDTO clienteDto) {
		Cliente  cli = clienteServico.buscarPorId(id);
		 cli = clienteServico.adicionarDto(clienteDto);
		if (cli == null) {
			return ResponseEntity.notFound().build();
		}
		cli.setId(id);
	   cli = clienteServico.adicionar(cli);
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
