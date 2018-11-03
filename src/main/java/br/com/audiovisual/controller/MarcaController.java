package br.com.audiovisual.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import br.com.audiovisual.model.Marca;
import br.com.audiovisual.service.MarcaService;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class MarcaController implements Initializable {

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

	private Marca marca;
	private MarcaService service = new MarcaService();
	List<Marca> marcas = new ArrayList<>();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			populaView();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@FXML
	void limpar(ActionEvent event) {
		clear();
	}

	@FXML
	void salvar() throws SQLException {
		capturaObjetosDosCampos();
		service.salvar(marca);
		clear();
		populaView();
	}

	private void populaView() throws SQLException {
		marcas = service.listar();
		tbClNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		tbClDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));

		tbMarca.setItems(FXCollections.observableArrayList(marcas));
		
	}
	
	public void clear() {
		txtNomeMarca.setText(null);
		txtAreaDescricao.setText(null);
	}

	private void capturaObjetosDosCampos() {
		marca = new Marca();
		marca.setNome(txtNomeMarca.getText());
		marca.setDescricao(txtAreaDescricao.getText());
	}

}
