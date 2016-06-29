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

/**
 *
 * @author angelino.caon
 */
public class Municipio extends Bean implements Serializable {

    private Long codigoIBGE;
    private String nome;
    private String uf;

    /**
     * @return the codigoIBGE
     */
    public Long getCodigoIBGE() {
        return codigoIBGE;
    }

    /**
     * @param codigoIBGE the codigoIBGE to set
     */
    public void setCodigoIBGE(Long codigoIBGE) {
        this.codigoIBGE = codigoIBGE;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the uf
     */
    public String getUF() {
        return uf;
    }

    /**
     * @param uf the uf to set
     */
    public void setUF(String uf) {
        this.uf = uf;
    }
}
