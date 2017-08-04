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

    @FXML
    private TextField usuario;
    @FXML
    private Label usuarioError;

    @FXML
    private PasswordField senha;
    @FXML
    private Label senhaError;

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
                usuarioError.setVisible(false);
                mensagem.setText("");
            } else {
                try {
                    Usuario.validarUsuario(usuario.getText());
                } catch (IllegalArgumentException ex) {
                    usuario.getStyleClass().add("error");
                    usuarioError.setVisible(true);
                    usuarioError.getTooltip().setText(ex.getMessage());
                }
            }
        });

        senha.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (newValue == true) {
                senha.getStyleClass().remove("error");
                senhaError.setVisible(false);
                mensagem.setText("");
            } else {
                try {
                    Usuario.validarSenha(senha.getText());
                } catch (IllegalArgumentException ex) {
                    senha.getStyleClass().add("error");
                    senhaError.setVisible(true);
                    senhaError.getTooltip().setText(ex.getMessage());
                }
            }
        });
    }

    @FXML
    public void doLogin(ActionEvent event) {
        try {
            Usuario.validarUsuario(usuario.getText());
            Usuario.validarSenha(senha.getText());
        } catch (IllegalArgumentException ex) {
            mensagem.setText(ex.getMessage());
        }
    }

}
