package br.com.audiovisual.service;

import java.sql.SQLException;
import java.util.List;

import br.com.audiovisual.dao.TipoDao;
import br.com.audiovisual.model.Tipo;

public class TipoService {

	private TipoDao tipoDao = new TipoDao();

	public List<Tipo> listar() throws SQLException {
		List<Tipo> tipos = tipoDao.consultarTodos();
		return tipos;
	}
}
