package br.com.audiovisual.Dao;

import java.sql.SQLException;
import java.util.List;

public interface InterfaceDAO<T> {
	public void salvar(T obj) throws SQLException;
	public void atualizar(T obj) throws SQLException;
	public void excluir(Long id) throws SQLException;
	public T consultarById(Long id) throws SQLException;
	public List<T> consultarTodos() throws SQLException;
}
