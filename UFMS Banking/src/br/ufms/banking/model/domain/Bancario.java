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

import br.ufms.banking.model.enumerate.TipoUsuario;
import br.ufms.banking.util.Validador;

/**
 *
 * @author Kleber Kruger
 */
public class Bancario extends Usuario {

    private static final long serialVersionUID = 1L;

    private String nome;
    private CPF cpf;
    private Agencia agencia;
    
     /**
     * Cria um novo objeto Bancario.
     *
     * @param nome
     * @param usuario
     * @param senha
     * @param cpf
     * @param agencia
     */
    public Bancario(String nome, String usuario, String senha, CPF cpf, Agencia agencia) {
        super(usuario, senha);

        setNome(nome);
        setCPF(cpf);
        setAgencia(agencia);
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
        Validador.validarNome(nome);
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
            throw new IllegalArgumentException("O CPF não pode ser nulo.");
        }
        this.cpf = cpf;
    }
    
    /**
     * @return the agencia
     */
    public final Agencia getAgencia() {
        return agencia;
    }

    /**
     * @param agencia the agencia to set
     */
    public final void setAgencia(Agencia agencia) {
        if (agencia == null) {
            throw new IllegalArgumentException("A agência não pode ser nula.");
        }
        this.agencia = agencia;
    }

    @Override
    public TipoUsuario getTipoUsuario() {
        return TipoUsuario.BANCARIO;
    }

}
