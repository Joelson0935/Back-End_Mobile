package br.com.mobile.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mobile.domain.Cidade;
import br.com.mobile.repository.CidadeRepository;
import br.com.mobile.service.exception.Mensagem;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository cidadeRepositorio;

	public List<Cidade> buscarTodos() {
		List<Cidade> cidades = cidadeRepositorio.findAll();

		return cidades;
	}

	public Cidade buscarPorId(Long id) {

		Cidade cidade = cidadeRepositorio.findById(id)
				.orElseThrow(() -> new Mensagem("Objeto não encontrado! Id: " + id));

		return cidade;
	}

	public Cidade adicionar(Cidade cidade) {

		cidade.getEstado().getId();
		Cidade cid = cidadeRepositorio.save(cidade);
		return cid;
	}

	public Cidade atualizar(Long id, Cidade cidade) {
		Cidade cid = cidadeRepositorio.findById(id).orElseThrow(() -> new Mensagem("Objeto não encontrado! Id: " + id));

		cidade.setId(id);
		cidade.getEstado().getId();
		cid = cidadeRepositorio.save(cidade);
		return cid;
	}

	public void excluir(Long id) {
		cidadeRepositorio.deleteById(id);
	}

}
