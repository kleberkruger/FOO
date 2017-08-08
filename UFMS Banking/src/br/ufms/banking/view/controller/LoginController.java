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
package br.ufms.banking.view.controller;

import br.ufms.banking.util.Validador;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

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

    @FXML
    private Label capsLockOn;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        usuario.textProperty().addListener((observable, oldValue, newValue) -> {
            usuario.setText(newValue.replaceAll("[^A-Za-z0-9]", "").toLowerCase());
        });

        usuario.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == true) {
                usuario.getStyleClass().remove("error");
                usuarioError.setVisible(false);
                mensagem.setText("");
            } else {
                try {
                    Validador.validarUsuario(usuario.getText(), null);
                } catch (IllegalArgumentException ex) {
                    usuario.getStyleClass().add("error");
                    usuarioError.setVisible(true);
                    usuarioError.getTooltip().setText(ex.getMessage());
                }
            }
        });

        senha.setOnKeyPressed((KeyEvent event) -> {
            capsLockOn.setVisible(Toolkit.getDefaultToolkit().getLockingKeyState(java.awt.event.KeyEvent.VK_CAPS_LOCK));
        });

        senha.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == true) {
                senha.getStyleClass().remove("error");
                senhaError.setVisible(false);
                mensagem.setText("");
            } else {
                try {
                    Validador.validarSenha(senha.getText());
                } catch (IllegalArgumentException ex) {
                    senha.getStyleClass().add("error");
                    senhaError.setVisible(true);
                    senhaError.getTooltip().setText(ex.getMessage());
                }
            }
        });
    }

    @FXML
    public void doLogin(ActionEvent event) throws IOException {
        try {
            Validador.validarUsuario(usuario.getText());
            Validador.validarSenha(senha.getText());

            if (usuario.getText().equalsIgnoreCase("admin")
                    && senha.getText().equalsIgnoreCase("admin")) {

                FXMLLoader loader = new FXMLLoader();
                Parent root = (Parent) loader.load(getClass().getClassLoader().getResourceAsStream(
                        "br/ufms/banking/view/fxml/MenuBancario.fxml"));

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.centerOnScreen();
            }

        } catch (IllegalArgumentException ex) {
            mensagem.setText(ex.getMessage());
        }
    }

}
