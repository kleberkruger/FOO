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

import br.ufms.banking.util.Validador;
import br.ufms.kruger.util.persistence.Bean;

/**
 * Esta classe representa a entidade Banco.
 *
 * Observação: Embora um banco possua uma lista de agências, criar um objeto banco com essa
 * informação poderia gerar consultas desnecessárias no banco de dados, uma vez cada objeto
 * Agencia é composto por uma grande quantidade de informações.
 *
 * @author Kleber Kruger
 */
public class Banco extends Bean<Short> {

    private static final long serialVersionUID = 1L;
    
    public static final short UFMSBANKING_CODIGO = 100;

    private String nome;

    /**
     * Cria um objeto Banco.
     *
     * @param codigo
     * @param nome
     */
    public Banco(Short codigo, String nome) {
        super(codigo);

        this.setNome(nome);
    }

    /**
     * @return the codigo
     */
    public final Short getCodigo() {
        return super.getID();
    }

    /**
     * @return the nome
     */
    public final String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public final void setNome(String nome) {
        Validador.validarNomeFantasia(nome);
        this.nome = nome;
    }

}
