package br.com.audiovisual.controller;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.audiovisual.model.Teste;
import br.com.audiovisual.service.TesteService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class StartController implements Serializable {

	private static final long serialVersionUID = 1L;

	@FXML
	private Button btnStart;

	@FXML
	private TextField txtNome;

	@FXML
	private Button btnSalvar;

	List<Teste> testes = new ArrayList<>();

	Teste teste = new Teste();

	public void Iniciar() throws IOException {
		Stage stage = new Stage();
		AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("../view/FXMLIniciou.fxml"));
		Scene scene = new Scene(pane);
		stage.setScene(scene);
		stage.initStyle(StageStyle.UTILITY);
		stage.show();
	}

	public void setarDados() {
		teste.setNome(txtNome.getText());
	}
	
	public void limparCampo() {
		txtNome.setText("");
	}

	@FXML
	public void salvar() throws SQLException {
		TesteService testeService = new TesteService();
		setarDados();
		testeService.salvar(teste);
		testes.add(teste);
		teste = new Teste();
		limparCampo();
	}
}
