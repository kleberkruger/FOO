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

import br.ufms.desafio.model.bean.Responsavel;
import br.ufms.desafio.model.dao.DAOFactory;
import java.time.LocalDate;

/**
 *
 * @author kleberkruger
 */
public class ResponsavelTester extends JogadorTester<Responsavel> {

    public ResponsavelTester() {
        super(DAOFactory.getInstance().getResponsavelDAO());
    }

    @Override
    protected void printBean(Responsavel bean) {
        super.printBean(bean);
        System.out.println("CPF: " + bean.getCPF());
    }

    @Override
    protected Responsavel createBean() {
        Responsavel a = new Responsavel();
        a.setNome("Menino Angelo");
        a.setEmail("garotinhoangelical@gmail.com");
        a.setUsuario("garotinhoangelical");
        a.setSenha("2424");
        a.setCPF("875.926.946-71");
        a.setNascimento(LocalDate.of(1985, 5, 8));
        return a;
    }

    @Override
    protected void updateBean(Responsavel bean) {
        System.out.println("Ângelo");
    }
    
}
