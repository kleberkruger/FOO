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

/**
 *
 * @author Kleber Kruger
 */
public class PessoaJuridica extends Correntista {

    private static final long serialVersionUID = 1L;

    private String nomeFantasia;
    private String razaoSocial;
    private CNPJ cnpj;

    /**
     * Cria um objeto PessoaJuridica.
     *
     * @param nomeFantasia
     * @param razaoSocial
     * @param usuario
     * @param senha
     * @param cnpj
     */
    public PessoaJuridica(String nomeFantasia, String razaoSocial,
            String usuario, String senha, CNPJ cnpj) {

        this(nomeFantasia, razaoSocial, usuario, senha, cnpj, null);
    }

    /**
     * Cria um objeto PessoaJuridica.
     *
     * @param nomeFantasia
     * @param razaoSocial
     * @param usuario
     * @param senha
     * @param cnpj
     * @param telefonePrincipal
     */
    public PessoaJuridica(String nomeFantasia, String razaoSocial,
            String usuario, String senha, CNPJ cnpj, Telefone telefonePrincipal) {

        this(nomeFantasia, razaoSocial, usuario, senha, cnpj, telefonePrincipal, null);
    }

    /**
     * Cria um objeto PessoaJuridica.
     *
     * @param nomeFantasia
     * @param razaoSocial
     * @param usuario
     * @param senha
     * @param cnpj
     * @param telefonePrincipal
     * @param telefoneSecundario
     */
    public PessoaJuridica(String nomeFantasia, String razaoSocial, String usuario, String senha,
            CNPJ cnpj, Telefone telefonePrincipal, Telefone telefoneSecundario) {

        this(null, nomeFantasia, razaoSocial, usuario, senha, cnpj, telefonePrincipal, telefoneSecundario);
    }

    /**
     * Cria um objeto PessoaJuridica.
     *
     * @param id
     * @param nomeFantasia
     * @param razaoSocial
     * @param usuario
     * @param senha
     * @param cnpj
     * @param telefonePrincipal
     * @param telefoneSecundario
     */
    protected PessoaJuridica(Long id, String nomeFantasia, String razaoSocial, String usuario, String senha,
            CNPJ cnpj, Telefone telefonePrincipal, Telefone telefoneSecundario) {

        super(id, usuario, senha, telefonePrincipal, telefoneSecundario);

        setNomeFantasia(nomeFantasia);
        setRazaoSocial(razaoSocial);
        setCNPJ(cnpj);
    }

    /**
     * @return the nomeFantasia
     */
    public final String getNomeFantasia() {
        return nomeFantasia;
    }

    /**
     * @param nomeFantasia the nomeFantasia to set
     */
    public final void setNomeFantasia(String nomeFantasia) {
        Validador.validarNomeFantasia(nomeFantasia);
        this.nomeFantasia = nomeFantasia;
    }

    /**
     * @return the razaoSocial
     */
    public final String getRazaoSocial() {
        return razaoSocial;
    }

    /**
     * @param razaoSocial the razaoSocial to set
     */
    public final void setRazaoSocial(String razaoSocial) {
        Validador.validarRazaoSocial(razaoSocial);
        this.razaoSocial = razaoSocial;
    }

    /**
     * @return the cnpj
     */
    public final CNPJ getCNPJ() {
        return cnpj;
    }

    /**
     * @param cnpj the cnpj to set
     */
    public final void setCNPJ(CNPJ cnpj) {
        this.cnpj = cnpj;
    }

}
