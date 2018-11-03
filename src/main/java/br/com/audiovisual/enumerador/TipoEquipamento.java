package br.com.audiovisual.enumerador;

public enum TipoEquipamento {

	PROJETOR(1L, "Projetor"), NOTEBOOK(2L, "Notebook"), CABO(3L, "Cabo"), SOM(4L, "Som"), OUTROS(5L, "Outros");

	private final Long id;
	private final String valor;

	private TipoEquipamento(Long id, String valor) {
		this.id = id;
		this.valor = valor;
	}

	public Long getId() {
		return id;
	}

	public String getValor() {
		return valor;
	}

	@Override
	public String toString() {
		return this.valor;
	}
	
}
