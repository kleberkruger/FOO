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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TitledPane;

/**
 * FXML Controller class
 *
 * @author Kleber Kruger
 */
public class MenuCorrentistaController implements Initializable {

    @FXML
    private TitledPane deposito;
    @FXML
    private TitledPane saque;
    @FXML
    private TitledPane transferencia;
    
    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            FXMLLoader loader1 = new FXMLLoader();
            Parent form1 = (Parent) loader1.load(getClass().getClassLoader().getResourceAsStream(
                    "br/ufms/banking/view/fxml/Deposito.fxml"));
            deposito.setContent(form1);

            FXMLLoader loader2 = new FXMLLoader();
            Parent form2 = (Parent) loader2.load(getClass().getClassLoader().getResourceAsStream(
                    "br/ufms/banking/view/fxml/Saque.fxml"));
            saque.setContent(form2);
            
            FXMLLoader loader3 = new FXMLLoader();
            Parent form3 = (Parent) loader3.load(getClass().getClassLoader().getResourceAsStream(
                    "br/ufms/banking/view/fxml/Transferencia.fxml"));
            transferencia.setContent(form3);
            
        } catch (IOException ex) {
            Logger.getLogger(MenuCorrentistaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
