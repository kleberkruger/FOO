/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufms.jogodavelha.controller;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;

/**
 *
 * @author Kleber Kruger
 */
public class JogoDaVelhaController extends JogoDaVelha implements Initializable {

    @FXML
    private TextField placarX;
    @FXML
    private TextField placarO;

    @FXML
    private ToggleButton btn1;
    @FXML
    private ToggleButton btn2;
    @FXML
    private ToggleButton btn3;
    @FXML
    private ToggleButton btn4;
    @FXML
    private ToggleButton btn5;
    @FXML
    private ToggleButton btn6;
    @FXML
    private ToggleButton btn7;
    @FXML
    private ToggleButton btn8;
    @FXML
    private ToggleButton btn9;

    private final Map<ToggleButton, Point2D> mapaBotoes;
    private final ToggleButton[][] arrayBotoes;

    private String jogadorClass;

    public JogoDaVelhaController() {
        this.mapaBotoes = new HashMap<>();
        this.arrayBotoes = new ToggleButton[3][3];
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mapaBotoes.put(btn1, new Point2D(0, 0));
        mapaBotoes.put(btn2, new Point2D(0, 1));
        mapaBotoes.put(btn3, new Point2D(0, 2));
        mapaBotoes.put(btn4, new Point2D(1, 0));
        mapaBotoes.put(btn5, new Point2D(1, 1));
        mapaBotoes.put(btn6, new Point2D(1, 2));
        mapaBotoes.put(btn7, new Point2D(2, 0));
        mapaBotoes.put(btn8, new Point2D(2, 1));
        mapaBotoes.put(btn9, new Point2D(2, 2));

        arrayBotoes[0][0] = btn1;
        arrayBotoes[0][1] = btn2;
        arrayBotoes[0][2] = btn3;
        arrayBotoes[1][0] = btn4;
        arrayBotoes[1][1] = btn5;
        arrayBotoes[1][2] = btn6;
        arrayBotoes[2][0] = btn7;
        arrayBotoes[2][1] = btn8;
        arrayBotoes[2][2] = btn9;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                arrayBotoes[i][j].disableProperty().bind(arrayBotoes[i][j].textProperty().isNotEmpty());
            }
        }
    }

    @FXML
    private void jogar(ActionEvent event) {
        ToggleButton btn = (ToggleButton) event.getSource();
        btn.getStyleClass().add(jogadorClass);

        Point2D p = mapaBotoes.get(btn);
        int lin = (int) p.getX();
        int col = (int) p.getY();

        super.jogar(lin, col);
    }

    @Override
    protected void jogoReiniciado() {
//        Task<Void> sleeper = new Task<Void>() {
//            @Override
//            protected Void call() throws Exception {
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                }
//                return null;
//            }
//        };
//        sleeper.setOnSucceeded((WorkerStateEvent event) -> {
//            for (int i = 0; i < 3; i++) {
//                for (int j = 0; j < 3; j++) {
//                    arrayBotoes[i][j].setSelected(false);
//                    arrayBotoes[i][j].setText("");
//                }
//            }
//        });
//
//        new Thread(sleeper).start();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                arrayBotoes[i][j].setSelected(false);
                arrayBotoes[i][j].setText("");
            }
        }
    }

    @Override
    protected void matrizAlterada(char[][] matrizAnterior, char[][] matrizAtual) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (matrizAtual[i][j] != ' ') {
                    ToggleButton btn = arrayBotoes[i][j];
                    btn.setText(String.valueOf(matrizAtual[i][j]));
                    System.out.println(btn.getText());
                    btn.getStyleClass().add(btn.getText().equals("x") ? "jogador-1" : "jogador-2");
                } else {
                    arrayBotoes[i][j].setText("");
                }
//                arrayBotoes[i][j].setText(String.valueOf(matrizAtual[i][j]));
            }
        }
    }

    @Override
    protected void placarAlterado(int pontosX, int pontosO) {
        placarX.setText(String.valueOf(pontosX));
        placarO.setText(String.valueOf(pontosO));
    }

    @Override
    protected void terminouEmVelha() {
        jogoReiniciado();
    }

    @Override
    protected void jogadorGanhou(char jogador) {
        jogoReiniciado();
    }

}
