package br.com.audiovisual.service;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import br.com.audiovisual.Dao.MarcaDao;
import br.com.audiovisual.Exeption.DadosInvalidosExeption;
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

	public void excluir(Long id) throws SQLException, DadosInvalidosExeption {
		EquipamentoService equipamentoService = new EquipamentoService();
		List<Equipamento> equipamentos = equipamentoService.listar();

		List<Equipamento> possuiMarcaVinculadaAEquipamento = equipamentos.stream()
				.filter(equip -> equip.getMarca().getId() == id)
				.collect(Collectors.toList());

		if (!possuiMarcaVinculadaAEquipamento.isEmpty()) {
			throw new DadosInvalidosExeption("Existe MARCA viunculada ao equipamento!");
		}

		marcaDao.excluir(id);
	}

	public Marca consulteById(Long id) throws SQLException {
		return marcaDao.consultarById(id);
	}

	public void editar(Marca marca) throws SQLException, DadosInvalidosExeption {
		marcaDao.atualizar(marca);
	}

}
