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

/**
 *
 * @author Kleber Kruger
 */
public class JogadorVirtual {

    private final String simbolo;

    public JogadorVirtual(String simbolo) {
        this.simbolo = simbolo;
    }

    public int jogar() {
        return 0;
//        int maxLin = 0, maxCol = 0, max = -2;
//        for (int lin = 0; lin < 3; lin++) {
//            for (int col = 0; col < 3; col++) {
//                if (!isOcupado(lin, col)) {
//                    matriz[lin][col] = CHAR_O;
//                    int valor = minimax(false); // vez do usuario
//                    matriz[lin][col] = CHAR_VAZIO;
//                    for (int i = 0; i < 3; i++) {
//                        System.out.println(matriz[i][0] + " " + matriz[i][1] + " " + matriz[i][2]);
//                    }
//                    if (valor > max) {
//                        max = valor;
//                        maxLin = lin;
//                        maxCol = col;
//                    }
//                }
//            }
//        }
//        if (max == -2) {
//            return -1;
//        } else {
//            matriz[maxLin][maxCol] = CHAR_O;
//            return 3 * maxLin + maxCol;
//        }
    }

    /**
     * Calcula o valor de uma jogada
     *
     * @param vezComputador é true na jogada do Computador.
     * @return o valor maximo da jogada na vez do Computador e o valor mínimo na vez do Usuário.
     */
    int minimax(boolean vezComputador) {
        return 0;
//        int valor, max = -2, min = 2;
//        if (ganhou(CHAR_O)) {
//            return 1;   // máximo para o computador
//        }
//        if (ganhou(CHAR_X)) {
//            return -1;  // minimo para o usuário
//        }
//        for (int lin = 0; lin < 3; lin++) {
//            for (int col = 0; col < 3; col++) {
//                if (!isOcupado(lin, col)) {
//                    matriz[lin][col] = vezComputador ? CHAR_O : CHAR_X;
//                    valor = minimax(!vezComputador);
//                    matriz[lin][col] = CHAR_VAZIO;
//                    if (valor > max) {
//                        max = valor;
//                    }
//                    if (valor < min) {
//                        min = valor;
//                    }
//                }
//            }
//        }
//        // significa que todas posicoes estão ocupadas e max não foi atualizado
//        // no if dentro dos laços for
//        if (max == -2) {
//            return 0;
//        }
//        if (vezComputador) {
//            return max;
//        } else {
//            return min;
//        }
    }
}
