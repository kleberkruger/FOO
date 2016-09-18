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

import br.ufms.desafio.model.bean.Turma;
import br.ufms.desafio.model.bean.enumerate.Periodo;
import br.ufms.desafio.model.dao.DAOFactory;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kleberkruger
 */
public class TurmaDAOTester extends DAOTester<Turma, Long> {

    public TurmaDAOTester() {
        super(DAOFactory.getInstance().getTurmaDAO());
    }

    @Override
    protected void printBean(Turma bean) {
        System.out.println("Código: " + bean.getCodigo() + "\n" + 
                "Nome: " + bean.getNome()+ "\n" + 
                "Responsável: " + bean.getResponsavel()+ "\n");
    }

    @Override
    protected Turma createBean() {
        try {
            Turma t = new Turma();
            t.setNome("Fundamentos em Orientação a Objetos");
            t.setResponsavel(DAOFactory.getInstance().getProfessorDAO().get(1L));
            t.setPeriodo(Periodo.NOTURNO);
            
            return t;
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return null;
    }

    @Override
    protected void updateBean(Turma bean) {
        bean.setNome("Técnicas em Orientação a Objetos");
    }
    
}
