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

import br.ufms.banking.model.enumerate.CategoriaCorrentista;
import br.ufms.banking.model.enumerate.TipoConta;

/**
 *
 * @author Kleber Kruger
 */
public class ContaPoupanca extends ContaBancaria {

    private static final long serialVersionUID = 1L;

    private CategoriaCorrentista categoria;

    /**
     * Cria um objeto ContaPoupanca com a categoria do cliente definida como C - taxa de rendimento
     * padrão (0.5% a.m.).
     *
     * @param correntista
     * @param agencia
     */
    public ContaPoupanca(Correntista correntista, NumeroBancario agencia) {
        this(correntista, agencia, CategoriaCorrentista.C);
    }

    /**
     * Cria um objeto ContaPoupanca.
     *
     * @param correntista
     * @param agencia
     * @param categoria
     */
    public ContaPoupanca(Correntista correntista, NumeroBancario agencia, CategoriaCorrentista categoria) {
        super(correntista, agencia);
        setCategoria(categoria);
    }

    /**
     * @return the categoria
     */
    public final CategoriaCorrentista getCategoria() {
        return categoria;
    }

    /**
     * @param categoria the categoria to set
     */
    public final void setCategoria(CategoriaCorrentista categoria) {
        this.categoria = categoria;
    }

    /**
     * @return o tipo da conta
     */
    @Override
    public TipoConta getTipo() {
        return TipoConta.CONTA_POUPANCA;
    }

}
