package br.com.audiovisual.service;

import java.sql.SQLException;
import java.util.List;

import br.com.audiovisual.dao.EquipamentoDao;
import br.com.audiovisual.model.Equipamento;

public class EquipamentoService {
	
	EquipamentoDao dao = new EquipamentoDao();
	
	public void salvar(Equipamento equipamento) throws SQLException {
		dao.salvar(equipamento);
	}
	
	public List<Equipamento> listar() throws SQLException {
		List<Equipamento> equipamentos = dao.consultarTodos();
		return equipamentos;
	}

}
