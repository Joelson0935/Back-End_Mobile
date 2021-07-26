package br.com.mobile.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mobile.domain.Cliente;
import br.com.mobile.repository.ClienteRepository;
import br.com.mobile.service.exception.Mensagem;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepositorio;

	public List<Cliente> buscarTodos() {
		List<Cliente> clientes = clienteRepositorio.findAll();

		return clientes;
	}

	public Cliente buscarPorId(Long id) {

		Cliente cliente = clienteRepositorio.findById(id)
				.orElseThrow(() -> new Mensagem("Objeto não encontrado! Id: " + id));

		return cliente;
	}

	public Cliente adicionar(Cliente cliente) {
		Cliente cli = clienteRepositorio.save(cliente);
		return cli;
	}

	public Cliente atualizar(Long id, Cliente cliente) {
		Cliente cli = clienteRepositorio.findById(id)
				.orElseThrow(() -> new Mensagem("Objeto não encontrado! Id: " + id));

		cliente.setId(id);
		cli = clienteRepositorio.save(cliente);
		return cli;
	}

	public void excluir(Long id) {
		clienteRepositorio.deleteById(id);
	}

}
