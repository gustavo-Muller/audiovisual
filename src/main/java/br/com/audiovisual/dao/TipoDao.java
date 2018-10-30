package br.com.audiovisual.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import br.com.audiovisual.ConnectionFactory.ConnectionFactory;
import br.com.audiovisual.model.Tipo;

public class TipoDao implements InterfaceDAO<Tipo>{
	
	private PreparedStatement stmt;
	private Statement stm;
	private Connection con;
	private ConnectionFactory connection = null;
	
	public TipoDao() {
		this.connection = new ConnectionFactory();
		this.con = this.connection.getConnection();
	}
	
	private final String listar = "SELECT * FROM tipo";

	@Override
	public void salvar(Tipo obj) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void atualizar(Tipo obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void excluir(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Tipo consultarById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Tipo> consultarTodos() throws SQLException {
		List<Tipo> list = new ArrayList<>();
		ResultSet res = null;
		stm = con.createStatement();
		res = stm.executeQuery(listar);
		
		while(res.next()) {
			Tipo tipo = new Tipo();
			tipo.setId(res.getLong("id"));
			tipo.setDescricao(res.getString("descricao"));
			list.add(tipo);
		}
		
		return list;
	}

}
