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
import java.time.LocalDate;

/**
 *
 * @author Kleber Kruger
 */
public abstract class Transacao extends Bean<Long> {

    private Double valor;
    private LocalDate dataHora;

    /**
     * Método responsável por gerar novos IDs para transações. Este método deve ser capaz de
     * identificar o último ID gerado e gerar automaticamente novos IDs. Números gerados jamais se
     * repetirão.
     *
     * @return id
     */
    private static long gerarNovoID() {
        throw new UnsupportedOperationException("Implemente este método.");
    }

    /**
     * Cria um novo objeto Transacao.
     *
     * @param valor
     */
    protected Transacao(double valor) {
        this(valor, LocalDate.now());
    }

    /**
     * Cria um novo objeto Transacao.
     *
     * @param valor
     * @param dataHora
     */
    protected Transacao(double valor, LocalDate dataHora) {
        super(gerarNovoID());

        if (valor < 0) {
            throw new IllegalArgumentException("O valor da transação não pode ser negativo.");
        }

        this.valor = valor;
        this.dataHora = dataHora;
    }

    /**
     * @return the valor
     */
    public Double getValor() {
        return valor;
    }

    /**
     * @return the dataHora
     */
    public LocalDate getDataHora() {
        return dataHora;
    }

    /**
     * @return the tipoTransacao
     */
    public abstract TipoTransacao getTipoTransacao();

}
