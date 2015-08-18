package br.com.chatreact.statuses;

public enum StatusAtivacao {

	ATIVO(1, "Ativo"),
	PENDENTE(2, "Pendente");

	private int id;
	
	private String descricao;
	
	private StatusAtivacao(int id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}
	
	public int getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}
}
