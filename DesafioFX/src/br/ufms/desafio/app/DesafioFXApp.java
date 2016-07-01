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
package br.ufms.desafio.app;

import br.ufms.desafio.model.bean.Municipio;
import br.ufms.desafio.model.dao.MunicipioDAO;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author kleberkruger
 */
public class DesafioFXApp extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        
        FXMLLoader loader = new FXMLLoader();

        // Carrega arquivo fxml do pacote view.fxml
        Parent root = (Parent) loader.load(getClass().getClassLoader().getResourceAsStream(
                "br/ufms/desafio/view/fxml/Login.fxml"));
        
        Scene scene = new Scene(root);

        // Adiciona o arquivo CSS para a personalização desta cena
        scene.getStylesheets().add(getClass().getClassLoader().getResource(
                "br/ufms/desafio/view/css/desafio.css").toExternalForm());

        // Altera o título da janela para "DesafioFX", define a cena e a exibe na tela
        stage.setTitle("DesafioFX");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Municipio municipio = new Municipio();
        municipio.setCodigoIBGE(8l);
        municipio.setNome("Pedro Gomes");
        municipio.setUF("MS");
        
        MunicipioDAO dao = new MunicipioDAO();
        try {
            System.out.println(dao.getAll().size());
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
}
