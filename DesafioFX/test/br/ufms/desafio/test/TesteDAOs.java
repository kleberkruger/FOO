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

/**
 *
 * @author kleberkruger
 */
public class TesteDAOs {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        new AlunoDAOTester().start();
        new EnderecoDAOTester().start();
//        new EscolaDAOTester().start();
        new MunicipioDAOTester().start();
//        new ProfessorDAOTester().start();
//        new ResponsavelDAOTester().start();
        new TelefoneDAOTester().start();
        new TurmaDAOTester().start();
    }

}
