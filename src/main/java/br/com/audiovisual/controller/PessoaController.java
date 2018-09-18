package br.com.audiovisual.controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PessoaController {

	@FXML
	private MenuBar mbMenu;

	@FXML
	private MenuItem miPessoa;

	@FXML
	private MenuItem miTeste;
	
	public void chamarTeste() throws IOException {
			Stage stage = new Stage();
			AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("../view/FXMLStart.fxml"));
			Scene scene = new Scene(pane);
			stage.setScene(scene);
			stage.initStyle(StageStyle.UTILITY);
			stage.show();
	}
	
	public void chamarCadastroUsuario() throws IOException {
		Stage stage = new Stage();
		AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("../view/FXMLPessoaCadastro.fxml"));
		Scene scene = new Scene(pane);
		stage.setScene(scene);
		stage.initStyle(StageStyle.UTILITY);
		stage.show();
}

}
