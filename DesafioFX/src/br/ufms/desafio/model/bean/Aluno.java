/*
 * Copyright (C) 2016 Kleber Kruger
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
import java.util.ArrayList;
import java.util.List;

/**
 * Classe que mapeia a tabela Aluno do banco de dados.
 *
 * @author Kleber Kruger
 */
public class Aluno extends Jogador implements Serializable {

    private static final long serialVersionUID = 1L;

    private Serie serie;
    private Escola escola;
    private LocalDate ingresso;
    private List<Responsavel> responsaveis;

    /**
     * Cria um novo objeto Aluno com os atributos série, nível, escola e data de ingresso nulos. A
     * lista de responsáveis é inicializada vazia e o restante dos atributps são inicializados de
     * acordo com os construtores das superclasses Jogador e Usuario.
     */
    public Aluno() {
        this.responsaveis = new ArrayList<>();
    }

    /**
     * @return the serie
     */
    public Serie getSerie() {
        return serie;
    }

    /**
     * @param serie the serie to set
     */
    public void setSerie(Serie serie) {
        this.serie = serie;
    }

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
