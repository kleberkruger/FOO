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

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Kleber Kruger
 */
public class FormDepositoController implements Initializable {

    @FXML
    private TextField nomeDepositanteText;
    @FXML
    private TextField foneDepositanteText;
    @FXML
    private TextField operacaoText;
    @FXML
    private TextField numeroContaText;
    @FXML
    private TextField nomeFavorecidoText;
    @FXML
    private TextField valorText;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void depositar(ActionEvent event) {
//        conta.depositar(Double.parseDouble(valorText.getText()), nomeDepositanteText.getText());
    }
}
