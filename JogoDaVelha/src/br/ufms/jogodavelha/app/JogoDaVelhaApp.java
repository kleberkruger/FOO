/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufms.jogodavelha.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author kleberkruger
 */
public class JogoDaVelhaApp extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(this.getClass().getClassLoader().getResource(
                "br/ufms/jogodavelha/view/fxml/JogoDaVelha.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("br/ufms/jogodavelha/view/css/style.css");
        
        stage.setTitle("Jogo da Velha");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
