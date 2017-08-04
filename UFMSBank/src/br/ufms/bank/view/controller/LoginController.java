/*
 * Copyright (C) 2017 Kleber Kruger
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package br.ufms.bank.view.controller;

import br.ufms.bank.model.Usuario;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Kleber Kruger
 */
public class LoginController implements Initializable {

    private static final int TAMANHO_MIN_USUARIO = 3;
    private static final int TAMANHO_MIN_SENHA = 3;

    @FXML
    private TextField usuario;

    @FXML
    private PasswordField senha;

    @FXML
    private CheckBox lembrarUsuario;

    @FXML
    private Label mensagem;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        usuario.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (newValue == true) {
                usuario.getStyleClass().remove("error");
//                mensagem.setText("");
            } else {
                try {
                    Usuario.validarUsuario(usuario.getText());
                } catch (IllegalArgumentException ex) {
                    usuario.getStyleClass().add("error");
                    mensagem.setText(ex.getMessage());
                }
            }
        });

        senha.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (newValue == true) {
                senha.getStyleClass().remove("error");
//                mensagem.setText("");
            } else {
                try {
                    Usuario.validarSenha(senha.getText());
                } catch (IllegalArgumentException ex) {
                    senha.getStyleClass().add("error");
                    mensagem.setText(ex.getMessage());
                }
            }
        });
    }

    @FXML
    public void doLogin(ActionEvent event) {
        if (usuario.getText().trim().isEmpty() || senha.getText().isEmpty()) {
            mensagem.setText("Informe usuário e senha.");
        }
    }

}
