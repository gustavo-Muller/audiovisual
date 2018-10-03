package br.com.audiovisual.controller;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class EquipamentoController {

	@FXML
	private JFXTextField txtNome;

	@FXML
	private JFXTextField txtCodigo;

	@FXML
	private JFXTextField txtMarca;

	@FXML
	private JFXTextArea txtDescricao;

	@FXML
	private JFXButton btnSalvar;

	@FXML
	private JFXButton btnLimpar;

	@FXML
	private JFXButton btnListar;

	@FXML
	void limpar(ActionEvent event) {

	}

	@FXML
	void salvar(ActionEvent event) {

	}

	@FXML
	void listar(ActionEvent event) throws IOException {
		Stage stage = new Stage();
		AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("../view/FXMLAparelhos.fxml"));
		Scene scene = new Scene(pane);
		stage.setScene(scene);
		stage.initStyle(StageStyle.UTILITY);
		stage.show();
	}

}
