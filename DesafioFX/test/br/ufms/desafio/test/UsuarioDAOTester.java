/*
 * Copyright (C) 2016 kleberkruger
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
package br.ufms.desafio.test;

import br.ufms.desafio.model.bean.Usuario;
import br.ufms.desafio.model.dao.UsuarioDAO;

/**
 *
 * @author kleberkruger
 * @param <B>
 */
public abstract class UsuarioDAOTester<B extends Usuario> extends DAOTester<B, Long> {

    public UsuarioDAOTester(UsuarioDAO<B> dao) {
        super(dao);
    }

    @Override
    protected void printBean(B bean) {

        System.out.print("Código: " + bean.getCodigo() + "\n"
                + "Nome: " + bean.getNome() + "\n"
                + "Email: " + bean.getEmail() + "\n"
                + "Usuario: " + bean.getUsuario() + "\n"
                + "Senha: " + bean.getSenha() + "\n"
                + "Data de criação: " + bean.getCriacao() + "\n");

        new EnderecoDAOTester().printBean(bean.getEndereco());

        TelefoneDAOTester telDAOTester = new TelefoneDAOTester();
        bean.getTelefones().stream().forEach((tel) -> {
            telDAOTester.printBean(tel);
        });
    }

    @Override
    protected abstract B createBean();

    @Override
    protected abstract void updateBean(B bean);

}
