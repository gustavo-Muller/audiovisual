package br.com.audiovisual.service;

import java.sql.SQLException;
import java.util.List;

import br.com.audiovisual.dao.UsuarioDao;
import br.com.audiovisual.model.Usuario;

public class UsuarioService {

	UsuarioDao dao = new UsuarioDao();

	public boolean jaPossue(Usuario user) throws SQLException {
		boolean result;
		if (dao.listarUsuario().contains(user)) {
			result = false;
		} else {
			result = true;
			dao.salvar(user);
		}

		return result;
	}
	
	public List<Usuario> listar() throws SQLException {
		List<Usuario> list = dao.listarUsuario();
		return list;
	}
	
	public void salva(Usuario u) throws SQLException {
		dao.salvar(u);
	}

}
