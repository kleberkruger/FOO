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
package br.ufms.banking.model.repository;

/**
 *
 * @author Kleber Kruger
 */
public class CorrentistaRepository {
    
    /**
     * Método responsável por gerar novos IDs para Pessoas (físicas ou juridicas). Este método deve
     * ser capaz de identificar o último ID gerado e gerar automaticamente novos IDs. Números
     * gerados jamais se repetirão.
     *
     * @return id
     */
    protected static long gerarNovoNumeroConta() {
        throw new UnsupportedOperationException("Implemente este método.");
    }
}
