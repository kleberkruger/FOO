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

import br.ufms.banking.model.enumerate.TipoConta;
import br.ufms.kruger.util.persistence.Bean;
import java.util.Collection;
import java.util.HashSet;

/**
 *
 * @author Kleber Kruger
 */
public abstract class ContaBancaria extends Bean<NumeroBancario> {

    private static final long serialVersionUID = 1L;

    protected Double saldo; // as operações que modificam o saldo podem ser sobrescritas

    private final Correntista correntista;
    private NumeroBancario agencia;
    private final Collection<Transacao> transacoes;

    /**
     *
     * @param numeroAgencia
     * @param numeroConta
     * @return
     */
    private static short gerarDigito(short numeroAgencia, int numeroConta) {
        return (short) ((numeroAgencia + numeroConta) % 10);
    }

    /**
     * Cria um objeto ContaBancaria.
     *
     * @param correntista
     * @param agencia
     */
    protected ContaBancaria(Correntista correntista, NumeroBancario agencia) {
        super(correntista.getNumeroConta());

        this.correntista = correntista;
        this.agencia = agencia;
        this.transacoes = new HashSet<>();
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
            throw new IllegalArgumentException("O valor do depósito não pode ser negativo.");
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
            throw new IllegalArgumentException("O valor do saque não pode ser negativo.");
        } else if (valor > saldo) {
            throw new IllegalArgumentException("Saldo insuficiente.");
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
    public Transferencia transferir(double valor, ContaBancaria destino) {
        this.sacar(valor);
        destino.depositar(valor);

        Transferencia transferencia = new Transferencia(this.toString(), destino.toString(), valor);
        transacoes.add(transferencia);
        return transferencia;
    }

    /**
     * Transferência Eletrônica (TED) para contas de outros bancos.
     *
     * @TODO: Implementar este método!
     *
     * @param valor
     * @param banco
     * @param destino
     *
     * @return
     */
    public Transferencia transferirEntreBancos(double valor, Banco banco, ContaCorrente destino) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * @param <C>
     * @return the correntista
     */
    public final <C extends Correntista> C getCorrentista() {
        return (C) correntista;
    }

    /**
     * @return the agencia
     */
    public NumeroBancario getAgencia() {
        return agencia;
    }

    /**
     * @param agencia the agencia to set
     */
    public void setAgencia(NumeroBancario agencia) {
        this.agencia = agencia;
    }

    /**
     * @return the numero
     */
    public final Integer getNumero() {
        return super.getID().getNumero();
    }
    
    /**
     * @return the digito
     */
    public final Integer getDigito() {
        return super.getID().getDigito();
    }


    /**
     * @return the saldo
     */
    public final String getNumeroFormatado() {
        return String.format("%08d-%d", this.getNumero(), this.getDigito());
    }

    /**
     * @return the saldo
     */
    public final Double getSaldo() {
        return saldo;
    }

    /**
     * @return the transacoes
     */
    public final Collection<Transacao> getTransacoes() {
        return transacoes;
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
