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
package br.ufms.banking.model.domain;

import java.io.Serializable;

/**
 *
 * @author Kleber Kruger
 */
public class NumeroBancario implements Comparable<NumeroBancario>, Serializable {

    private static final long serialVersionUID = 1L;

    private final Integer numero;
    private final Integer digito;

    /**
     * Cria um número bancário.
     *
     * @param numero
     */
    public NumeroBancario(int numero) {
        this(numero, null);
    }
    
//    /**
//     * Cria um número bancário.
//     *
//     * @param numero
//     * @param qtdCasas
//     */
//    public NumeroBancario(int numero, short qtdCasas) {
//        this(numero, null);
//    }

    /**
     * Cria um número bancário.
     *
     * @param numero
     * @param digito
     */
    public NumeroBancario(int numero, Integer digito) {
        if (numero < 0) {
            throw new IllegalArgumentException("O número não pode ser negativo.");
        }
        this.numero = numero;
        this.digito = validar(numero);
    }

//    /**
//     *
//     * @param numero
//     * @param digito
//     * @param qtdCasas
//     */
//    public NumeroBancario(Integer numero, Integer digito, int qtdCasas) {
//        if (numero < 0) {
//            throw new IllegalArgumentException("O número não pode ser negativo.");
//        }
//        this.numero = numero;
//        this.digito = validar(numero);
//    }

    /**
     * Algoritmo verificador de dígito usado em códigos bancários como mecanismo de autenticação
     * para verificar a validade e a autenticidade de um valor numérico, evitando assim fraudes ou
     * erros de transmissão ou digitação.
     *
     * Algoritmo: Módulo 10.
     *
     * @param numero numero a ser verificado.
     * @return o dígito verificador
     */
    public static int validar(int numero) {
        return validar(String.valueOf(numero));
    }

    /**
     * Algoritmo verificador de dígito usado em códigos bancários como mecanismo de autenticação
     * para verificar a validade e a autenticidade de um valor numérico, evitando assim fraudes ou
     * erros de transmissão ou digitação.
     *
     * Algoritmo: Módulo 10.
     *
     * @param numero numero a ser verificado.
     * @return o dígito verificador
     *
     * @exception NumberFormatException caso a string não contenha um valor que possa ser convertido
     * em um inteiro.
     */
    private static int validar(String numero) {
        int i = 2;
        int sum = 0;
        for (char c : numero.toCharArray()) {
            int res = Integer.parseInt(String.valueOf(c)) * i;
            sum += res > 9 ? (res - 9) : res;
            i = i == 2 ? 1 : 2;
        }
        return 10 - (sum % 10);
    }

    /**
     * @return the numero
     */
    public Integer getNumero() {
        return numero;
    }

    /**
     * @return the digito
     */
    public Integer getDigito() {
        return digito;
    }

    @Override
    public int compareTo(NumeroBancario other) {
        return this.numero - other.numero;
    }

}
