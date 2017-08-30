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
package br.ufms.banking.test;

import br.ufms.banking.model.domain.Usuario;
import br.ufms.kruger.util.persistence.ReadOnlyDAO;

/**
 *
 * @author Kleber Kruger
 * @param <U>
 */
public abstract class UsuarioDAOTester<U extends Usuario> extends DAOTester<U, Long> {

    public UsuarioDAOTester(ReadOnlyDAO<U, Long> dao) {
        super(dao);
    }

    @Override
    protected void printBean(U bean) {
        StringBuilder output = new StringBuilder();
        output.append("ID: ").append(bean.getID()).append("\n");
        output.append("Usuário: ").append(bean.getUsuario()).append("\n");
        output.append("Senha: ").append(bean.getSenha()).append("\n");
        output.append("Tipo de Usuário: ").append(bean.getTipoUsuario()).append("\n");
        System.out.println(output);
    }

    @Override
    protected abstract U createBean();

    @Override
    protected abstract void updateBean(U bean);

}
