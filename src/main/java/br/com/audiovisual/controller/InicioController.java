package br.com.audiovisual.controller;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class InicioController {

	@FXML
	private AnchorPane acPanePrincipal;

	@FXML
	private MenuBar mbMenu;

	@FXML
	private MenuItem miPessoa;

	@FXML
	private MenuItem miTeste;

	@FXML
	private MenuItem miEquipamento;

	@FXML
	private MenuItem miLEquipamento;

	Stage stage = new Stage();

	public void chamarTeste() throws IOException {

		URL arquivoFxml;
		arquivoFxml = getClass().getResource("../view/FXMLStart.fxml");
		Parent fxmlParent = (Parent) FXMLLoader.load(arquivoFxml);
		acPanePrincipal.getChildren().clear();
		acPanePrincipal.getChildren().add(fxmlParent);
	}

	public void irParaCadastroUsuario() throws IOException {

		URL arquivoFxml;
		arquivoFxml = getClass().getResource("../view/FXMLUsuarioCadastro.fxml");
		Parent fxmlParent = (Parent) FXMLLoader.load(arquivoFxml);
		acPanePrincipal.getChildren().clear();
		acPanePrincipal.getChildren().add(fxmlParent);
	}

	@FXML
	void irParaCadastroEquipamento() throws IOException {

		URL arquivoFxml;
		arquivoFxml = getClass().getResource("../view/FXMLAparelhoCadastro.fxml");
		Parent fxmlParent = (Parent) FXMLLoader.load(arquivoFxml);
		acPanePrincipal.getChildren().clear();
		acPanePrincipal.getChildren().add(fxmlParent);
	}

	@FXML
	void irParaLocacaoDeEquipamento() throws IOException {

		URL arquivoFxml;
		arquivoFxml = getClass().getResource("../view/FXMLLocacao.fxml");
		Parent fxmlParent = (Parent) FXMLLoader.load(arquivoFxml);
		acPanePrincipal.getChildren().clear();
		acPanePrincipal.getChildren().add(fxmlParent);
	}

}
