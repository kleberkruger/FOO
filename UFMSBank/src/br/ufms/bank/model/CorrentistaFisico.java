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
public class CorrentistaFisico extends Correntista {

    private static final long serialVersionUID = 1L;
    
    private static final int TAMANHO_MIN_NOME = 4;

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
    public CorrentistaFisico(String nome, String usuario, String senha, CPF cpf) {
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
    public CorrentistaFisico(String nome, String usuario, String senha, CPF cpf, Telefone telefone) {
        super(usuario, senha);
        setNome(nome);
        setCPF(cpf);
        setTelefone(telefone);
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
        if (nome == null) {
            throw new IllegalArgumentException("O nome não pode ser nulo");
        }

        // Remove os espaços do início e final da String caso exista.
        nome = nome.trim();

        if (nome.isEmpty()) {
            throw new IllegalArgumentException("O nome não pode ser vazio");
        } else if (nome.length() < TAMANHO_MIN_NOME) {
            throw new IllegalArgumentException("Nome muito curto");
        }
        for (char c : nome.toCharArray()) {
            if (!Character.isAlphabetic(c) && !Character.isSpaceChar(c)) {
                throw new IllegalArgumentException("Nome inválido");
            }
        }

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
