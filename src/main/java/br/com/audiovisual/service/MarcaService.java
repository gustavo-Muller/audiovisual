package br.com.audiovisual.service;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import br.com.audiovisual.Exeption.RegraDeNegocioExeption;
import br.com.audiovisual.dao.MarcaDao;
import br.com.audiovisual.model.Equipamento;
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

	public void excluir(Long id) throws SQLException {
		if (id == 0 || id == null)
			return;
		marcaDao.excluir(id);
	}

	public Marca consulteById(Long id) throws SQLException {
		return marcaDao.consultarById(id);
	}

	public void editar(Marca marca) throws SQLException {
		EquipamentoService equipamentoService = new EquipamentoService();
		List<Equipamento> equipamentos = equipamentoService.listar();
		
		boolean possuiMarcaVinculadaAEquipamento =
				equipamentos.stream()
		.filter(e -> e.getMarca() == marca) != null;
		
		if(possuiMarcaVinculadaAEquipamento) {
			new RegraDeNegocioExeption("Existe equipamento cadastrado para essa marca,\n não é possível realizar a edição!");
		}

		marcaDao.atualizar(marca);
		return;
	}

}
