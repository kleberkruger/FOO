/*
 * Copyright (C) 2017 Universidade Federal de Mato Grosso do Sul
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
package br.ufms.controller;

import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.converter.NumberStringConverter;

/**
 *
 * @author Kleber Kruger
 */
public class JogoDaVelhaController extends JogoDaVelha implements Initializable {

    public static final InputStream FXML = JogoDaVelhaController.class.getResourceAsStream(
            "/br/ufms/view/fxml/JogoDaVelha.fxml");

    @FXML
    private TextField placarX;
    @FXML
    private TextField placarO;
    
    @FXML
    private Label lblMsg;

    @FXML
    private Button btn1;
    @FXML
    private Button btn2;
    @FXML
    private Button btn3;
    @FXML
    private Button btn4;
    @FXML
    private Button btn5;
    @FXML
    private Button btn6;
    @FXML
    private Button btn7;
    @FXML
    private Button btn8;
    @FXML
    private Button btn9;

    private final Button[] botoes;

    public JogoDaVelhaController() {
        super(true);
        this.botoes = new Button[9];
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        this.placarX.textProperty().bindBidirectional(getPlacarJogador1(), new NumberStringConverter());
        this.placarO.textProperty().bindBidirectional(getPlacarJogador2(), new NumberStringConverter());

        this.botoes[0] = btn1;
        this.botoes[1] = btn2;
        this.botoes[2] = btn3;
        this.botoes[3] = btn4;
        this.botoes[4] = btn5;
        this.botoes[5] = btn6;
        this.botoes[6] = btn7;
        this.botoes[7] = btn8;
        this.botoes[8] = btn9;

        for (int i = 0; i < 9; i++) {
            botoes[i].textProperty().bindBidirectional(getCampos()[i]);

            final Button btn = botoes[i];
            final int lin = i / 3;
            final int col = i % 3;
            btn.setOnAction((ActionEvent event) -> {
                super.jogar(lin, col);
            });
            btn.disableProperty().bind(btn.textProperty().isNotNull().and(btn.textProperty().isNotEmpty()));
            btn.textProperty().addListener((obs, oldValue, newValue) -> {
                if (newValue != null) {
                    btn.getStyleClass().setAll("button", newValue.equalsIgnoreCase("x") ? "jogador-x"
                            : newValue.equalsIgnoreCase("o") ? "jogador-o" : null);
                }
            });
        }
    }

    private void reiniciarPartida() {
        for (Button btn : botoes) {
            btn.disableProperty().unbind();
            btn.setDisable(true);
        }
        Task<Void> sleeper = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                Thread.sleep(1500);
                return null;
            }
        };
        sleeper.setOnSucceeded((WorkerStateEvent event) -> {
            super.reiniciar();
            for (Button btn : botoes) {
                lblMsg.setText(null);
                btn.disableProperty().bind(btn.textProperty().isNotNull().and(btn.textProperty().isNotEmpty()));
            }
        });
        new Thread(sleeper).start();
    }

    @Override
    public void jogadorGanhou(String jogador, Point2D[] pontos) {
        
        for (Button btn : botoes) {
            btn.getStyleClass().setAll("button");
        }
        for (Point2D p : pontos) {
            int index = 3 * (int) p.getX() + (int) p.getY();
            botoes[index].getStyleClass().setAll("button", "ganhou");
        }
        
        if (jogador.equalsIgnoreCase("o")) {
            lblMsg.setText("Você perdeu, mané!");
        } else {
            lblMsg.setText("Parabéns, você ganhou!");
        }
        
        reiniciarPartida();
    }

    @Override
    public void terminouEmVelha() {
        
        for (Button btn : botoes) {
            btn.getStyleClass().setAll("button");
        }
        
        reiniciarPartida();
    }

}
