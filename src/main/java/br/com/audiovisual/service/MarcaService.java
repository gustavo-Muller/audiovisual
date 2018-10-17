package br.com.audiovisual.service;

import java.util.List;

import br.com.audiovisual.dao.MarcaDao;
import br.com.audiovisual.model.Marca;

public class MarcaService {

	private MarcaDao marcaDao;

	public List<Marca> listar() {
		List<Marca> marcas = marcaDao.listarMarca();
		return marcas;
	}

}
