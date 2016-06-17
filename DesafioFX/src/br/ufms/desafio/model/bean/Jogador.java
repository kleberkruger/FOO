/*
 * Copyright (C) 2016 angelino.caon
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
package br.ufms.desafio.model.bean;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author angelino.caon
 */
public class Jogador extends Usuario implements Serializable {
    
    private LocalDate nascimento;
    private List<Deficiencia> deficiencias;
    private Desempenho desempenho;

    /**
     * @return the nascimento
     */
    public LocalDate getNascimento() {
        return nascimento;
    }

    /**
     * @param nascimento the nascimento to set
     */
    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }

    /**
     * @return the deficiencias
     */
    public List<Deficiencia> getDeficiencias() {
        return deficiencias;
    }

    /**
     * @param deficiencias the deficiencias to set
     */
    public void setDeficiencias(List<Deficiencia> deficiencias) {
        this.deficiencias = deficiencias;
    }

    /**
     * @return the desempenho
     */
    public Desempenho getDesempenho() {
        return desempenho;
    }

    /**
     * @param desempenho the desempenho to set
     */
    public void setDesempenho(Desempenho desempenho) {
        this.desempenho = desempenho;
    }
}
