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
import java.time.format.DateTimeFormatter;

/**
 *
 * @author kleberkruger
 * @param <B>
 */
public abstract class UsuarioTester<B extends Usuario> extends DAOTester<B, Long> {

    public UsuarioTester(UsuarioDAO<B> dao) {
        super(dao);
    }

    @Override
    protected void printBean(B bean) {
        StringBuilder output = new StringBuilder();
        output.append("Código: ").append(bean.getCodigo()).append("\n");
        output.append("Nome: ").append(bean.getNome()).append("\n");
        output.append("Email: ").append(bean.getEmail()).append("\n");
        output.append("Usuário: ").append(bean.getUsuario()).append("\n");
        output.append("Senha: ").append(bean.getSenha()).append("\n");
        output.append("Data de criação: ").append(bean.getCriacao().format(DateTimeFormatter.ISO_DATE)).append("\n");
        System.out.println(output);
        
        new EnderecoTester().printBean(bean.getEndereco());

        TelefoneTester telDAOTester = new TelefoneTester();
        bean.getTelefones().stream().forEach((tel) -> {
            telDAOTester.printBean(tel);
        });
    }

    @Override
    protected abstract B createBean();

    @Override
    protected abstract void updateBean(B bean);

}
