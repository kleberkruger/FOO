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

import br.ufms.desafio.model.bean.Municipio;
import br.ufms.desafio.model.bean.enumerate.UF;
import br.ufms.desafio.model.dao.DAOFactory;

/**
 *
 * @author kleberkruger
 */
public class MunicipioDAOTester extends DAOTester<Municipio, Long> {

    public MunicipioDAOTester() {
        super(DAOFactory.getInstance().getMunicipioDAO());
    }

    @Override
    protected void printBean(Municipio bean) {
        System.out.println("Código IBGE: " + bean.getCodigo() + "\n"
                + "Nome: " + bean.getNome() + "\n"
                + "UF: " + bean.getUF().toString());
    }

    @Override
    protected Municipio createBean() {
        Municipio m = new Municipio();
        m.setCodigo(1L);
        m.setNome("Coxim");
        m.setUF(UF.MS);
        return m;
    }

    @Override
    protected void updateBean(Municipio bean) {
        bean.setNome("Rio Verde");
    }

}
