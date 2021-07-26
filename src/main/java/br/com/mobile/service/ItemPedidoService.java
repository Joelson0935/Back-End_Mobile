package br.com.mobile.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mobile.domain.ItemPedido;
import br.com.mobile.repository.ItemPedidoRepository;
import br.com.mobile.service.exception.Mensagem;

@Service
public class ItemPedidoService {

	@Autowired
	private ItemPedidoRepository itemPedidoRepositorio;

	public List<ItemPedido> buscarTodos() {
		List<ItemPedido> itens = itemPedidoRepositorio.findAll();

		return itens;
	}

	public ItemPedido buscarPorId(Long id) {

		ItemPedido item = itemPedidoRepositorio.findById(id)
				.orElseThrow(() -> new Mensagem("Objeto não encontrado! Id: " + id));

		return item;
	}

	public ItemPedido adicionar(ItemPedido item) {
		item.getPedido().getId();
		item.getProduto().getId();
		ItemPedido itens = itemPedidoRepositorio.save(item);
		return itens;
	}

	public ItemPedido atualizar(Long id, ItemPedido item) {
		ItemPedido itens = itemPedidoRepositorio.findById(id)
				.orElseThrow(() -> new Mensagem("Objeto não encontrado! Id: " + id));
		item.getPedido().getId();
		item.getProduto().getId();
		item.setId(id);
		itens = itemPedidoRepositorio.save(item);
		return itens;
	}

	public void excluir(Long id) {
		itemPedidoRepositorio.deleteById(id);
	}

}
