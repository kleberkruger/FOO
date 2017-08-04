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
package br.ufms.bank.model.enumerate;

/**
 *
 * @author Kleber Kruger
 */
public enum TipoConta {

    CONTA_CORRENTE(001, "Conta Corrente"),
    CONTA_POUPANCA(013, "Conta Poupança");

    private final Integer operacao;
    private final String descricao;

    private TipoConta(Integer operacao, String descricao) {
        this.operacao = operacao;
        this.descricao = descricao;
    }

    /**
     * @return the operacao
     */
    public Integer getOperacao() {
        return operacao;
    }
    
    /**
     * @return the operacao formatada
     */
    public String getOperacaoFormatada() {
        return String.format("%3d", operacao);
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

}
