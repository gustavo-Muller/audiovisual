package br.com.audiovisual.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import br.com.audiovisual.ConnectionFactory.ConnectionFactory;

public class PessoaDao {

	private PreparedStatement stmt;
	private Connection con;
	private Statement stm;
	private ConnectionFactory connection = null;

	public PessoaDao() {
		this.connection = new ConnectionFactory();
		this.con = this.connection.getConnection();
	}

	String salvar = "INSERT INTO usuario(nome, email, telefone, tipo) values(?, ?, ?, ?)";
}
