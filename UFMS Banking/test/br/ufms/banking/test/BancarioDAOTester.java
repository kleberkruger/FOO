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
import br.ufms.banking.model.dao.db.BancarioDAO;
import br.ufms.banking.model.domain.Bancario;
import br.ufms.banking.model.domain.CPF;
import br.ufms.banking.model.domain.NumeroBancario;
import br.ufms.kruger.util.persistence.MySQLDatabaseManager;
import java.sql.SQLException;

/**
 *
 * @author Kleber Kruger
 */
public class BancarioDAOTester extends UsuarioDAOTester<Bancario> {

    public BancarioDAOTester() {
        super(new BancarioDAO(MySQLDatabaseManager.getInstance()));
    }

    @Override
    protected void printBean(Bancario bean) {
        super.printBean(bean);

        StringBuilder output = new StringBuilder();
        output.append("Nome: ").append(bean.getNome()).append("\n");
        output.append("CPF: ").append(bean.getCPF()).append("\n");
        System.out.println(output);

        new AgenciaDAOTester().printBean(bean.getAgencia());
    }

    @Override
    protected Bancario createBean() {
        try {
            AgenciaDAO agenciaDAO = new AgenciaDAO(MySQLDatabaseManager.getInstance());
            return new Bancario("Gederson Vaz", "gedersonvaz", "123", new CPF("02135730165"),
                    agenciaDAO.get(new NumeroBancario(1)));
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return null;
    }

    @Override
    protected void updateBean(Bancario bean) {
        bean.setNome("Gedson Faria");
        bean.setCPF(new CPF("02135730165"));
        bean.setUsuario("gedsonfaria");
        bean.setSenha("123");
    }

}
