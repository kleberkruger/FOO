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
package br.ufms.bank.model;

import br.ufms.bank.model.enumerate.TipoTransacao;

/**
 *
 * @author Kleber Kruger
 */
public class Saque extends Transacao {

    private static final long serialVersionUID = 1L;

    private final String conta;

    /**
     *
     * @param conta
     * @param valor
     */
    protected Saque(String conta, double valor) {
        super(valor);
        this.conta = conta;
    }

    /**
     * @return the conta
     */
    public final String getConta() {
        return conta;
    }

    /**
     * @return the tipo da transação
     */
    @Override
    public final TipoTransacao getTipoTransacao() {
        return TipoTransacao.SAQUE;
    }

}
