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
package br.ufms.bank.model;

/**
 *
 * @author Kleber Kruger
 */
public class PessoaFisica extends Correntista {

    private static final long serialVersionUID = 1L;

    private String nome;
    private CPF cpf;

    /**
     * Cria um objeto PessoaFisica.
     *
     * @param nome
     * @param usuario
     * @param senha
     * @param cpf
     */
    public PessoaFisica(String nome, String usuario, String senha, CPF cpf) {
        this(nome, usuario, senha, cpf, null);
    }

    /**
     * Cria um objeto PessoaFisica.
     *
     * @param nome
     * @param usuario
     * @param senha
     * @param cpf
     * @param telefone
     */
    public PessoaFisica(String nome, String usuario, String senha, CPF cpf, Telefone telefone) {
        this(nome, usuario, senha, cpf, telefone, null);
    }

    /**
     * Cria um objeto PessoaFisica.
     *
     * @param nome
     * @param usuario
     * @param senha
     * @param cpf
     * @param telefonePrincipal
     * @param telefoneSecundario
     */
    public PessoaFisica(String nome, String usuario, String senha, CPF cpf,
            Telefone telefonePrincipal, Telefone telefoneSecundario) {

        this(null, nome, usuario, senha, cpf, telefonePrincipal, telefoneSecundario);
    }

    /**
     * Cria um objeto PessoaFisica.
     *
     * @param id
     * @param nome
     * @param usuario
     * @param senha
     * @param cpf
     * @param telefonePrincipal
     * @param telefoneSecundario
     */
    protected PessoaFisica(Long id, String nome, String usuario, String senha, CPF cpf,
            Telefone telefonePrincipal, Telefone telefoneSecundario) {

        super(id, usuario, senha, telefonePrincipal, telefoneSecundario);

        setNome(nome);
        setCPF(cpf);
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
//        Validador.validarNome(nome);
        this.nome = nome;
    }

    /**
     * @return the cpf
     */
    public final CPF getCPF() {
        return cpf;
    }

    /**
     * @param cpf the cpf to set
     */
    public final void setCPF(CPF cpf) {
        if (cpf == null) {
            throw new IllegalArgumentException("O CPF não pode ser nulo");
        }
        this.cpf = cpf;
    }

}
