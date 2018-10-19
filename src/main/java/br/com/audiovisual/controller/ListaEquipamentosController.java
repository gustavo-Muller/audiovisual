package br.com.audiovisual.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import br.com.audiovisual.model.Equipamento;
import br.com.audiovisual.model.Marca;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ListaEquipamentosController implements Initializable {

	@FXML
	private JFXButton btnAdicionar;

	@FXML
	private JFXButton btnEditar;

	@FXML
	private JFXButton btnExcluir;

	@FXML
	private TableView<Equipamento> tblEquipamentos;

	@FXML
	private TableColumn<Equipamento, String> clnNome, clnTipo, clnMarca, clnDescricao;

	@FXML
	private TableColumn<Equipamento, Integer> clnCodigo;

	private Stage stage;

	List<Equipamento> equipamentos = new ArrayList<>();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Equipamento equipamento = new Equipamento("projetor", 123, "algo", new Marca(1L, "marca", "Marca de Teste"),
				"descricao");
		equipamentos.add(equipamento);

		clnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		clnCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
		clnTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
		clnMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
		clnDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));

		tblEquipamentos.setItems(FXCollections.observableArrayList(equipamentos));
	}

	@FXML
	void adicionar() throws IOException {
		Stage stage = new Stage();
		AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("../view/FXMLAparelhoCadastro.fxml"));
		Scene scene = new Scene(pane);
		stage.setScene(scene);
		stage.initStyle(StageStyle.UTILITY);
		stage.show();
	}

	@FXML
	void editar() throws IOException {
		this.stage = new Stage();
		AnchorPane root;
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/FXMLAparelhoCadastro.fxml"));
		root = fxmlLoader.load();
		EquipamentoController equipamentoController = fxmlLoader.getController();
		stage.setTitle("Edição de Equipamento");
		stage.setScene(new Scene(root));
		stage.initStyle(StageStyle.UTILITY);
		Equipamento equipamento = this.tblEquipamentos.getSelectionModel().getSelectedItem();
		equipamentoController.monteObjetoNaTela(equipamento);
		stage.show();
	}

	@FXML
	void excluir() {

	}
}
