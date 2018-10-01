package br.com.audiovisual.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.audiovisual.ConnectionFactory.ConnectionFactory;
import br.com.audiovisual.model.Teste;

public class TesteDao {

	private PreparedStatement stmt;
	private Connection con;
	private Statement stm;
	private ConnectionFactory connection = null;

	public TesteDao() {
		this.connection = new ConnectionFactory();
		this.con = this.connection.getConnection();
	}

	String sqlSalvar = "INSERT INTO teste(nome) VALUES(?)";

	public void salvarTeste(Teste teste) throws SQLException {asas
		con.setAutoCommit(false);
		stmt = con.prepareStatement(sqlSalvar);

		stmt.setString(1, teste.getNome());

		stmt.executeUpdate();
		con.commit();

	}

	public List<Teste> listarTestes() throws SQLException {
		List<Teste> lista = new ArrayList<Teste>();
		ResultSet res = null;
		stm = con.createStatement();
		res = stm.executeQuery("select * from teste");
		while (res.next()) {
			Teste teste = new Teste();

			teste.setId(res.getLong("id"));
			teste.setNome(res.getString("nome"));
			lista.add(teste);
		}
		return lista;
	}

}
