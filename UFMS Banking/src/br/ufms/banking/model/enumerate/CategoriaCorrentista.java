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
package br.ufms.banking.model.enumerate;

/**
 *
 * @author Kleber Kruger
 */
public enum CategoriaCorrentista {

    A(0.75f, 10.0f), B(0.6f, 12.0f), C(0.5f, 14.0f);

    private final float taxaRendimento;
    private final float taxaJuros;

    /**
     *
     * @param taxaRendimento
     * @param taxaJuros
     */
    private CategoriaCorrentista(float taxaRendimento, float taxaJuros) {
        this.taxaRendimento = taxaRendimento;
        this.taxaJuros = taxaJuros;
    }

    /**
     * @return the taxaRendimento
     */
    public final float getTaxaRendimento() {
        return taxaRendimento;
    }

    /**
     * @return the taxaJuros
     */
    public final float getTaxaJuros() {
        return taxaJuros;
    }

}
