package br.com.audiovisual.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.audiovisual.ConnectionFactory.ConnectionFactory;
import br.com.audiovisual.model.Usuario;
import javafx.beans.property.LongProperty;

public class PessoaDao {

	private PreparedStatement stmt;
	private Connection con;
	private Statement stm;
	private ConnectionFactory connection = null;

	public PessoaDao() {
		this.connection = new ConnectionFactory();
		this.con = this.connection.getConnection();
	}

	private final String salvar = "INSERT INTO usuario(nome, email, telefone, tipo) values(?, ?, ?, ?)";
	private final String listar = "SELECT * FROM usuario";

	public void salvar(Usuario pessoa) throws SQLException {
		con.setAutoCommit(false);
		stmt = con.prepareStatement(salvar);

		stmt.setString(1, String.valueOf(pessoa.getNome()));
		stmt.setString(2, String.valueOf(pessoa.getEmail()));
		stmt.setString(3, String.valueOf(pessoa.getTelefone()));
		stmt.setString(4, String.valueOf(pessoa.getCelular()));
		stmt.setString(5, String.valueOf(pessoa.getTipoUsuario()));

		stmt.executeQuery();
		con.commit();
	}

	public List<Usuario> listarUsuario() throws SQLException {
		List<Usuario> list = new ArrayList<>();
		ResultSet res = null;

		stm = con.createStatement();
		res = stm.executeQuery(listar);

		while (res.next()) {
			Usuario user = new Usuario();

			/*
			 * Implementar algo para pois o ResultSet não possui metodo
			 * com suporte para  LongProperty ou qualquer tipo Property
			 */

		}

		return list;
	}
}
