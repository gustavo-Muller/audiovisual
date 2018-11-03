package br.com.audiovisual.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import br.com.audiovisual.model.Marca;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class MarcaController {

	@FXML
	private JFXTextField txtNomeMarca;

	@FXML
	private JFXButton btSalvar;

	@FXML
	private JFXButton btLimpar;

	@FXML
	private TableView<Marca> tbMarca;

	@FXML
	private TableColumn<Marca, String> tbClNome;

	@FXML
	private TableColumn<Marca, String> tbClDescricao;

	@FXML
	private JFXTextArea txtAreaDescricao;

	@FXML
	void limpar(ActionEvent event) {

	}

	@FXML
	void salvar(ActionEvent event) {

	}
}
