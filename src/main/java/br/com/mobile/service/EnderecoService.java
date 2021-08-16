package br.com.mobile.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mobile.domain.Endereco;
import br.com.mobile.repository.EnderecoRepository;
import br.com.mobile.service.exception.Mensagem;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository enderecoRepositorio;

	public List<Endereco> buscarTodos() {
		List<Endereco> enderecos = enderecoRepositorio.findAll();

		return enderecos;
	}

	public Endereco buscarPorId(Long id) {

		Endereco endereco = enderecoRepositorio.findById(id)
				.orElseThrow(() -> new Mensagem("Objeto não encontrado! Id: " + id));

		return endereco;
	}

	public Endereco adicionar(Endereco endereco) {

		endereco.getCliente().getId();
		endereco.getCidade().getId();
		endereco.getCidade().getNome();
		Endereco end = enderecoRepositorio.save(endereco);
		return end;
	}

	public Endereco atualizar(Long id, Endereco endereco) {
		Endereco end = enderecoRepositorio.findById(id).orElseThrow(() -> new Mensagem("Objeto não encontrado! Id: " + id));

		endereco.setId(id);
		endereco.getCliente().getId();
		endereco.getCidade().getId();
		endereco.getCidade().getNome();
		end = enderecoRepositorio.save(endereco);
		return end;
	}

	public void excluir(Long id) {
		enderecoRepositorio.deleteById(id);
	}

}
