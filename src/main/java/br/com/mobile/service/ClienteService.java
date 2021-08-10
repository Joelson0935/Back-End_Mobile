package br.com.mobile.service;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.mobile.domain.Cidade;
import br.com.mobile.domain.Cliente;
import br.com.mobile.domain.Endereco;
import br.com.mobile.domain.dto.ClienteDTO;
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

	@Transactional
	public Cliente adicionarDto(ClienteDTO clienteDto) {
		Cliente cliente = new Cliente(null, clienteDto.getNome(), clienteDto.getEmail(), clienteDto.getCpfOuCnpj(),
				clienteDto.getTipoCliente());
		Cidade cidade = new Cidade(clienteDto.getCidadeId(), null);
		Endereco endereco = new Endereco(null, clienteDto.getLogradouro(), clienteDto.getNumero(),
				clienteDto.getComplemento(), clienteDto.getBairro(), clienteDto.getCep(), cliente, cidade);
		cliente.getEnderecos().add(endereco);
		cliente.getTelefones().add(clienteDto.getTelefone());
		if (clienteDto.getTelefone1() != null) {
			cliente.getTelefones().add(clienteDto.getTelefone1());
		}
		if (clienteDto.getTelefone2() != null) {
			cliente.getTelefones().add(clienteDto.getTelefone2());
		}
		return cliente;
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
