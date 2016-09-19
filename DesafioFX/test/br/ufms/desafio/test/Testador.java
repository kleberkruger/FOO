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
public class Testador {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

//        Telefone tel = new Telefone();
//        tel.setDDD("67");
//        tel.setDDD("(67)");
//        tel.setNumero("32914816");
//        tel.setNumero("3291-4816");
//        tel.setNumero("998393619");
//        tel.setNumero("99839-3619");
//        tel.setNumero("9-9839-3619");
//        System.out.println(tel);
//        Set<Deficiencia> defs = new HashSet<>();
//        defs.add(Deficiencia.MENTAL);
//        defs.add(Deficiencia.FISICA);
//        
//        String str = defs.toString();
//        str = str.substring(1, str.length() - 1).replace(" ", "");
//        System.out.println(str);
//
        new MunicipioTester().start();
//        new EnderecoDAOTester().start();
//        new TelefoneDAOTester().start();

        new EscolaTester().start();
        new AlunoTester().start();
        new ProfessorTester().start();
        new ResponsavelTester().start();

//        new TurmaDAOTester().start();
    }

}
