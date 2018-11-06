package br.com.audiovisual.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import br.com.audiovisual.Exeption.DadosInvalidosExeption;
import br.com.audiovisual.Utils.Utils;
import br.com.audiovisual.model.Marca;
import br.com.audiovisual.service.MarcaService;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class MarcaController implements Initializable {

	@FXML
	private JFXTextField txtNomeMarca;

	@FXML
	private JFXButton btSalvar, btEditar, btExcluir;

	@FXML
	private TableView<Marca> tbMarca;

	@FXML
	private TableColumn<Marca, String> tbClNome;

	@FXML
	private TableColumn<Marca, String> tbClDescricao;

	@FXML
	private JFXTextArea txtAreaDescricao;

	private EventHandler<ActionEvent> actionExcluir;
	private boolean ehEdicao;
	private Marca marca;
	private MarcaService service = new MarcaService();
	List<Marca> marcas = new ArrayList<>();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			atualizeGrid();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void editar(ActionEvent event) {
		if (!possuiEquipamentoSelecionado()) {
			return;
		}

		exibaMarcaNosCampos();

		ehEdicao = true;
		btEditar.setDisable(true);
		btExcluir.setText("Cancelar");

		actionExcluir = btExcluir.getOnAction();
		btExcluir.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				retireDoEstadodeDeEdicao();
			}
		});

	}

	private void retireDoEstadodeDeEdicao() {
		btEditar.setDisable(false);
		btExcluir.setOnAction(actionExcluir);
		btExcluir.setText("Excluir");
		clear();
	}

	private void exibaMarcaNosCampos() {
		txtNomeMarca.setText(marca.getNome());
		txtAreaDescricao.setText(marca.getDescricao().isEmpty() ? "" : marca.getDescricao());
	}

	@FXML
	void salvar() throws SQLException, DadosInvalidosExeption {
		if (!podeMontarMarca())
			return;

		if (ehEdicao) {
			marca = tbMarca.getSelectionModel().getSelectedItem();
			service.editar(marca);
			retireDoEstadodeDeEdicao();
		} else {
			monteMarca();
			service.salvar(marca);
		}

		clear();
		atualizeGrid();
	}

	@FXML
	void excluir() {
		if (!possuiEquipamentoSelecionado())
			return;

		boolean confirmou = Utils.showConfirmationMessage(AlertType.CONFIRMATION, "Deseja mesmo excluir?");
		if (!confirmou)
			return;

		marca = tbMarca.getSelectionModel().getSelectedItem();

		try {

			service.excluir(marca.getId());
			atualizeGrid();

		} catch (SQLException | DadosInvalidosExeption e) {

			e.printStackTrace();

		}
	}

	private boolean possuiEquipamentoSelecionado() {
		this.marca = tbMarca.getSelectionModel().getSelectedItem();
		if (marca == null) {
			Utils.showMessage(AlertType.INFORMATION, "Selecione um equipamento");
			return false;
		}
		return true;
	}

	private void atualizeGrid() throws SQLException {
		marcas = service.listar();
		tbClNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		tbClDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));

		tbMarca.setItems(FXCollections.observableArrayList(marcas));

	}

	public boolean podeMontarMarca() {
		if (txtNomeMarca.getText().isEmpty() || txtNomeMarca.getText() == null) {
			Utils.showMessage(AlertType.INFORMATION, "Nome e um campo de Preenchimento OBRIGATÓRIRO!");
			return false;
		}
		return true;
	}

	public void clear() {
		txtNomeMarca.setText(null);
		txtAreaDescricao.setText(null);
	}

	private void monteMarca() {
		marca = new Marca();
		marca.setNome(txtNomeMarca.getText());
		marca.setDescricao(txtAreaDescricao.getText());
	}

}
