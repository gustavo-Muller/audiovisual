package br.com.audiovisual.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import br.com.audiovisual.model.Equipamento;
import br.com.audiovisual.model.Marca;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class EquipamentoController implements Initializable {

    @FXML
    private JFXTextField txtNome;

    @FXML
    private JFXTextField txtCodigo;

    @FXML
    private JFXTextArea txtDescricao;

    @FXML
    private JFXComboBox<?> cbTipo;

    @FXML
    private JFXComboBox<Marca> cbMarca;

    @FXML
    private JFXButton btnSalvar;

    @FXML
    private JFXButton btnExcluir;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Altera alguma coisa
    }

    @FXML
    void excluir(ActionEvent event) {

    }

    @FXML
    void salvar(ActionEvent event) {

    }

    public void monteObjetoNaTela(Equipamento equipamento) {
        txtCodigo.setText(String.valueOf(equipamento.getCodigo()));
        txtNome.setText(equipamento.getNome());
        txtDescricao.setText(equipamento.getDescricao());
    }

}