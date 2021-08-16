package br.com.mobile.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mobile.domain.Categoria;
import br.com.mobile.domain.Produto;
import br.com.mobile.repository.ProdutoRepository;
import br.com.mobile.service.exception.Mensagem;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepositorio;

	public List<Produto> buscarTodos() {
		List<Produto> produtos = produtoRepositorio.findAll();

		return produtos;
	}

	public Produto buscarPorId(Long id) {

		Produto produtos = produtoRepositorio.findById(id)
				.orElseThrow(() -> new Mensagem("Objeto não encontrado! Id: " + id));

		return produtos;
	}

	public Produto adicionar(Produto produto) {
		var cat = new Categoria();
		produto.adicionarCategoria(cat);
		Produto produtos = produtoRepositorio.save(produto);
		return produtos;
	}

	public Produto atualizar(Long id, Produto produto) {
		Produto prod = produtoRepositorio.findById(id)
				.orElseThrow(() -> new Mensagem("Objeto não encontrado! Id: " + id));
		var cat = new Categoria();
		produto.adicionarCategoria(cat);

		produto.setId(id);

		prod = produtoRepositorio.save(produto);
		return prod;
	}

	public void excluir(Long id) {
		produtoRepositorio.deleteById(id);
	}

}
