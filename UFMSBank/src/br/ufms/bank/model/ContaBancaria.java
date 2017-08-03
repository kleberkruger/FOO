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

import br.ufms.bank.model.enumerate.TipoConta;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kleber Kruger
 */
public abstract class ContaBancaria extends Bean<Long> {

    private static final long serialVersionUID = 1L;

    private final Correntista correntista;
    private final List<Transacao> transacoes;

    protected Double saldo; // as operações que modificam o saldo podem ser sobrescritas

    /**
     * Cria um objeto ContaBancaria.
     *
     * @param correntista
     */
    protected ContaBancaria(Correntista correntista) {
        super(correntista.getID());

        this.correntista = correntista;
        this.transacoes = new ArrayList<>();
    }

    /**
     * Realiza a operação de depósito nesta conta.
     *
     * @param valor
     *
     * @return
     */
    public Deposito depositar(double valor) {
        return depositar(valor, null);
    }

    /**
     * Realiza a operação de depósito nesta conta.
     *
     * @param valor
     * @param depositante
     *
     * @return
     */
    public Deposito depositar(double valor, String depositante) {
        if (valor < 0) {
            throw new IllegalArgumentException("O valor do depósito não pode ser negativo");
        }
        this.saldo += valor;

        Deposito deposito = new Deposito(valor, depositante);
        transacoes.add(deposito);
        return deposito;
    }

    /**
     * Realiza a operação de saque nesta conta.
     *
     * @param valor
     *
     * @return
     */
    public Saque sacar(double valor) {
        if (valor < 0) {
            throw new IllegalArgumentException("O valor do saque não pode ser negativo");
        } else if (valor > saldo) {
            throw new IllegalArgumentException("Saldo insuficiente");
        }
        this.saldo -= valor;

        Saque saque = new Saque(this.getNumero(), valor);
        transacoes.add(saque);
        return saque;
    }

    /**
     * Realiza a operação de trasnferência de valores entre contas.
     *
     * @param valor
     * @param contaDestino
     *
     * @return
     */
    public Transferencia transferir(double valor, ContaCorrente contaDestino) {
        this.sacar(valor);
        contaDestino.depositar(valor);

        Transferencia transferencia = new Transferencia(getNumero(), contaDestino.getNumero(), valor);
        transacoes.add(transferencia);
        return transferencia;
    }

    /**
     * @return the correntista
     */
    public final Correntista getCorrentista() {
        return correntista;
    }

    /**
     * @return the saldo
     */
    public final Long getNumero() {
        return super.getID();
    }

    /**
     * @return the saldo
     */
    public final Double getSaldo() {
        return saldo;
    }

    public abstract TipoConta getTipo();

}
