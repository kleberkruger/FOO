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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author Kleber Kruger
 */
public class FormularioContaController implements Initializable {

    @FXML
    private ScrollPane formularioConta;
    
    @FXML
    private StackPane dadosContaContent;

    private final BooleanProperty smallScreenProperty;

    public FormularioContaController() {
        smallScreenProperty = new SimpleBooleanProperty(false);
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            FXMLLoader loader = new FXMLLoader();
            Parent formulario = (Parent) loader.load(getClass().getClassLoader().getResourceAsStream(
                    "br/ufms/banking/view/fxml/GridPessoaFisica.fxml"));

            dadosContaContent.getChildren().add(formulario);

        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        
//        doResponsive();
    }

    private void doResponsive() {
//        smallScreenProperty.bind(formularioConta.widthProperty().lessThan(400));
//        smallScreenProperty.addListener((observable, oldValue, newValue) -> {
//            if (newValue == true) {
//                changeToSmallLayout();
//            } else {
//                changeToLargeLayout();
//            }
//        });
//
        formularioConta.widthProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            if (newValue.doubleValue() <= 600) {
                changeToSmallLayout();
            } else {
                changeToLargeLayout();
            }
        });
    }

    private void changeToSmallLayout() {
//
//        System.out.println("Small Layout");
//
//        GridPane.setConstraints(cpf, 0, 1);
//        GridPane.setConstraints(cpfError, 1, 1);
//        GridPane.setConstraints(telefonePrincipal, 0, 2);
//        GridPane.setConstraints(telefonePrincipalError, 1, 2);
//        GridPane.setConstraints(telefoneSecundario, 0, 3);
//        GridPane.setConstraints(telefoneSecundarioError, 1, 3);
//        GridPane.setConstraints(usuario, 0, 4);
//        GridPane.setConstraints(usuarioError, 1, 4);
//        GridPane.setConstraints(senha, 0, 5);
//        GridPane.setConstraints(senhaError, 1, 5);
//        GridPane.setConstraints(senhaConfirmacao, 0, 6);
//        GridPane.setConstraints(senhaConfirmacaoError, 1, 6);
//        GridPane.setConstraints(forcaSenhaPane, 0, 7);
//
//        GridPane.setConstraints(contaPoupancaPane, 0, 1);
//        GridPane.setConstraints(contaPoupancaPaneError, 1, 1);
//
//        gridDadosCorrentista.getColumnConstraints().remove(3);
//        gridDadosCorrentista.getColumnConstraints().remove(2);
    }

    private void changeToLargeLayout() {
//
//        System.out.println("Large Layout");
//
//        GridPane.setConstraints(cpf, 2, 0);
//        GridPane.setConstraints(cpfError, 3, 0);
//        GridPane.setConstraints(telefonePrincipal, 0, 1);
//        GridPane.setConstraints(telefonePrincipalError, 1, 1);
//        GridPane.setConstraints(telefoneSecundario, 2, 1);
//        GridPane.setConstraints(telefoneSecundarioError, 3, 1);
//        GridPane.setConstraints(usuario, 0, 2);
//        GridPane.setConstraints(usuarioError, 1, 2);
//        GridPane.setConstraints(senha, 2, 2);
//        GridPane.setConstraints(senhaError, 3, 2);
//        GridPane.setConstraints(senhaConfirmacao, 2, 3);
//        GridPane.setConstraints(senhaConfirmacaoError, 3, 3);
//        GridPane.setConstraints(forcaSenhaPane, 0, 3);
//
//        GridPane.setConstraints(contaPoupancaPane, 2, 0);
//        GridPane.setConstraints(contaPoupancaPaneError, 3, 0);
    }

}
