package br.com.audiovisual.model;

import br.com.audiovisual.enuns.TipoUsuario;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Usuario {

	private LongProperty id;
	private StringProperty nome;
	private StringProperty email;
	private StringProperty telefone;
	private StringProperty celular;
	private ObjectProperty<TipoUsuario> tipoUsuario;

	public Usuario(Long id, String nome, String email, String telefone, String celular, TipoUsuario tipoUsuario) {
		this.id = new SimpleLongProperty(id);
		this.nome = new SimpleStringProperty(nome);
		this.email = new SimpleStringProperty(email);
		this.telefone = new SimpleStringProperty(telefone);
		this.celular = new SimpleStringProperty(celular);
		this.tipoUsuario = new SimpleObjectProperty<TipoUsuario>(tipoUsuario);
	}

	public Usuario() {

	}

	public LongProperty getId() {
		return id;
	}

	public void setId(LongProperty id) {
		this.id = id;
	}

	public StringProperty getNome() {
		return nome;
	}

	public void setNome(StringProperty nome) {
		this.nome = nome;
	}

	public StringProperty getEmail() {
		return email;
	}

	public void setEmail(StringProperty email) {
		this.email = email;
	}

	public StringProperty getTelefone() {
		return telefone;
	}

	public void setTelefone(StringProperty telefone) {
		this.telefone = telefone;
	}

	public ObjectProperty<TipoUsuario> getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(ObjectProperty<TipoUsuario> tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public StringProperty getCelular() {
		return celular;
	}

	public void setCelular(StringProperty celular) {
		this.celular = celular;
	}

}
