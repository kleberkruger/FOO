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

import br.ufms.desafio.model.bean.Endereco;
import br.ufms.desafio.model.bean.Municipio;
import br.ufms.desafio.model.bean.enumerate.UF;
import br.ufms.desafio.model.dao.DAOFactory;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author kleberkruger
 */
public class EnderecoTester extends DAOTester<Endereco, Long> {

    public EnderecoTester() {
        super(DAOFactory.getInstance().getEnderecoDAO());
    }

    @Override
    protected void printBean(Endereco bean) {
        StringBuilder output = new StringBuilder();
        output.append("Código: ").append(bean.getCodigo()).append("\n");
        output.append("Logradouro: ").append(bean.getLogradouro()).append("\n");
        output.append("Número: ").append(bean.getNumero()).append("\n");
        output.append("S/N: ").append(bean.getSemNumero()).append("\n");
        output.append("Complemento: ").append(bean.getComplemento()).append("\n");
        output.append("Bairro: ").append(bean.getBairro()).append("\n");
        output.append("CEP: ").append(bean.getCEP()).append("\n");
        System.out.println(output);

        if (bean.getMunicipio() != null) {
            new MunicipioTester().printBean(bean.getMunicipio());
        }
    }

    @Override
    protected Endereco createBean() {

        Endereco e = new Endereco();

        e.setBairro("Santa Maria");
        e.setCEP("794000000");
        e.setCodigo(1l);
        e.setComplemento("Apt 11");
        e.setLogradouro("Rua Marcio Lima Nantes");
        e.setNumero((short) 1115);
        e.setSemNumero(false);

        Municipio m = new Municipio();
        m.setCodigo(1l);
        m.setNome("Coxim");
        m.setUF(UF.MS);

        e.setMunicipio(m);

        return e;
    }

    @Override
    protected void updateBean(Endereco bean) {
        bean.setLogradouro("Avenida Marcio Lima Nantes");
    }

    @Override
    protected void insertDependencyList(List<Serializable> dependencies) {
        dependencies.add(1l);
    }

}
