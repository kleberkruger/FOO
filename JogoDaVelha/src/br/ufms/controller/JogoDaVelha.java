/*
 * Copyright (C) 2017 kleberkruger
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

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

/**
 *
 * @author kleberkruger
 */
public abstract class JogoDaVelha {

    private final IntegerProperty placarJogador1 = new SimpleIntegerProperty();
    private final IntegerProperty placarJogador2 = new SimpleIntegerProperty();

    private final StringProperty campo1 = new SimpleStringProperty();
    private final StringProperty campo2 = new SimpleStringProperty();
    private final StringProperty campo3 = new SimpleStringProperty();
    private final StringProperty campo4 = new SimpleStringProperty();
    private final StringProperty campo5 = new SimpleStringProperty();
    private final StringProperty campo6 = new SimpleStringProperty();
    private final StringProperty campo7 = new SimpleStringProperty();
    private final StringProperty campo8 = new SimpleStringProperty();
    private final StringProperty campo9 = new SimpleStringProperty();

    private final StringProperty[] campos = new StringProperty[9];
    private final StringProperty matriz[][] = new StringProperty[3][3];

    private final StringProperty jogadorAtual = new SimpleStringProperty();

    private final boolean computadorJogando;
    private final JogadorVirtual jogadorVirtual;

    private final ProcessarJogada processarJogada = new ProcessarJogada();

    public JogoDaVelha(boolean computadorJogando) {
        this.computadorJogando = computadorJogando;
        this.jogadorVirtual = computadorJogando ? new JogadorVirtual("o") : null;

        this.campos[0] = campo1;
        this.campos[1] = campo2;
        this.campos[2] = campo3;
        this.campos[3] = campo4;
        this.campos[4] = campo5;
        this.campos[5] = campo6;
        this.campos[6] = campo7;
        this.campos[7] = campo8;
        this.campos[8] = campo9;

        this.matriz[0][0] = campo1;
        this.matriz[0][1] = campo2;
        this.matriz[0][2] = campo3;
        this.matriz[1][0] = campo4;
        this.matriz[1][1] = campo5;
        this.matriz[1][2] = campo6;
        this.matriz[2][0] = campo7;
        this.matriz[2][1] = campo8;
        this.matriz[2][2] = campo9;

        for (StringProperty property : campos) {
            property.addListener(processarJogada);
        }

        novoJogo();
    }

    private void novoJogo() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                matriz[i][j].setValue(null);
            }
        }
        jogadorAtual.set("x");
    }

    /**
     * Verifica se a posição lin,col já está ocupada com X ou O.
     *
     * @param lin é a linha da matriz
     * @param col é a coluna da matriz
     * @return verdadeiro se a matriz está preenchida, falso caso contrário.
     */
    boolean isOcupado(int lin, int col) {
        return !matriz[lin][col].getValueSafe().isEmpty();
    }

    /**
     * Verifica se a posição lin,col já está ocupada com X ou O.
     *
     * @param lin é a linha da matriz
     * @param col é a coluna da matriz
     * @return verdadeiro se a matriz está preenchida, falso caso contrário.
     */
    boolean isOcupado(String matriz[][], int lin, int col) {
        return matriz[lin][col] != null && !matriz[lin][col].isEmpty();
    }

    /**
     * Reinicia a partida.
     */
    public void reiniciar() {
        novoJogo();
    }

    public synchronized void jogar(int posicao) {
        jogar(posicao / 3, posicao % 3);
    }

    public synchronized void jogar(int lin, int col) {
        matriz[lin][col].setValue(jogadorAtual.get());
        trocarJogador();

        if (computadorJogando) {

            for (StringProperty property : campos) {
                property.removeListener(processarJogada);
            }

            int pos = jogadorVirtual.jogar();
            
            for (StringProperty property : campos) {
                property.addListener(processarJogada);
            }
            
            if (pos != -1) {
                int l = pos / 3;
                int c = pos % 3;
                matriz[l][c].setValue(jogadorVirtual.simbolo);
                trocarJogador();
            }
        }
    }

    public class ProcessarJogada implements ChangeListener<String> {

        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            processarJogada(newValue);
        }

        private void processarJogada(String simbolo) {
            if (ganhou(simbolo)) {
                jogadorGanhou(simbolo);
                pontuar(simbolo);
            } else if (deuVelha()) {
                terminouEmVelha();
            }
        }

    }

    private void trocarJogador() {
        jogadorAtual.set(jogadorAtual.get().equalsIgnoreCase("x") ? "o" : "x");
    }

