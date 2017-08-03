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

/**
 *
 * @author Kleber Kruger
 */
public class ContaCorrente extends ContaBancaria {
    
    private static final long serialVersionUID = 1L;
    
    private Double limite;
    
    public ContaCorrente(Correntista correntista) {
        super(correntista);
        
        final ContaCorrente cc = this;
        correntista.registrarConta(cc);
    }
    
    /**
     * Realiza a operação de saque nesta conta.
     *
     * @param valor
     * 
     * @return 
     */
    @Override
    public Saque sacar(double valor) {
        if (valor < 0) {
            throw new IllegalArgumentException("O valor do saque não pode ser negativo");
        } else if (valor > saldo + limite) {
            throw new IllegalArgumentException("Saldo insuficiente");
        }
        this.saldo -= valor;
        return new Saque(getNumero(), valor);
    }
    
    /**
     * 
     * @return 
     */
    public final Double getLimite() {
        return limite;
    }
    
    /**
     * 
     * @param valor 
     */
    public void setLimite(double valor) {
        if (valor < 0) {
            throw new IllegalArgumentException("O valor do limite não pode ser negativo");
        }
        this.limite = valor;
    }

    @Override
    public TipoConta getTipo() {
        return TipoConta.CONTA_CORRENTE;
    }
    
}
