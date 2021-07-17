package br.com.mobile.domain.enuns;

public enum EstadoPagamento {
	
	PENDENTE("pendente"), QUITADO("quitado"), CANCELADO("cancelado");
	
	private String nome;

	private EstadoPagamento(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}
	
	public static EstadoPagamento toEnum(String nome) {
		
		if(nome == null) {
			return null;
		}
		for(EstadoPagamento ep : EstadoPagamento.values()) {
			if(nome.equals(ep.getNome())) {
				return ep;
			}
		}
		throw new IllegalArgumentException("Cliente não encontrado " + nome);
	}
}
