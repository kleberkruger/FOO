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
public class Telefone extends Bean implements Serializable {
    
    private Integer ddd;
    private Integer numero;
    private TipoTelefone tipo;

    /**
     * @return the ddd
     */
    public Integer getDdd() {
        return ddd;
    }

    /**
     * @param ddd the ddd to set
     */
    public void setDdd(Integer ddd) {
        this.ddd = ddd;
    }

    /**
     * @return the numero
     */
    public Integer getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    /**
     * @return the tipo
     */
    public TipoTelefone getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(TipoTelefone tipo) {
        this.tipo = tipo;
    }
}
