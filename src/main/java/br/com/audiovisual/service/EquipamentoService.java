package br.com.audiovisual.service;

import java.sql.SQLException;
import java.util.List;

import br.com.audiovisual.Dao.EquipamentoDao;
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

	public void excluir(Long codigo) throws SQLException {
		if(codigo == 0) return;
		else {
			dao.excluir(codigo);
		}
		
	}

	public void editar(Equipamento equipamento) throws SQLException {
		if(equipamento.getId() == null || equipamento.getId() == 0) return;
		dao.atualizar(equipamento);
	}

}
