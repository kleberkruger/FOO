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
public class ContaPoupanca extends ContaBancaria {
    
    private static final long serialVersionUID = 1L;
    
    private static final float TAXA_RENDIMENTO_PADRAO = 0.5f;

    private float taxaRendimento;

    /**
     * Cria um objeto ContaPoupanca com a taxa de rendimento padrão (0.5% a.m.).
     *
     * @param correntista
     */
    public ContaPoupanca(Correntista correntista) {
        this(correntista, TAXA_RENDIMENTO_PADRAO);
    }
    
    /**
     * Cria um objeto ContaPoupanca.
     *
     * @param correntista
     * @param taxaRendimento
     */
    public ContaPoupanca(Correntista correntista, float taxaRendimento) {
        super(correntista);
        setTaxaRendimento(taxaRendimento);
        
        final ContaPoupanca cp = this;
        correntista.registrarConta(cp);
    }

    /**
     * @return the taxaRendimento
     */
    public final Float getTaxaRendimento() {
        return taxaRendimento;
    }

    /**
     * @param taxaRendimento the taxaRendimento to set
     */
    public final void setTaxaRendimento(float taxaRendimento) {
        if (taxaRendimento < 0) {
            throw new IllegalArgumentException("A porcentagem da taxa de rendimento não pode ser negativa");
        }
        this.taxaRendimento = taxaRendimento;
    }

    @Override
    public TipoConta getTipo() {
        return TipoConta.CONTA_POUPANCA;
    }

}
