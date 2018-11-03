package br.com.audiovisual.dao;

import java.sql.SQLException;
import java.util.List;

public interface InterfaceDAO<T> {
	public void salvar(T obj) throws SQLException;
	public void atualizar(T obj) throws SQLException;
	public void excluir(Long id) throws SQLException;
	public T consultarById(int id);
	public List<T> consultarTodos() throws SQLException;
}
