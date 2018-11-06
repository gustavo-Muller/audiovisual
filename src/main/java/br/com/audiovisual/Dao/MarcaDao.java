package br.com.audiovisual.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.audiovisual.ConnectionFactory.ConnectionFactory;
import br.com.audiovisual.model.Marca;

public class MarcaDao implements InterfaceDAO<Marca> {

	private PreparedStatement stmt;
	private Connection con;
	private ConnectionFactory connection = null;

	private final String listar = "SELECT * FROM marca";
	private final String salvar = "INSERT INTO marca(nome, descricao) VALUES(?, ?)";
	private final String editar = "UPDATE marca SET nome = ?, descricao = ? WHERE id = ?";
	private final String excluir = "DELETE FROM marca WHERE id = ?";

	public MarcaDao() {
		this.connection = new ConnectionFactory();
		this.con = this.connection.getConnection();
	}

	@Override
	public void salvar(Marca obj) throws SQLException {
		con.setAutoCommit(false);
		stmt = con.prepareStatement(salvar);

		try {

			stmt.setString(1, obj.getNome());
			stmt.setString(2, obj.getDescricao());

			stmt.executeUpdate();
			con.commit();

		} catch (Exception e) {
			e.getMessage();
		}

	}

	@Override
	public void atualizar(Marca marca) throws SQLException {
		con.setAutoCommit(false);
		stmt = con.prepareStatement(editar);
		
		stmt.setString(1, marca.getNome());
		stmt.setString(2, marca.getDescricao().isEmpty() ? "" : marca.getDescricao());
		stmt.setLong(3, marca.getId());

		stmt.executeUpdate();
		con.commit();
	}

	@Override
	public void excluir(Long id) throws SQLException {
		con.setAutoCommit(false);
		stmt = con.prepareStatement(excluir);

		stmt.setLong(1, id);

		int rowsChange = stmt.executeUpdate();
		if (rowsChange == 0)
			return;

		con.commit();
	}

	@Override
	public Marca consultarById(Long id) throws SQLException {
		stmt = con.prepareStatement(listar + " WHERE id = ");
		ResultSet res = stmt.executeQuery();
		
		Marca marca = new Marca();
		
		if(res.next()) {
			marca.setId(res.getLong("id"));
			marca.setNome("nome");
			marca.setDescricao("descricao");
		}
		return marca;
	}

	@Override
	public List<Marca> consultarTodos() throws SQLException {
		List<Marca> list = new ArrayList<>();
		ResultSet res = null;
		stmt = (PreparedStatement) con.prepareStatement(listar);
		res = stmt.executeQuery();

		while (res.next()) {
			Marca m = new Marca();
			m.setId(res.getLong("id"));
			m.setNome(res.getString("nome"));
			m.setDescricao(res.getString("descricao"));
			list.add(m);
		}

		return list;
	}

}
