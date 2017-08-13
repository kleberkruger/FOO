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

import br.ufms.bank.model.enumerate.TipoConta;
import br.ufms.kruger.util.persistence.Bean;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kleber Kruger
 * @param <C> o tipo do Correntista (PessoaFísica ou PessoaJurídica)
 */
public abstract class ContaBancaria<C extends Correntista> extends Bean<Long> {

    private static final long serialVersionUID = 1L;

    private final C correntista;    
    private final List<Transacao> transacoes;

    protected Double saldo; // as operações que modificam o saldo podem ser sobrescritas

    /**
     * Cria um objeto ContaBancaria.
     *
     * @param correntista
     */
    protected ContaBancaria(C correntista) {
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

        Deposito deposito = new Deposito(this.toString(), valor, depositante);
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

        Saque saque = new Saque(this.toString(), valor);
        transacoes.add(saque);
        return saque;
    }

    /**
     * Realiza a operação de trasnferência de valores entre contas.
     *
     * @param valor
     * @param destino
     *
     * @return
     */
    public Transferencia transferir(double valor, ContaCorrente destino) {
        this.sacar(valor);
        destino.depositar(valor);

        Transferencia transferencia = new Transferencia(this.toString(), destino.toString(), valor);
        transacoes.add(transferencia);
        return transferencia;
    }

    /**
     * @return the correntista
     */
    public final C getCorrentista() {
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
    public final String getNumeroFormatado() {
        return String.format("%05d", this.getNumero());
    }

    /**
     * @return the saldo
     */
    public final Double getSaldo() {
        return saldo;
    }

    /**
     * @return the tipo
     */
    public abstract TipoConta getTipo();

    /**
     * Retorna a representação string deste objeto conforme o exemplo: Operação: 001; Conta: 01234;
     * Resultado: "001/01234".
     *
     * @return operação/numero
     */
    @Override
    public String toString() {
        return getTipo().getOperacaoFormatada() + '/' + getNumeroFormatado();
    }

}
