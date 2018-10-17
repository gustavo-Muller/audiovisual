package br.com.audiovisual.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import br.com.audiovisual.ConnectionFactory.ConnectionFactory;
import br.com.audiovisual.model.Marca;

public class MarcaDao {
	
	private PreparedStatement stmt;
	private Connection con;
	private ConnectionFactory connection = null;
	
	public MarcaDao() {
		this.connection = new ConnectionFactory();
		this.con = this.connection.getConnection();
	}

	public List<Marca> listarMarca() {
		List<Marca> list = new ArrayList<>();
		
		
		return list;
	}

}
