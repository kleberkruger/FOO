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

import br.ufms.desafio.model.bean.Jogador;
import br.ufms.desafio.model.dao.JogadorDAO;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author kleberkruger
 * @param <B>
 */
public abstract class JogadorDAOTester<B extends Jogador> extends UsuarioDAOTester<B> {

    public JogadorDAOTester(JogadorDAO<B> dao) {
        super(dao);
    }

    @Override
    protected void printBean(B bean) {
        super.printBean(bean);
        System.out.println("Data de nascimento: " + bean.getNascimento().format(DateTimeFormatter.ISO_DATE) + "\n"
                + "Deficiências: \n");
        bean.getDeficiencias().stream().forEach((d) -> {
            System.out.println(d.toString());
        });
    }

    @Override
    protected abstract B createBean();

    @Override
    protected abstract void updateBean(B bean);
    
}