//    private void processarJogada(String simbolo) {
//        if (ganhou(simbolo)) {
//            jogadorGanhou(simbolo);
//            pontuar(simbolo);
//        } else if (deuVelha()) {
//            terminouEmVelha();
//        }
//    }
    private boolean deuVelha() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (matriz[i][j].getValueSafe().isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    private void pontuar(String vencedor) {
        if (vencedor.equalsIgnoreCase("x")) {
            placarJogador1.set(placarJogador1.get() + 1);
        } else if (vencedor.equalsIgnoreCase("o")) {
            placarJogador2.set(placarJogador2.get() + 1);
        }
    }

    private boolean equalsMatriz(int lin, int col, String simbolo) {
        return matriz[lin][col].getValueSafe().equalsIgnoreCase(simbolo);
    }

    private boolean ganhouLinha(String simbolo) {
        return (equalsMatriz(0, 0, simbolo) && equalsMatriz(0, 1, simbolo) && equalsMatriz(0, 2, simbolo))
                || (equalsMatriz(1, 0, simbolo) && equalsMatriz(1, 1, simbolo) && equalsMatriz(1, 2, simbolo))
                || (equalsMatriz(2, 0, simbolo) && equalsMatriz(2, 1, simbolo) && equalsMatriz(2, 2, simbolo));
    }

    private boolean ganhouColuna(String simbolo) {
        return (equalsMatriz(0, 0, simbolo) && equalsMatriz(1, 0, simbolo) && equalsMatriz(2, 0, simbolo))
                || (equalsMatriz(0, 1, simbolo) && equalsMatriz(1, 1, simbolo) && equalsMatriz(2, 1, simbolo))
                || (equalsMatriz(0, 2, simbolo) && equalsMatriz(1, 2, simbolo) && equalsMatriz(2, 2, simbolo));
    }

    private boolean ganhouDiagonal(String simbolo) {
        return (equalsMatriz(0, 0, simbolo) && equalsMatriz(1, 1, simbolo) && equalsMatriz(2, 2, simbolo))
                || (equalsMatriz(0, 2, simbolo) && equalsMatriz(1, 1, simbolo) && equalsMatriz(2, 0, simbolo));
    }

    /**
     * Verifica se alguém ganhou o jogo.
     *
     * @param simbolo pode ser 'X' ou 'O'.
     * @return verdadeiro se letra ganhou o jogo, senão falso.
     */
    private boolean ganhou(String simbolo) {
        return ganhouLinha(simbolo) || ganhouColuna(simbolo) || ganhouDiagonal(simbolo);
    }

    /**
     * Verifica se alguém ganhou o jogo.
     *
     * @param simbolo pode ser 'X' ou 'O'.
     * @return verdadeiro se letra ganhou o jogo, senão falso.
     */
    private boolean ganhou(String matriz[][], String simbolo) {
        return // linhas
                (matriz[0][0] != null && matriz[0][0].equalsIgnoreCase(simbolo)
                && matriz[0][1] != null && matriz[0][1].equalsIgnoreCase(simbolo)
                && matriz[0][2] != null && matriz[0][2].equalsIgnoreCase(simbolo))
                || (matriz[1][0] != null && matriz[1][0].equalsIgnoreCase(simbolo)
                && matriz[1][1] != null && matriz[1][1].equalsIgnoreCase(simbolo)
                && matriz[1][2] != null && matriz[1][2].equalsIgnoreCase(simbolo))
                || (matriz[2][0] != null && matriz[2][0].equalsIgnoreCase(simbolo)
                && matriz[2][1] != null && matriz[2][1].equalsIgnoreCase(simbolo)
                && matriz[2][2] != null && matriz[2][2].equalsIgnoreCase(simbolo))
                || // colunas 
                (matriz[0][0] != null && matriz[0][0].equalsIgnoreCase(simbolo)
                && matriz[1][0] != null && matriz[1][0].equalsIgnoreCase(simbolo)
                && matriz[2][0] != null && matriz[2][0].equalsIgnoreCase(simbolo))
                || (matriz[0][1] != null && matriz[0][1].equalsIgnoreCase(simbolo)
                && matriz[1][1] != null && matriz[1][1].equalsIgnoreCase(simbolo)
                && matriz[2][1] != null && matriz[2][1].equalsIgnoreCase(simbolo))
                || (matriz[0][2] != null && matriz[0][2].equalsIgnoreCase(simbolo)
                && matriz[1][2] != null && matriz[1][2].equalsIgnoreCase(simbolo)
                && matriz[2][2] != null && matriz[2][2].equalsIgnoreCase(simbolo))
                || // diagonais     
                (matriz[0][0] != null && matriz[0][0].equalsIgnoreCase(simbolo)
                && matriz[1][1] != null && matriz[1][1].equalsIgnoreCase(simbolo)
                && matriz[2][2] != null && matriz[2][2].equalsIgnoreCase(simbolo))
                || (matriz[0][2] != null && matriz[0][2].equalsIgnoreCase(simbolo)
                && matriz[1][1] != null && matriz[1][1].equalsIgnoreCase(simbolo)
                && matriz[2][0] != null && matriz[2][0].equalsIgnoreCase(simbolo));
    }

    /**
     * @return the placarJogador1
     */
    public IntegerProperty getPlacarJogador1() {
        return placarJogador1;
    }

    /**
     * @return the placarJogador2
     */
    public IntegerProperty getPlacarJogador2() {
        return placarJogador2;
    }

    /**
     * @return the campo1
     */
    public StringProperty getCampo1() {
        return campo1;
    }

    /**
     * @return the campo2
     */
    public StringProperty getCampo2() {
        return campo2;
    }

    /**
     * @return the campo3
     */
    public StringProperty getCampo3() {
        return campo3;
    }

    /**
     * @return the campo4
     */
    public StringProperty getCampo4() {
        return campo4;
    }

    /**
     * @return the campo5
     */
    public StringProperty getCampo5() {
        return campo5;
    }

    /**
     * @return the campo6
     */
    public StringProperty getCampo6() {
        return campo6;
    }

    /**
     * @return the campo7
     */
    public StringProperty getCampo7() {
        return campo7;
    }

    /**
     * @return the campo8
     */
    public StringProperty getCampo8() {
        return campo8;
    }

    /**
     * @return the campo9
     */
    public StringProperty getCampo9() {
        return campo9;
    }

    /**
     * @return the campos
     */
    public StringProperty[] getCampos() {
        return campos;
    }

    public abstract void jogadorGanhou(String jogador);

    public abstract void terminouEmVelha();

    public class JogadorVirtual {

        private final String simbolo;
        private final String outroSimbolo;

        private final String matrizCopia[][] = new String[3][3];

        public JogadorVirtual(String simbolo) {
            this.simbolo = simbolo;
            this.outroSimbolo = simbolo.equalsIgnoreCase("x") ? "o" : "x";
        }

//        private void copiarMatriz(StringProperty matriz[][]) {
//            for (int i = 0; i < 3; i++) {
//                for (int j = 0; j < 3; j++) {
//                    matrizCopia[i][j] = matriz[i][j].getValueSafe();
//                }
//            }
//        }
//
//        public int jogar() {
//            int maxLin = 0, maxCol = 0, max = -2;
//            
//            copiarMatriz(matriz);
//
//            for (int lin = 0; lin < 3; lin++) {
//                for (int col = 0; col < 3; col++) {
//                    if (!isOcupado(matrizCopia, lin, col)) {
//                        matrizCopia[lin][col] = simbolo;
//                        int valor = minimax(false); // vez do usuario
//                        matrizCopia[lin][col] = "";
//                        if (valor > max) {
//                            max = valor;
//                            maxLin = lin;
//                            maxCol = col;
//                        }
//                    }
//                }
//            }
//            if (max == -2) {
//                return -1;
//            } else {
//                return 3 * maxLin + maxCol;
//            }
//        }
//
//        /**
//         * Calcula o valor de uma jogada
//         *
//         * @param vezComputador é true na jogada do Computador.
//         * @return o valor maximo da jogada na vez do Computador e o valor mínimo na vez do Usuário.
//         */
//        private int minimax(boolean vezComputador) {
//            int valor, max = -2, min = 2;
//            if (ganhou(matrizCopia, simbolo)) {
//                return 1;   // máximo para o computador
//            }
//            if (ganhou(matrizCopia, simbolo)) {
//                return -1;  // minimo para o usuário
//            }
//            for (int lin = 0; lin < 3; lin++) {
//                for (int col = 0; col < 3; col++) {
//                    if (!isOcupado(matrizCopia, lin, col)) {
//                        matrizCopia[lin][col] = vezComputador ? simbolo : outroSimbolo;
//                        valor = minimax(!vezComputador);
//                        matrizCopia[lin][col] = null;
//                        if (valor > max) {
//                            max = valor;
//                        }
//                        if (valor < min) {
//                            min = valor;
//                        }
//                    }
//                }
//            }
//            // significa que todas posicoes estão ocupadas e max não foi atualizado
//            // no if dentro dos laços for
//            if (max == -2) {
//                return 0;
//            }
//            if (vezComputador) {
//                return max;
//            } else {
//                return min;
//            }
        public int jogar() {

            int maxLin = 0, maxCol = 0, valor, max = -2;
            for (int lin = 0; lin < 3; lin++) {
                for (int col = 0; col < 3; col++) {
                    if (!isOcupado(lin, col)) {
                        matriz[lin][col].set(simbolo);
                        valor = minimax(false); // vez do usuario
                        matriz[lin][col].set("");
                        if (valor > max) {
                            max = valor;
                            maxLin = lin;
                            maxCol = col;
                        }
                    }
                }
            }
            if (max == -2) {
                return -1;
            } else {
//                matriz[maxLin][maxCol].set(simbolo);
                return 3 * maxLin + maxCol;
            }
        }

        /**
         * Calcula o valor de uma jogada
         *
         * @param vezComputador é true na jogada do Computador.
         * @return o valor maximo da jogada na vez do Computador e o valor mínimo na vez do Usuário.
         */
        private int minimax(boolean vezComputador) {
            int valor, max = -2, min = 2;
            if (ganhou(simbolo)) {
                return 1;   // máximo para o computador
            }
            if (ganhou(outroSimbolo)) {
                return -1;  // minimo para o usuário
            }
            for (int lin = 0; lin < 3; lin++) {
                for (int col = 0; col < 3; col++) {
                    if (!isOcupado(lin, col)) {
                        matriz[lin][col].set(vezComputador ? simbolo : outroSimbolo);
                        valor = minimax(!vezComputador);
                        matriz[lin][col].set("");
                        if (valor > max) {
                            max = valor;
                        }
                        if (valor < min) {
                            min = valor;
                        }
                    }
                }
            }
            // significa que todas posicoes estão ocupadas e max não foi atualizado
            // no if dentro dos laços for
            if (max == -2) {
                return 0;
            }
            if (vezComputador) {
                return max;
            } else {
                return min;
            }
        }
    }

}
