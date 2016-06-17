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
public class Aluno extends Jogador implements Serializable {
    
    private Escola escola;
    private Integer serie;
    private LocalDate ingresso;
    private List<Responsavel> responsaveis;

    /**
     * @return the escola
     */
    public Escola getEscola() {
        return escola;
    }

    /**
     * @param escola the escola to set
     */
    public void setEscola(Escola escola) {
        this.escola = escola;
    }

    /**
     * @return the serie
     */
    public Integer getSerie() {
        return serie;
    }

    /**
     * @param serie the serie to set
     */
    public void setSerie(Integer serie) {
        this.serie = serie;
    }

    /**
     * @return the ingresso
     */
    public LocalDate getIngresso() {
        return ingresso;
    }

    /**
     * @param ingresso the ingresso to set
     */
    public void setIngresso(LocalDate ingresso) {
        this.ingresso = ingresso;
    }

    /**
     * @return the responsaveis
     */
    public List<Responsavel> getResponsaveis() {
        return responsaveis;
    }

    /**
     * @param responsaveis the responsaveis to set
     */
    public void setResponsaveis(List<Responsavel> responsaveis) {
        this.responsaveis = responsaveis;
    }
}
