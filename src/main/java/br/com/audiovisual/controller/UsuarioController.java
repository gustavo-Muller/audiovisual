package br.com.audiovisual.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import br.com.audiovisual.enumerador.TipoUsuario;
import br.com.audiovisual.model.Usuario;
import br.com.audiovisual.service.UsuarioService;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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

	private UsuarioService service = new UsuarioService();
	List<Usuario> usuarios = new ArrayList<>();
	private Usuario user = new Usuario();
	private Usuario usuarioSelecionado;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			AdicioneNaGrid();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		carregarTipoUsuario();
	}

	@FXML
	void editar(ActionEvent event) {

	}

	@FXML
	void excluir() throws SQLException {
		selecao();
		this.service.ecluirUsuario(usuarioSelecionado);
		AdicioneNaGrid();
		clear();
	}

	@FXML
	void salvar(ActionEvent event) throws SQLException {
		montaObjeto();
		service.salva(user);
		AdicioneNaGrid();
		clear();
	}

	public void selecao() {
		usuarioSelecionado = this.tblUsuarios.getSelectionModel().getSelectedItem();
		this.txtNome.setText(usuarioSelecionado.getNome());
		this.txtEmail.setText(usuarioSelecionado.getEmail());
		this.txtCelular.setText(usuarioSelecionado.getCelular());
		this.txtTelefone.setText(usuarioSelecionado.getTelefone());
		this.cbTipoPessoa.getSelectionModel().select(usuarioSelecionado.getTipoUsuario());
	}

	private void clear() {
		txtCelular.setText(null);
		txtEmail.setText(null);
		txtNome.setText(null);
		txtTelefone.setText(null);
	}

	private void AdicioneNaGrid() throws SQLException {
		usuarios = service.listar();
		clnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		clnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
		clnTelefoneFixo.setCellValueFactory(new PropertyValueFactory<>("telefone"));
		clnCelular.setCellValueFactory(new PropertyValueFactory<>("celular"));
		clnTipo.setCellValueFactory(new PropertyValueFactory<>("tipoUsuario"));

		tblUsuarios.setItems(FXCollections.observableArrayList(usuarios));
	}

	public void montaObjeto() {
		user.setNome(txtNome.getText());
		user.setEmail(txtEmail.getText());
		user.setTelefone(txtTelefone.getText());
		user.setCelular(txtCelular.getText());
		user.setTipoUsuario(cbTipoPessoa.getValue());
	}

	private void carregarTipoUsuario() {
		cbTipoPessoa.getItems().add(null);
		cbTipoPessoa.getItems().addAll(TipoUsuario.values());
	}

	public Usuario getUsuarioSelecionado() {
		return usuarioSelecionado;
	}

}
