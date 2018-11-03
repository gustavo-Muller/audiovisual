package br.com.audiovisual.service;

import java.sql.SQLException;
import java.util.List;

import br.com.audiovisual.dao.MarcaDao;
import br.com.audiovisual.model.Marca;

public class MarcaService {

	private MarcaDao marcaDao = new MarcaDao();

	public List<Marca> listar() throws SQLException {
		List<Marca> marcas = marcaDao.consultarTodos();
		return marcas;
	}

	public void salvar(Marca marca) throws SQLException {
		marcaDao.salvar(marca);
	}

}
