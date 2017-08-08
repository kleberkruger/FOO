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
package br.ufms.bank.exception;

/**
 *
 * @author Kleber Kruger
 */
public class ValidacaoException extends Exception {

    private static final String MENSAGEM_PADRAO = "Erro de validação";

    /**
     * Cria uma exceção de validação.
     */
    public ValidacaoException() {
        this(MENSAGEM_PADRAO);
    }

    /**
     * Cria uma exceção de validação com a mensagem recebida por parâmetro.
     *
     * @param mensagem
     */
    public ValidacaoException(String mensagem) {
        super(mensagem);
    }

}
