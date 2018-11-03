package br.com.audiovisual.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.audiovisual.ConnectionFactory.ConnectionFactory;
import br.com.audiovisual.model.Usuario;

public class UsuarioDao {

	private PreparedStatement stmt;
	private Connection con;
	private ConnectionFactory connection = null;

	public UsuarioDao() {
		this.connection = new ConnectionFactory();
		this.con = this.connection.getConnection();
	}

	private final String salvar = "INSERT INTO usuario(nome, email, telefone, celular, tipo) values(?, ?, ?, ?, ?)";
	private final String listar = "SELECT * FROM usuario";
	private final String delete = "DELETE FROM usuario WHERE id = ?";
	private final String editar = "UPDATE usuario SET nome = ?, email = ?, telefone = ?, celular = ?, tipo = ? WHERE id = ?";

	public void salvar(Usuario pessoa) throws SQLException {
		con.setAutoCommit(false);
		stmt = con.prepareStatement(salvar);

		stmt.setString(1, pessoa.getNome());
		stmt.setString(2, pessoa.getEmail());
		stmt.setString(3, pessoa.getTelefone());
		stmt.setString(4, pessoa.getCelular());
		int tipo = pessoa.getTipoUsuario().getId().intValue();
		stmt.setInt(5, tipo);

		stmt.executeUpdate();
		con.commit();
	}

	public void editar(Usuario usuario) throws SQLException {
		con.setAutoCommit(false);
		stmt = con.prepareStatement(editar);

		stmt.setString(1, usuario.getNome());
		stmt.setString(2, usuario.getEmail());
		stmt.setString(3, usuario.getTelefone());
		stmt.setString(4, usuario.getCelular());
		int tipo = usuario.getTipoUsuario().getId().intValue();
		stmt.setInt(5, tipo);
		stmt.setLong(6, usuario.getId());

		stmt.executeUpdate();
		con.commit();
	}

	public List<Usuario> listarUsuario() throws SQLException {
		List<Usuario> list = new ArrayList<>();
		ResultSet res = null;

		stmt = (PreparedStatement) con.prepareStatement(listar);
		res = stmt.executeQuery();

		while (res.next()) {
			Usuario user = new Usuario();
			user.setId(res.getLong("id"));
			user.setNome(res.getString("nome"));
			user.setEmail(res.getString("email"));
			user.setTelefone(res.getString("telefone"));
			user.setCelular(res.getString("celular"));
			user.setTipoUsuario(res.getInt("tipo"));
			list.add(user);
		}

		return list;
	}

	public void excluir(Usuario usuario) throws SQLException {
		con.setAutoCommit(false);
		stmt = con.prepareStatement(delete);

		stmt.setLong(1, usuario.getId());

		stmt.executeUpdate();
		con.commit();
	}

}
