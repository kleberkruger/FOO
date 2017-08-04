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
import br.ufms.bank.util.Validador;

/**
 *
 * @author Kleber Kruger
 */
public abstract class Usuario extends Bean<Long> {

    private static final long serialVersionUID = 1L;

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
        Validador.validarUsuario(usuario);
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
        Validador.validarSenha(senha);
        this.senha = senha;
    }

    /**
     * @return the tipo
     */
    public abstract TipoUsuario getTipo();

}
