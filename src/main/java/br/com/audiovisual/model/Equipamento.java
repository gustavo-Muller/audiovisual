package br.com.audiovisual.model;

import br.com.audiovisual.enumerador.TipoEquipamento;

public class Equipamento {
	
	private Long id;
	private String codigo;
	private String nome;
	private TipoEquipamento tipo;
	private Marca marca;
	private String descricao;

	public Equipamento(Long id, String nome, String codigo, TipoEquipamento tipo, Marca marca, String descricao) {
		this.id = id;
		this.nome = nome;
		this.codigo = codigo;
		this.tipo = tipo;
		this.marca = marca;
		this.descricao = descricao;
	}

	public Equipamento() {

	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public TipoEquipamento getTipo() {
		return tipo;
	}

	public void setTipo(Long id) {
		for (TipoEquipamento tipo : TipoEquipamento.values()) {
			if (tipo.getId() == id) {
				this.tipo = tipo;
				break;
			}
		}
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
