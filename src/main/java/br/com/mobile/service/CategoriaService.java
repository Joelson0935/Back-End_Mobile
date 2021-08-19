package br.com.mobile.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.mobile.domain.Categoria;
import br.com.mobile.domain.dto.CategoriaDTO;
import br.com.mobile.repository.CategoriaRepository;
import br.com.mobile.service.exception.Mensagem;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepositorio;

	public List<Categoria> buscarTodos() {
		List<Categoria> categorias = categoriaRepositorio.findAll();
		return categorias;
	}

	public Categoria buscarPorId(Long id) {

		Categoria cat = categoriaRepositorio.findById(id)
				.orElseThrow(() -> new Mensagem("Objeto não encontrado! Id: " + id));

		return cat;
	}

	public Page<Categoria> buscarPorPagina(Pageable pageable) {
		Page<Categoria> categorias = categoriaRepositorio.findAll(pageable);
		return categorias;
	}

	public Categoria adicionar(Categoria categoria) {

		Categoria cat = categoriaRepositorio.save(categoria);
		return cat;
	}

	public Categoria atualizar(Long id, Categoria categoria) {
		Categoria cat = categoriaRepositorio.findById(id)
				.orElseThrow(() -> new Mensagem("Objeto não encontrado! Id: " + id));

		categoria.setId(id);
		cat = categoriaRepositorio.save(categoria);
		return cat;
	}

	public void excluir(Long id) {
		categoriaRepositorio.deleteById(id);
	}
	// Método auxiliar de conversão para dto.
	public Categoria paraDto(CategoriaDTO categoriaDto) {
		return new Categoria(categoriaDto.getId(), categoriaDto.getNome());
	}
}
