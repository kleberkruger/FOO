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
public class Telefone implements Serializable {

    private static final long serialVersionUID = 1L;

    private String numero;

    public Telefone(String numero) {
        setNumero(numero);
    }

    /**
     * Retorna o número do telefone com a máscara.
     *
     * @return
     */
    public String getNumeroFormatado() {
        switch (numero.length()) {
            case 8:
                return numero;
            case 9:
                return numero;
        }
        return numero;
    }

    /**
     * Valida o número do documento.
     *
     * @param numero
     * @return
     */
    private boolean validar(String numero) {
        for (char c : numero.toCharArray()) {
            if (!Character.isDigit(c) && !Character.isSpaceChar(c)
                    && c != '(' && c != ')' && c != '-') {
                return false;
            }
        }
        return true;
    }

    public boolean isTelefone(String numero) {
        return numero.matches(".((10)|([1-9][1-9]).)\\s9?[6-9][0-9]{3}-[0-9]{4}")
                || numero.matches(".((10)|([1-9][1-9]).)\\s[2-5][0-9]{3}-[0-9]{4}");
    }

    /**
     * @return the numero
     */
    public final String getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    public final void setNumero(String numero) {
        if (!validar(numero)) {
            throw new IllegalArgumentException("Número de telefone inválido");
        }
        this.numero = numero;
    }

    @Override
    public String toString() {
        return getNumeroFormatado();
    }

}
