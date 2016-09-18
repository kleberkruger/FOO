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
public class EnderecoDAOTester extends DAOTester<Endereco, Long> {

    public EnderecoDAOTester() {
        super(DAOFactory.getInstance().getEnderecoDAO());
    }

    @Override
    protected void printBean(Endereco bean) {
        System.out.println(
                "Logradouro: " + bean.getLogradouro()
                + "\nNúmero: " + bean.getNumero()
                + "\nS/N: " + bean.getSemNumero()
                + "\nBairro: " + bean.getBairro()
                + "\nComplemento: " + bean.getComplemento()
                + "\nCEP: " + bean.getCEP()
        );
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
