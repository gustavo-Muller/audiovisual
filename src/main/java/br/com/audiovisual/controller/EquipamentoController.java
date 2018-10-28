package br.com.audiovisual.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import br.com.audiovisual.model.Equipamento;
import br.com.audiovisual.model.Marca;
import br.com.audiovisual.model.Tipo;
import br.com.audiovisual.service.EquipamentoService;
import br.com.audiovisual.service.MarcaService;
import br.com.audiovisual.service.TipoService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class EquipamentoController implements Initializable {

    @FXML
    private JFXTextField txtNome, txtCodigo;

    @FXML
    private JFXTextArea txtDescricao;

    @FXML
    private JFXComboBox<Tipo> cbTipo;

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
   
    private EquipamentoService serviceEquipamento = new EquipamentoService();
    private MarcaService serviceMarca = new MarcaService();
    private TipoService serviceTipo = new TipoService();
    
    private Equipamento equipamento = new Equipamento();
    private List<Equipamento> equipamentos = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
			carregueMarcas();
			carreguetipos();
			adicioneNaGrid();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    private void carregueMarcas() throws SQLException {
    	List<Marca> marcas = serviceMarca.listar();
    	cbMarca.getItems().addAll(marcas);
    }
    
    private void carreguetipos() throws SQLException {
    	List<Tipo> tipos = serviceTipo.listar();
    	cbTipo.getItems().addAll(tipos);
    }

    @FXML
    void excluir(ActionEvent event) {

    }

    @FXML
    void salvar(ActionEvent event) throws SQLException {
    	monteObjeto();
    	serviceEquipamento.salvar(equipamento);
    	adicioneNaGrid();
    	
    	
    }
    
    @FXML
    void editar(ActionEvent event) {
    	
    }

    public void monteObjeto() {
    	equipamento.setCodigo(Integer.parseInt(txtCodigo.getText()));
    	equipamento.setNome(txtNome.getText());
    	equipamento.setTipo(cbTipo.getSelectionModel().getSelectedItem());
    	equipamento.setMarca(cbMarca.getSelectionModel().getSelectedItem());
    	equipamento.setDescricao(txtDescricao.getText());
    }
    
    public void adicioneNaGrid() throws SQLException {
    	equipamentos = serviceEquipamento.listar();
    	clnCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
    	clnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
    	clnTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
    	clnMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
    	clnDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));

    	tblEquipamentos.setItems(FXCollections.observableArrayList(equipamentos));
    }

}