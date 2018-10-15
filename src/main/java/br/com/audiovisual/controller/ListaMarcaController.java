package br.com.audiovisual.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import br.com.audiovisual.model.Marca;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ListaMarcaController implements Initializable {

	@FXML
	private TableView<Marca> tbMarca;

	@FXML
	private TableColumn<Marca, String> clNome;

	@FXML
	private TableColumn<Marca, String> clDescricao;

	@FXML
	private JFXButton btNova;

	@FXML
	private JFXButton btExluir;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

}
