package br.com.audiovisual.controller;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JDialog;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTextField;

import br.com.audiovisual.Utils.Utils;
import br.com.audiovisual.enumerador.TipoEquipamento;
import br.com.audiovisual.model.Equipamento;
import br.com.audiovisual.model.Marca;
import br.com.audiovisual.service.EquipamentoService;
import br.com.audiovisual.service.MarcaService;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.cell.PropertyValueFactory;

public class EquipamentoController implements Initializable {

	@FXML
	private JFXTextField txtNome, txtCodigo, txtDescricao;

	@FXML
	private JFXComboBox<TipoEquipamento> cbTipo;

	@FXML
	private JFXComboBox<Marca> cbMarca;

	@FXML
	private JFXButton btnSalvar, btnExcluir, btnEditar;

	@FXML
	private TableColumn<Equipamento, String> clnNome;

	@FXML
	private TableColumn<Equipamento, Integer> clnCodigo;

	@FXML
	private TableColumn<Equipamento, String> clnTipo;

	@FXML
	private TableColumn<Equipamento, String> clnMarca;

	@FXML
	private TableColumn<Equipamento, String> clnDescricao;

	@FXML
	private TableView<Equipamento> tblEquipamentos;

	private boolean ehEdicao;
	private EventHandler<ActionEvent> actionExcluir;

	private EquipamentoService serviceEquipamento = new EquipamentoService();
	private MarcaService serviceMarca = new MarcaService();

	private Equipamento equipamento = new Equipamento();
	private List<Equipamento> equipamentos = new ArrayList<>();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			carregueMarcas();
			carreguetipos();
			adicioneNaGrid();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void carregueMarcas() throws SQLException {
		List<Marca> marcas = serviceMarca.listar();
		cbMarca.getItems().add(null);
		cbMarca.getItems().addAll(marcas);
	}

	private void carreguetipos() {
		cbTipo.getItems().add(null);
		cbTipo.getItems().addAll(TipoEquipamento.values());
	}

	@FXML
	public void exclua(ActionEvent event) throws SQLException {
		if (!possuiEquipamentoSelecionado())
			return;

		boolean confirmou = Utils.showConfirmationMessage(AlertType.CONFIRMATION, "Deseja mesmo excluir?");
		if (!confirmou)
			return;

		serviceEquipamento.excluir(equipamento.getId());
		adicioneNaGrid();
	}

	private void retireDoEstadodeDeEdicao() {
		ehEdicao = false;
		btnExcluir.setText("Excluir");
		btnExcluir.setOnAction(actionExcluir);
		btnEditar.setDisable(false);
		txtCodigo.setDisable(false);
		clear();
	}

	@FXML
	public void graveEquipamento(ActionEvent event) throws SQLException {

		if (!podeMontarEquipamento())
			return;
		monteEquipamento();

		if (ehEdicao) {
			serviceEquipamento.editar(equipamento);
		} else {
			serviceEquipamento.salvar(equipamento);
		}

		adicioneNaGrid();
		clear();
		retireDoEstadodeDeEdicao();
	}

	@FXML
	public void prepareParaEditar(ActionEvent event) {
		if (!possuiEquipamentoSelecionado())
			return;

		exibaEquipamentoNosCampos();
		ehEdicao = true;
		btnExcluir.setText("Cancelar");

		actionExcluir = btnExcluir.getOnAction();
		btnExcluir.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				retireDoEstadodeDeEdicao();
			}
		});

		btnEditar.setDisable(true);
	}

	private void exibaEquipamentoNosCampos() {

		this.txtNome.setText(equipamento.getNome());
		this.txtCodigo.setText(String.valueOf(equipamento.getCodigo()));
		this.cbTipo.getSelectionModel().select(equipamento.getTipo());
		this.cbMarca.getSelectionModel().select(equipamento.getMarca());
		this.txtDescricao.setText(equipamento.getDescricao());
	}

	private boolean possuiEquipamentoSelecionado() {
		this.equipamento = tblEquipamentos.getSelectionModel().getSelectedItem();
		if (equipamento == null) {
			Utils.showMessage(AlertType.INFORMATION, "Selecione um equipamento");
			return false;
		}
		return true;
	}

	private void monteEquipamento() {
		if (ehEdicao) {
			Long id = tblEquipamentos.getSelectionModel().getSelectedItem().getId();
			equipamento.setId(id);
		}

		equipamento.setCodigo(Integer.parseInt(txtCodigo.getText()));
		equipamento.setNome(txtNome.getText());
		equipamento.setTipo(cbTipo.getSelectionModel().getSelectedItem().getId());
		equipamento.setMarca(cbMarca.getSelectionModel().getSelectedItem());
		equipamento.setDescricao(txtDescricao.getText());
	}

	private boolean podeMontarEquipamento() {

		if (valideCampo(txtCodigo.getText().isEmpty(), "Código é obrigatório!")) return false;
		if (valideCampo(txtNome.getText().isEmpty(), "Nome é obrigatório!")) return false;
		if (valideCampo(cbTipo.getSelectionModel().getSelectedItem() == null, "Selecione um Tipo de Equipamento!")) return false;
		if (valideCampo(cbMarca.getSelectionModel().getSelectedItem() == null, "Selecione uma marca!")) return false;

		return true;
	}

	private boolean valideCampo(boolean ehCondicaoValida, String mensagem) {
		if (!ehCondicaoValida) {
			Utils.showMessage(AlertType.INFORMATION, mensagem);
		}
		return true;
	}

	private void adicioneNaGrid() throws SQLException {
		equipamentos = serviceEquipamento.listar();
		clnCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
		clnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		clnTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
		clnMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
		clnDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));

		tblEquipamentos.setItems(FXCollections.observableArrayList(equipamentos));
	}

	private void clear() {
		txtNome.clear();
		txtCodigo.clear();
		cbTipo.setValue(null);
		cbMarca.setValue(null);
		txtDescricao.clear();
		equipamento = new Equipamento();
	}
}