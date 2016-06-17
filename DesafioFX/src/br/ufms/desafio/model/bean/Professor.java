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
import java.util.List;

/**
 *
 * @author angelino.caon
 */
public class Professor extends Jogador implements Serializable {
    
    private Titulacao titulacao;
    private List<Escola> escolas;
    private List<Turma> turmas;

    /**
     * @return the titulacao
     */
    public Titulacao getTitulacao() {
        return titulacao;
    }

    /**
     * @param titulacao the titulacao to set
     */
    public void setTitulacao(Titulacao titulacao) {
        this.titulacao = titulacao;
    }

    /**
     * @return the escolas
     */
    public List<Escola> getEscolas() {
        return escolas;
    }

    /**
     * @param escolas the escolas to set
     */
    public void setEscolas(List<Escola> escolas) {
        this.escolas = escolas;
    }

    /**
     * @return the turmas
     */
    public List<Turma> getTurmas() {
        return turmas;
    }

    /**
     * @param turmas the turmas to set
     */
    public void setTurmas(List<Turma> turmas) {
        this.turmas = turmas;
    }
}
