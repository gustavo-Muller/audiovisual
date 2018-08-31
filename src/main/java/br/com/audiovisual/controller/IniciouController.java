package br.com.audiovisual.controller;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.audiovisual.model.Teste;
import br.com.audiovisual.service.TesteService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class IniciouController implements Initializable {

	@FXML
	private TableView<Teste> tbTeste;

	@FXML
	private TableColumn<Teste, String> clNome;

	@FXML
	private TableColumn<Teste, Long> clId;

	TesteService testeService = new TesteService();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		clId.setCellValueFactory(new PropertyValueFactory<Teste, Long>("id"));
		clNome.setCellValueFactory(new PropertyValueFactory<Teste, String>("nome"));
		tbTeste.setItems(populaTabela());
	}

	private ObservableList<Teste> populaTabela() {
		return FXCollections.observableArrayList(testeService.listarTeste());
	}

}
