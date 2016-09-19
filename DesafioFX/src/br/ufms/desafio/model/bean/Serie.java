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

import br.ufms.desafio.model.bean.enumerate.NivelEnsino;
import java.io.Serializable;

/**
 * Classe que mapeia a tabela Serie do banco de dados.
 *
 * @author Kleber Kruger
 */
public class Serie extends Bean<Long> implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private NivelEnsino nivel;
    private Short ano;
    
    /**
     * Cria um novo objeto Serie (série do aluno) com todos os atributos inicialmente nulos.
     */
    public Serie() {
        super();
    }

    /**
     * @return the nivel
     */
    public NivelEnsino getNivel() {
        return nivel;
    }

    /**
     * @param nivel the nivel to set
     */
    public void setNivel(NivelEnsino nivel) {
        this.nivel = nivel;
    }

    /**
     * @return the ano
     */
    public Short getAno() {
        return ano;
    }

    /**
     * @param ano the ano to set
     */
    public void setAno(Short ano) {
        this.ano = ano;
    }
    
}
