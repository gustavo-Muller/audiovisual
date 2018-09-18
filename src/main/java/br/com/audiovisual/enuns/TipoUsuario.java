package br.com.audiovisual.enuns;

public enum TipoUsuario {

	PROFESSOR(1L, "Professor"), ADIMINISTRADOR(2L, "Administrador");

	private final Long id;
	private final String valor;

	private TipoUsuario(Long id, String valor) {
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
