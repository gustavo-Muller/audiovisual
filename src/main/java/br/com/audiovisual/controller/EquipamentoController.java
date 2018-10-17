package br.com.audiovisual.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import br.com.audiovisual.model.Equipamento;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;


public class EquipamentoController implements Initializable{

	@FXML
	private JFXTextField txtNome;

	@FXML
	private JFXTextField txtCodigo;

	@FXML
	private JFXTextField txtMarca;

	@FXML
	private JFXTextArea txtDescricao;

	@FXML
	private JFXButton btnSalvar;

	@FXML
	private JFXButton btnLimpar;

	@FXML
	private JFXButton btnListar;
	
	public Equipamento equipamento = new Equipamento();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		txtNome.setText(equipamento.getNome());
	}
	
	@FXML
	void limpar(ActionEvent event) {

	}

	@FXML
	void salvar(ActionEvent event) {

	}

}
