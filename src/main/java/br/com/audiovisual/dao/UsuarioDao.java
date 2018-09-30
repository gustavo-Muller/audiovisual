package br.com.audiovisual.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.audiovisual.ConnectionFactory.ConnectionFactory;
import br.com.audiovisual.enumerador.TipoUsuario;
import br.com.audiovisual.model.Usuario;

public class UsuarioDao {

	private PreparedStatement stmt;
	private Connection con;
	private Statement stm;
	private ConnectionFactory connection = null;

	public UsuarioDao() {
		this.connection = new ConnectionFactory();
		this.con = this.connection.getConnection();
	}

	private final String salvar = "INSERT INTO usuario(nome, email, telefone, celular, tipo) values(?, ?, ?, ?, ?)";
	private final String listar = "SELECT * FROM usuario";

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

	public List<Usuario> listarUsuario() throws SQLException {
		List<Usuario> list = new ArrayList<>();
		ResultSet res = null;

		stm = con.createStatement();
		res = stm.executeQuery(listar);

		while (res.next()) {
			Usuario user = new Usuario();
			user.setId(res.getLong("id"));
			user.setNome(res.getString("nome"));
			user.setEmail(res.getString("email"));
			user.setTelefone(res.getString("telefone"));
			user.setCelular(res.getString("celular"));
//			final String tipo = res.getString("tipo");
			user.setTipoUsuario(TipoUsuario.PROFESSOR);
			list.add(user);
		}

		return list;
	}
}
