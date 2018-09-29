package br.com.audiovisual.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class EquipamentoController {

	@FXML
	private JFXTextField txtNome;

	@FXML
	private JFXTextField txtCodigo;

	@FXML
	private JFXTextField txtTipo;

	@FXML
	private JFXTextField txtMarca;

	@FXML
	private JFXTextArea txtDescricao;

	@FXML
	private JFXButton btnSalvar;

	@FXML
	private JFXButton btnLimpar;

	@FXML
	void limpar(ActionEvent event) {

	}

	@FXML
	void salvar(ActionEvent event) {

	}

}
