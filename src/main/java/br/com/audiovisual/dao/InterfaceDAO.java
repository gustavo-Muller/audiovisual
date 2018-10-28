package br.com.audiovisual.dao;

import java.sql.SQLException;
import java.util.List;

public interface InterfaceDAO<T> {
	public void salvar(T obj) throws SQLException;
	public void atualizar(T obj);
	public void exvluir(int id);
	public T consultarById(int id);
	public List<T> consultarTodos() throws SQLException;
}
