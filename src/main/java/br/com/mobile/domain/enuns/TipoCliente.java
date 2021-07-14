package br.com.mobile.domain.enuns;

public enum TipoCliente {
	
	PESSOAFISICA("PF"), PESSOAJURIDICA("PJ");
	
	private String nome;

	private TipoCliente(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}
	
	public static TipoCliente toEnum(String nome) {
		
		if(nome == null) {
			return null;
		}
		for(TipoCliente tc : TipoCliente.values()) {
			if(nome.equals(tc.getNome())) {
				return tc;
			}
		}
		throw new IllegalArgumentException("Cliente não encontrado " + nome);
	}
}
