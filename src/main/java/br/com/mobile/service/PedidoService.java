package br.com.mobile.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mobile.domain.Pedido;
import br.com.mobile.repository.PedidoRepository;
import br.com.mobile.service.exception.Mensagem;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepositorio;

	public List<Pedido> buscarTodos() {
		List<Pedido> pedidos = pedidoRepositorio.findAll();

		return pedidos;
	}

	public Pedido buscarPorId(Long id) {

		Pedido pedido = pedidoRepositorio.findById(id)
				.orElseThrow(() -> new Mensagem("Objeto não encontrado! Id: " + id));

		return pedido;
	}

	public Pedido adicionar(Pedido pedido) {

		pedido.getEnderecoEntrega().getId();
		pedido.getCliente().getId();
		Pedido ped = pedidoRepositorio.save(pedido);
		return ped;
	}

	public Pedido atualizar(Long id, Pedido pedido) {
		Pedido ped = pedidoRepositorio.findById(id).orElseThrow(() -> new Mensagem("Objeto não encontrado! Id: " + id));

		pedido.setId(id);
		pedido.getEnderecoEntrega().getId();
		pedido.getCliente().getId();
		ped = pedidoRepositorio.save(pedido);
		return ped;
	}

	public void excluir(Long id) {
		pedidoRepositorio.deleteById(id);
	}

}
