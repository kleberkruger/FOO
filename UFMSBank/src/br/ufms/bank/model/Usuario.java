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

import br.ufms.bank.model.enumerate.TipoUsuario;

/**
 *
 * @author Kleber Kruger
 */
public abstract class Usuario extends Bean<Long> {
    
    private static final long serialVersionUID = 1L;

    private static final int TAMANHO_MIN_USUARIO = 3;
    private static final int TAMANHO_MIN_SENHA = 3;

    private String usuario;
    private String senha;

    /**
     * Cria um usuário.
     *
     * @param id
     * @param usuario
     * @param senha
     */
    public Usuario(Long id, String usuario, String senha) {
        super(id);

        setUsuario(usuario);
        setSenha(senha);
    }

    /**
     * @return the usuario
     */
    public final String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public final void setUsuario(String usuario) {
        if (usuario == null) {
            throw new IllegalArgumentException("O nome de usuário não pode ser nulo");
        }

        // Remove os espaços do início e final da String caso exista.
        usuario = usuario.trim();

        if (usuario.isEmpty()) {
            throw new IllegalArgumentException("O nome de usuário não pode ser vazio");
        } else if (usuario.length() < TAMANHO_MIN_USUARIO) {
            throw new IllegalArgumentException("Nome de usuário muito curto");
        }
        for (char c : usuario.toCharArray()) {
            if (!Character.isAlphabetic(c) && !Character.isDigit(c)) {
                throw new IllegalArgumentException("Nome de usuário inválido");
            }
        }

        this.usuario = usuario;
    }

    /**
     * @return the senha
     */
    public final String getSenha() {
        return senha;
    }

    /**
     * @param senha the senha to set
     */
    public final void setSenha(String senha) {
        if (senha == null) {
            throw new IllegalArgumentException("A senha não pode ser nula");
        } else if (senha.isEmpty()) {
            throw new IllegalArgumentException("A senha não pode ser vazia");
        } else if(senha.length() < TAMANHO_MIN_SENHA) {
            throw new IllegalArgumentException("Senha muito curta");
        }
        this.senha = senha;
    }

    /**
     * @return the tipo
     */
    public abstract TipoUsuario getTipo();

}
