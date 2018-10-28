package br.com.audiovisual.model;

public class Tipo {
	
	private Long id;
	private String descricao;

	public Tipo() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String nome) {
		this.descricao = nome;
	}

	@Override
	public String toString() {
		return descricao;
	}

}
