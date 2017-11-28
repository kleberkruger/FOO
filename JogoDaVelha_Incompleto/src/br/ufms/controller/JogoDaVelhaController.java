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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * Herde a classe JogoDaVelha.
 *
 * @author Kleber Kruger
 */
public class JogoDaVelhaController implements Initializable {

    public static final InputStream FXML = JogoDaVelhaController.class.getResourceAsStream(
            "/br/ufms/view/fxml/JogoDaVelha.fxml");

//    @FXML
//    private TextField placarX;
//    @FXML
//    private TextField placarO;

    @FXML
    private Label lblMsg;

    // Colocar os botões aqui...
    private final Button[] botoes;

    public JogoDaVelhaController() {
//        super(true); // caso passe o valor "false", o jogará em modo "dois jogadores".
        this.botoes = new Button[9];
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        this.botoes[0] = btn1;
//        this.botoes[1] = btn2;
//        this.botoes[2] = btn3;
//        this.botoes[3] = btn4;
//        this.botoes[4] = btn5;
//        this.botoes[5] = btn6;
//        this.botoes[6] = btn7;
//        this.botoes[7] = btn8;
//        this.botoes[8] = btn9;

        // Faça os PropertyBinds dos placares...
        // Faça os PropertyBinds dos botões...
        for (int i = 0; i < 9; i++) {
            // Vincule e crie as ações e os eventos dos botões...
        }
    }

    private void adicionarEstiloNoBotao(Button btn) {
        btn.textProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue != null) {
                btn.getStyleClass().setAll("button", newValue.equalsIgnoreCase("x") ? "jogador-x"
                        : newValue.equalsIgnoreCase("o") ? "jogador-o" : null);
            }
        });
    }

    /**
     * Reinicia a partida esperando o tempo de 1500 ms. Antes disso, é precisso fazer unbind da
     * propriedade disableProperty e deixá-los desabilitado (setDisable(true)).
     *
     * Ao concluir o tempo de espera, reiniciar o jogo da velha (super.reiniciar()), seta o texto
     * dos botões para null e faz o seguinte bind:
     *
     * btn.disableProperty().bind(btn.textProperty().isNotNull().and(btn.textProperty().isNotEmpty()));
     */
    private void reiniciarPartida() {
    }
}
