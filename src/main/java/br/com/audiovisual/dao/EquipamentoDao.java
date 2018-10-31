package br.com.audiovisual.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.audiovisual.ConnectionFactory.ConnectionFactory;
import br.com.audiovisual.model.Equipamento;
import br.com.audiovisual.model.Marca;
import br.com.audiovisual.model.Tipo;

public class EquipamentoDao implements InterfaceDAO<Equipamento> {

	private PreparedStatement stmt;
	private Connection con;
	private Statement stm;
	private ConnectionFactory connection = null;
	
	public EquipamentoDao() {
		this.connection = new ConnectionFactory();
		this.con = this.connection.getConnection();
	}
	
	private final String salvar = "INSERT INTO equipamento(nome, descricao, id_marca, id_tipo)"
								+ " VALUES(?, ?, ?, ?)";
	private final String listar = "SELECT e.id AS Equipamento_Id, e.nome, e.descricao, e.id_marca, e.id_tipo, \n" + 
								  "t.descricao AS Tipo_Descricao, m.descricao AS Marca_Descricao\n" + 
								  "FROM EQUIPAMENTO e\n" + 
								  "INNER JOIN tipo t ON t.id = e.id_tipo\n" + 
								  "INNER JOIN marca m ON m.id = e.id_marca;";
	private final String deletar = "DELETE FROM equipamento WHERE id = ?";
	private final String editar = "UPDATE equipamento SET nome = ?, descricao = ?, id_marca = ?, id_tipo = ? WHERE id = ?";
	
	@Override
	public void salvar(Equipamento obj) throws SQLException {
		con.setAutoCommit(false);
		stmt = con.prepareStatement(salvar);
		
		stmt.setString(1, obj.getNome());
		stmt.setString(2, obj.getDescricao());
		stmt.setLong(3, obj.getMarca().getId());
		stmt.setLong(4, obj.getTipo().getId());
		
		stmt.executeUpdate();
		con.commit();
	}

	@Override
	public void atualizar(Equipamento obj) throws SQLException {
		con.setAutoCommit(false);
		stmt = con.prepareStatement(editar);
		
		stmt.setString(1, obj.getNome());
		stmt.setString(2, obj.getDescricao());
		stmt.setLong(3, obj.getMarca().getId());
		stmt.setLong(4, obj.getTipo().getId());
		stmt.setInt(5, obj.getCodigo());
		
		stmt.executeUpdate();
		con.commit();
		
	}

	@Override
	public void excluir(int id) throws SQLException {
		con.setAutoCommit(false);
		stmt = con.prepareStatement(deletar);
		
		stmt.setInt(1, id);
		
		int rowsChange = stmt.executeUpdate();
		if(rowsChange == 0) return;
		con.commit();
	}

	@Override
	public Equipamento consultarById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Equipamento> consultarTodos() throws SQLException {
		List<Equipamento> equipamentos = new ArrayList<>();
		
		
		ResultSet res = null;
		
		stm = con.createStatement();
		res = stm.executeQuery(listar);
		
		while(res.next()) {
			Equipamento equipamento = new Equipamento();
			Tipo tipo = new Tipo();
			Marca marca = new Marca();
			
			equipamento.setNome(res.getString("nome"));
			equipamento.setCodigo(res.getInt("Equipamento_id"));
			
			tipo.setId(res.getLong("id_tipo"));
			tipo.setDescricao(res.getString("Tipo_Descricao"));
			
			marca.setId(res.getLong("id_marca"));
			marca.setDescricao(res.getString("Marca_Descricao"));
			
			equipamento.setTipo(tipo);
			equipamento.setMarca(marca);
			equipamento.setDescricao(res.getString("descricao"));
			equipamentos.add(equipamento);
		}
		
		return equipamentos;
	}
	

}
