package br.com.audiovisual.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import br.com.audiovisual.Exeption.DadosInvalidosExeption;
import br.com.audiovisual.Utils.Utils;
import br.com.audiovisual.enumerador.TipoUsuario;
import br.com.audiovisual.model.Usuario;
import br.com.audiovisual.service.UsuarioService;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
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

	EventHandler<ActionEvent> actionExcluir;

	@FXML
	void cancelar(ActionEvent event) {
		clear();
		usuarioSelecionado = new Usuario();
		user = new Usuario();

		btExcluir.setDisable(false);
		btEditar.setDisable(true);
	}

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
	void editar(ActionEvent event) throws SQLException {
		user.setId(usuarioSelecionado.getId());
		montaObjeto();
		montaOnjetoDaTabelaNosCampos();

		if (usuarioSelecionado.getId() != null) {
			btEditar.setDisable(true);
			actionExcluir = btExcluir.getOnAction();
			btExcluir.setText("Cancelar");
			btExcluir.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					retireDoEstadodeDeEdicao();
				}
			});
		}
	}

	private void retireDoEstadodeDeEdicao() {
		btExcluir.setText("Excluir");
		btExcluir.setOnAction(actionExcluir);
		btEditar.setDisable(true);
		btExcluir.setDisable(true);
		clear();
	}

	@FXML
	void excluir() throws SQLException {
		montaOnjetoDaTabelaNosCampos();

		boolean result = Utils.showConfirmationMessage(AlertType.CONFIRMATION, "Deseja mesmo excluir?");
		if (result == true) {
			this.service.ecluirUsuario(usuarioSelecionado);
			AdicioneNaGrid();
			usuarioSelecionado = new Usuario();
		} else {
			clear();
		}

		btExcluir.setDisable(true);
		btEditar.setDisable(true);
		clear();
	}

	@FXML
	void salvar(ActionEvent event) throws DadosInvalidosExeption, SQLException {

		if (!podeMontarUsuario())
			return;
		montaObjeto();

		service.salva(user);
		AdicioneNaGrid();
		retireDoEstadodeDeEdicao();
		btEditar.setDisable(false);
		btExcluir.setDisable(false);
		btExcluir.setDisable(true);
		btEditar.setDisable(true);
		clear();

	}

	public void selecao() {
		usuarioSelecionado = this.tblUsuarios.getSelectionModel().getSelectedItem();
		btExcluir.setDisable(false);
		btEditar.setDisable(false);
	}

	public void montaOnjetoDaTabelaNosCampos() {
		this.txtNome.setText(usuarioSelecionado.getNome());
		this.txtEmail.setText(usuarioSelecionado.getEmail());
		this.txtCelular.setText(usuarioSelecionado.getCelular());
		this.txtTelefone.setText(usuarioSelecionado.getTelefone());
		this.cbTipoPessoa.getSelectionModel().select(usuarioSelecionado.getTipoUsuario());
	}

	private void clear() {
		txtCelular.clear();
		txtEmail.clear();
		txtTelefone.clear();
		txtNome.clear();
		cbTipoPessoa.setValue(null);

		user = new Usuario();
		usuarioSelecionado = new Usuario();
	}

	private boolean podeMontarUsuario() {
		if (valideCampo(txtNome.getText().isEmpty() || txtNome.getText() == null, "Nome Ã© obrigatÃ³rio!"))
			return false;
		if (valideCampo(txtEmail.getText().isEmpty() || txtEmail.getText() == null, "E-mail Ã© obrigatÃ³rio!"))
			return false;
		if (valideCampo(cbTipoPessoa.getSelectionModel().isEmpty() || cbTipoPessoa.getSelectionModel() == null,
				"Selecione um Tipo de usuÃ¡rio!"))
			return false;

		return true;
	}

	private boolean valideCampo(boolean estaInconsistente, String mensagem) {
		if (estaInconsistente) {
			Utils.showMessage(AlertType.INFORMATION, mensagem);
			return true;
		}
		return false;
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
