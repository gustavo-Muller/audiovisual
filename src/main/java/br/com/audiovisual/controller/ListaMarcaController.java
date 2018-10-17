package br.com.audiovisual.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import br.com.audiovisual.model.Marca;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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

	List<Marca> marcas = new ArrayList<>();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		clNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		clDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));

		tbMarca.setItems(FXCollections.observableArrayList(marcas));
	}

}
