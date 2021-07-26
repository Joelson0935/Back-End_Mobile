package br.com.mobile.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mobile.domain.Pagamento;
import br.com.mobile.repository.PagamentoRepository;
import br.com.mobile.service.exception.Mensagem;

@Service
public class PagamentoService {

	@Autowired
	private PagamentoRepository pagamentoRepositorio;

	public List<Pagamento> buscarTodos() {
		List<Pagamento> pagamentos = pagamentoRepositorio.findAll();
		return pagamentos;
	}

	public Pagamento buscarPorId(Long id) {

		Pagamento pag = pagamentoRepositorio.findById(id)
				.orElseThrow(() -> new Mensagem("Objeto não encontrado! Id: " + id));

		return pag;
	}

	public Pagamento adicionar(Pagamento pagamento) {
	pagamento.setId(pagamento.getPedido().getId());
		Pagamento pag = pagamentoRepositorio.save(pagamento);
		return pag;
	}

	public Pagamento atualizar(Long id, Pagamento pagamento) {
		pagamento.getPedido().getId();
		Pagamento pag = pagamentoRepositorio.findById(id)
				.orElseThrow(() -> new Mensagem("Objeto não encontrado! Id: " + id));

		pagamento.setId(pag.getPedido().getId());
		pag = pagamentoRepositorio.save(pagamento);
		return pag;
	}

	public void excluir(Long id) {
		pagamentoRepositorio.deleteById(id);
	}

}
