package br.com.audiovisual.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import br.com.audiovisual.enuns.TipoUsuario;
import br.com.audiovisual.model.Usuario;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;

public class UsuarioController implements Initializable {

	@FXML
	private JFXTextField txtNome;

	@FXML
	private JFXTextField txtCelular;

	@FXML
	private JFXTextField txtTelefone;

	@FXML
	private ComboBox<TipoUsuario> cbTipoPessoa;

	@FXML
	private JFXTextField txtEmail;

	@FXML
	private JFXButton btSalvar;

	@FXML
	private JFXButton btEditar;

	@FXML
	private JFXButton btExcluir;

	@FXML
	private GridPane grdCadastroUsuario;

	@FXML
	private TableColumn<Usuario, String> clnNome, clnEmail, clnCelular, clnTelefoneFixo, clnTipo;

	@FXML
	private TableView<Usuario> tblUsuarios;

	private ObservableList<Usuario> _usuarios = FXCollections.observableArrayList();

	@FXML
	void editar(ActionEvent event) {

	}

	@FXML
	void excluir(ActionEvent event) {

	}

	@FXML
	void salvar(ActionEvent event) {
		if (!PodeSalvar())
			return;

		TipoUsuario tipoUsuario = cbTipoPessoa.getSelectionModel().getSelectedItem();
		_usuarios.add(new Usuario(1L, txtNome.getText(), txtEmail.getText(), txtCelular.getText(),
				txtTelefone.getText(), tipoUsuario));
		AdicioneNaGrid();
		clear();

	}

	private void clear() {
		txtCelular.setText(null);
		txtEmail.setText(null);
		txtNome.setText(null);
		txtTelefone.setText(null);
	}

	private void AdicioneNaGrid() {
		clnNome.setCellValueFactory(p -> p.getValue().getNome());
		clnEmail.setCellValueFactory(p -> p.getValue().getEmail());
		clnCelular.setCellValueFactory(p -> p.getValue().getCelular());
		clnTelefoneFixo.setCellValueFactory(p -> p.getValue().getTelefone());
		clnTipo.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getTipoUsuario().getName()));
		this.tblUsuarios.setItems(_usuarios);
	}

	private boolean PodeSalvar() {
		return true;
	}

	private void carregarTipoUsuario() {
		cbTipoPessoa.getItems().add(null);
		cbTipoPessoa.getItems().addAll(TipoUsuario.values());
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		AdicioneNaGrid();
		carregarTipoUsuario();
	}

}
