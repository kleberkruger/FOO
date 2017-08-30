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

import br.ufms.banking.model.dao.db.AgenciaDAO;
import br.ufms.banking.model.domain.Agencia;
import br.ufms.banking.model.domain.Municipio;
import br.ufms.banking.model.domain.NumeroBancario;
import br.ufms.banking.model.domain.Telefone;
import br.ufms.banking.model.enumerate.UF;
import br.ufms.kruger.util.persistence.MySQLDatabaseManager;

/**
 *
 * @author Kleber Kruger
 */
public class AgenciaDAOTester extends DAOTester<Agencia, NumeroBancario> {

    public AgenciaDAOTester() {
        super(new AgenciaDAO(MySQLDatabaseManager.getInstance()));
    }

    @Override
    protected void printBean(Agencia bean) {
        StringBuilder output = new StringBuilder();
        output.append("Agência: ").append(bean.getNumero()).append("-")
                .append(bean.getDigito()).append("\n");
        output.append("Município: ").append(bean.getMunicipio().getNome()).append(" - ").
                append(bean.getMunicipio().getUF().toString()).append("\n");
        output.append("Telefone: ").append(bean.getTelefone()).append("\n");
        System.out.println(output);
    }

    @Override
    protected Agencia createBean() {
        return new Agencia(new Municipio("Rio Verde", UF.MT), new Telefone("6732912828"));
    }

    @Override
    protected void updateBean(Agencia bean) {
        bean.setMunicipio(new Municipio("Coxim", UF.MS));
        bean.setTelefone(new Telefone("6732910200"));
    }

}
