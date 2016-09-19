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

import br.ufms.desafio.model.bean.Telefone;
import br.ufms.desafio.model.bean.enumerate.TipoTelefone;
import br.ufms.desafio.model.dao.DAOFactory;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author kleberkruger
 */
public class TelefoneTester extends DAOTester<Telefone, Long> {

    public TelefoneTester() {
        super(DAOFactory.getInstance().getTelefoneDAO());
    }

    @Override
    protected void printBean(Telefone bean) {
        StringBuilder output = new StringBuilder();
        output.append("Código: ").append(bean.getCodigo()).append("\n");
        output.append("DDD: ").append(bean.getDDD()).append("\n");
        output.append("Número: ").append(bean.getNumero()).append("\n");
        output.append("Tipo: ").append(bean.getTipo()).append("\n");
        output.append("Principal: ").append(bean.getPrincipal()).append("\n");
        System.out.println(output);
    }

    @Override
    protected Telefone createBean() {
        Telefone tel = new Telefone();

        tel.setDDD("67");
        tel.setNumero("999-999-999");
        tel.setTipo(TipoTelefone.CELULAR);
        tel.setPrincipal(true);
        return tel;
    }

    @Override
    protected void updateBean(Telefone bean) {
        bean.setNumero("999-999-998");
        bean.setTipo(TipoTelefone.FAX_COMERCIAL);
        bean.setPrincipal(true);
    }

    @Override
    protected void insertDependencyList(List<Serializable> dependencies) {
        dependencies.add(1L);
    }
}
