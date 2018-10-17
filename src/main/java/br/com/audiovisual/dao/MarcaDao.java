package br.com.audiovisual.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import br.com.audiovisual.ConnectionFactory.ConnectionFactory;
import br.com.audiovisual.model.Marca;

public class MarcaDao {
	
	private PreparedStatement stmt;
	private Statement stm;
	private Connection con;
	private ConnectionFactory connection = null;
	
	private final String listar = "SELECT * FROM marca";
	
	public MarcaDao() {
		this.connection = new ConnectionFactory();
		this.con = this.connection.getConnection();
	}

	public List<Marca> listarMarca() throws SQLException {
		List<Marca> list = new ArrayList<>();
		ResultSet res = null;
		stm = con.createStatement();
		res = stm.executeQuery(listar);
		
		while(res.next()) {
			Marca m = new Marca();
			m.setId(res.getLong("id"));
			m.setNome(res.getString("nome"));
			m.setDescricao(res.getString("descricao"));
			list.add(m);
		}
		
		stmt.executeUpdate();
		con.commit();
		return list;
	}

}
