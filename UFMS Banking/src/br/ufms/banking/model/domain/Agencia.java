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
package br.ufms.banking.model.domain;

import br.ufms.kruger.util.persistence.Bean;

/**
 * Classe que representa a entidade Agencia.
 *
 * Esta classe poderá futuramente substituir a informação municipio por endereço completo.
 *
 * Observação: embora uma agência possua uma lista de bancários (funcionários da agência) e de
 * contas, ter essa informação ao carregar o objeto Agencia geraria overhead desnecessário.
 *
 * @author Kleber Kruger
 */
public class Agencia extends Bean<NumeroBancario> {

    private static final long serialVersionUID = 1L;

    private Municipio municipio;
    private Telefone telefone;

    /**
     * Cria um objeto Agencia.
     *
     * @param municipio
     */
    public Agencia(Municipio municipio) {
        this(municipio, null);
    }

    /**
     * Cria um objeto Agencia.
     *
     * @param municipio
     * @param telefone
     */
    public Agencia(Municipio municipio, Telefone telefone) {
        this(null, municipio, telefone);
    }
    
    /**
     * Cria um objeto Agencia.
     *
     * @param numero
     * @param municipio
     * @param telefone
     */
    public Agencia(NumeroBancario numero, Municipio municipio, Telefone telefone) {
        super(numero);
        
        setMunicipio(municipio);
        setTelefone(telefone);
    }

    /**
     * @return the numero
     */
    public final Integer getNumero() {
        return super.getID() != null ? super.getID().getNumero() : null;
    }

    /**
     * @return the digito
     */
    public final Integer getDigito() {
        return super.getID() != null ? super.getID().getDigito() : null;
    }

    /**
     * @return the municipio
     */
    public final Municipio getMunicipio() {
        return municipio;
    }

    /**
     * @param municipio the municipio to set
     */
    public final void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    /**
     * @return the telefone
     */
    public final Telefone getTelefone() {
        return telefone;
    }

    /**
     * @param telefone the telefone to set
     */
    public final void setTelefone(Telefone telefone) {
        this.telefone = telefone;
    }

}
