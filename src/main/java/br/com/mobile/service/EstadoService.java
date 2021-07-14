package br.com.mobile.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mobile.domain.Estado;
import br.com.mobile.repository.EstadoRepository;
import br.com.mobile.service.exception.Mensagem;

@Service
public class EstadoService {

	@Autowired
	private EstadoRepository estadoRepositorio;

	public List<Estado> buscarTodos() {
		List<Estado> estados = estadoRepositorio.findAll();

		return estados;
	}

	public Estado buscarPorId(Long id) {

		Estado est = estadoRepositorio.findById(id).orElseThrow(() -> new Mensagem("Objeto não encontrado! Id: " + id));

		return est;
	}

	public Estado adicionar(Estado estado) {

		Estado est = estadoRepositorio.save(estado);
		return est;
	}

	public Estado atualizar(Long id, Estado estado) {
		Estado est = estadoRepositorio.findById(id).orElseThrow(() -> new Mensagem("Objeto não encontrado! Id: " + id));

		estado.setId(id);
		est = estadoRepositorio.save(estado);
		return est;
	}

	public void excluir(Long id) {
		estadoRepositorio.deleteById(id);
	}

}
