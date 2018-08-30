package br.com.audiovisual.model;

import java.util.ArrayList;

public class Teste {

	private Long id;
	private String nome;
	ArrayList<Teste> listaTeste;

	public ArrayList<Teste> getListaTeste() {
		return listaTeste;
	}

	public void setListaTeste(ArrayList<Teste> listaTeste) {
		this.listaTeste = listaTeste;
	}

	public void addTeste(Teste t) {
		listaTeste.add(t);
	}

	public Teste() {
		listaTeste = new ArrayList<>();
	}
	
	public Teste(String nome) {
		this.nome = nome;
	}

	public Teste(Long id, String nome) {
		this.id = id;
		this.nome = nome;
		listaTeste = new ArrayList<>();
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

	public String toString() {
		return this.nome;
	}

}
