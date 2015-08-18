package br.com.chatreact.statuses;

public enum StatusLogado {
	ONLINE(1, "On line"),
	OFFLINE(2, "Off line"),
	OCUPADO(3, "Ocupado"),
	AUSENTE(4, "Ausente");
	
	private int id;
	
	private String descricao;
	
	private StatusLogado(int id, String descricao) {
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
