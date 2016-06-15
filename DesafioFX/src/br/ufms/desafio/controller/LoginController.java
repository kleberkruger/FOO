/*
 * Copyright (C) 2016 kleberkruger
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
package br.ufms.desafio.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author kleberkruger
 */
public class LoginController implements Initializable {

    @FXML
    private Button fxEntrar;

    @FXML
    private TextField fxUsuario;

    @FXML
    private PasswordField fxSenha;

    @FXML
    private Label fxMensagem;

    @FXML
    private Hyperlink fxEsqueciSenha;

    @FXML
    private ProgressIndicator fxProgresso;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void fxEntrarOnAction(ActionEvent event) {
//        mostrarMensagem("Aparecer");
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException ex) {
//            mostrarMensagem("Apagar");
//        }
//        Task<Integer> task = new Task<Integer>() {
//            
//            @Override
//            protected Integer call() throws Exception {
//                int iterations;
//                for (iterations = 0; iterations < 100000; iterations++) {
//                    if (isCancelled()) {
//                        break;
//                    }
//                    System.out.println("Iteration " + iterations);
//                }
//                return iterations;
//            }
//        };
//        
//        task.run();
        
        if (fxUsuario.getText().isEmpty() || fxSenha.getText().isEmpty()) {
            mostrarMensagem("Digite usuário e senha.");
        } else if (fxUsuario.getText().equals("admin") && fxSenha.getText().equals("admin")) {

            try {

                FXMLLoader loader = new FXMLLoader();
                Parent root = (Parent) loader.load(getClass().getClassLoader().getResourceAsStream(
                        "br/ufms/desafio/view/fxml/Principal.fxml"));
                Scene scene = new Scene(root);

                Stage janelaAtual = (Stage) ((Node) event.getSource()).getScene().getWindow();
                janelaAtual.setScene(scene);
                janelaAtual.centerOnScreen();

            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }

        } else {
            mostrarMensagem("Usuário e senha incorretos.");
        }
    }

    private void mostrarMensagem(String msg) {
        fxProgresso.setVisible(false);
        fxEsqueciSenha.setVisible(false);
        fxMensagem.setVisible(true);
        fxMensagem.setText(msg);
    }
}
