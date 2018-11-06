package br.com.audiovisual.Dao;

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

public class EquipamentoDao implements InterfaceDAO<Equipamento> {

	private PreparedStatement stmt;
	private Connection con;
	private Statement stm;
	private ConnectionFactory connection = null;

	public EquipamentoDao() {
		this.connection = new ConnectionFactory();
		this.con = this.connection.getConnection();
	}

	private final String salvar = "INSERT INTO equipamento(codigo, nome, descricao, marca, tipo)"
			+ " VALUES(?, ?, ?, ?, ?)";
	private final String listar = "SELECT e.idEquipamento, e.codigo, e.nome, e.descricao, e.tipo, \r\n"
			+ "m.id, m.nome AS Marca_nome\r\n" + "FROM equipamento e\r\n" + "INNER JOIN marca m ON m.id = e.marca";
	private final String deletar = "DELETE FROM equipamento WHERE idEquipamento = ?";
	private final String editar = "UPDATE equipamento SET codigo = ?, nome = ?, descricao = ?, marca = ?, tipo = ? WHERE idEquipamento = ?";

	@Override
	public void salvar(Equipamento obj) throws SQLException {
		con.setAutoCommit(false);
		stmt = con.prepareStatement(salvar);

		stmt.setString(1, obj.getCodigo());
		stmt.setString(2, obj.getNome());
		stmt.setString(3, obj.getDescricao());
		stmt.setLong(4, obj.getMarca().getId());
		stmt.setLong(5, obj.getTipo().getId());

		stmt.executeUpdate();
		con.commit();
	}

	@Override
	public void atualizar(Equipamento equipamento) throws SQLException {
		con.setAutoCommit(false);
		stmt = con.prepareStatement(editar);

		stmt.setString(1, equipamento.getCodigo());
		stmt.setString(2, equipamento.getNome());
		stmt.setString(3, equipamento.getDescricao());
		stmt.setLong(4, equipamento.getMarca().getId());
		stmt.setLong(5, equipamento.getTipo().getId());
		stmt.setLong(6, equipamento.getId());

		stmt.executeUpdate();
		con.commit();

	}

	@Override
	public void excluir(Long id) throws SQLException {
		con.setAutoCommit(false);
		stmt = con.prepareStatement(deletar);

		stmt.setLong(1, id);

		int rowsChange = stmt.executeUpdate();
		if (rowsChange == 0)
			return;
		con.commit();
	}

	@Override
	public Equipamento consultarById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Equipamento> consultarTodos() throws SQLException {
		List<Equipamento> equipamentos = new ArrayList<>();

		ResultSet res = null;

		stm = con.createStatement();
		res = stm.executeQuery(listar);

		while (res.next()) {
			Equipamento equipamento = new Equipamento();
			Marca marca = new Marca();

			equipamento.setId(res.getLong("idEquipamento"));
			equipamento.setNome(res.getString("nome"));
			equipamento.setCodigo(res.getString("codigo"));

			marca.setId(res.getLong("id"));
			marca.setNome(res.getString("Marca_nome"));

			equipamento.setTipo(res.getLong("tipo"));
			equipamento.setMarca(marca);
			equipamento.setDescricao(res.getString("descricao"));
			equipamentos.add(equipamento);
		}

		return equipamentos;
	}
}
