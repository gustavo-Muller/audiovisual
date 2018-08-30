package br.com.audiovisual.service;

import java.util.ArrayList;
import java.util.List;

import br.com.audiovisual.dao.TesteDao;
import br.com.audiovisual.model.Teste;

public class TesteService {

	TesteDao testeDao = new TesteDao();

	public void salvar(Teste teste) {
		try {
			if (teste.getId() == null) {
				testeDao.salvarTeste(teste);
			} else {
				System.out.println("Não pode");
			}
		} catch (Exception e) {

			System.out.println("Teste e nulo " + e.getMessage());
		}
	}

	public List<Teste> listarTeste() {
		List<Teste> testes = new ArrayList<Teste>();
		try {
			testes = testeDao.listarTestes();
		} catch (Exception e) {
			e.getMessage();
		}
		return testes;
	}

}
