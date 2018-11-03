package br.com.audiovisual.dao;

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
	public void atualizar(Marca obj) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void excluir(Long id) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public Marca consultarById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Marca> consultarTodos() throws SQLException {
		List<Marca> list = new ArrayList<>();
		ResultSet res = null;
		stmt = (PreparedStatement) con.prepareStatement(listar);
		res = stmt.executeQuery();

		while (res.next()) {
			Marca m = new Marca();
			m.setId(res.getLong("idMarca"));
			m.setNome(res.getString("nome"));
			m.setDescricao(res.getString("descricao"));
			list.add(m);
		}

		return list;
	}

}
