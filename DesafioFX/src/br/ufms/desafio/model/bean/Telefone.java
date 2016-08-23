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

import br.ufms.desafio.model.bean.enumerate.TipoTelefone;
import java.io.Serializable;

/**
 * Classe que mapeia a tabela Telefone do banco de dados.
 *
 * @author Kleber Kruger
 */
public class Telefone extends Bean<Long> implements Serializable {

    private static final long serialVersionUID = 1L;

    private String ddd;
    private String numero;
    private Boolean principal;
    private TipoTelefone tipo;

    /**
     * Cria um novo objeto Telefone com todos os atributos nulos inicialmente.
     */
    public Telefone() {
        super();
    }

    /**
     * @return the ddd
     */
    public String getDDD() {
        return ddd;
    }

    /**
     * @param ddd the ddd to set
     */
    public void setDDD(String ddd) {
        this.ddd = ddd;
    }

    /**
     * @return the numero
     */
    public String getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * @return the principal
     */
    public Boolean getPrincipal() {
        return principal;
    }

    /**
     * @param principal the principal to set
     */
    public void setPrincipal(Boolean principal) {
        this.principal = principal;
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
