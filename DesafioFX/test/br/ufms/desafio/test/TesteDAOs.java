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
import br.ufms.desafio.model.bean.enumerate.Deficiencia;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author kleberkruger
 */
public class TesteDAOs {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Telefone tel = new Telefone();
        tel.setDDD("67");
        tel.setDDD("(67)");
        tel.setNumero("32914816");
        tel.setNumero("3291-4816");
        tel.setNumero("998393619");
        tel.setNumero("99839-3619");
        tel.setNumero("9-9839-3619");
        System.out.println(tel);

//        Connection conn;
//        try {
//            conn = DatabaseManager.getInstance().getConnection();
//            PreparedStatement ps = conn.prepareStatement("INSERT INTO desafio.usuario (nome, email, "
//                    + "usuario, senha, data_criacao) VALUES(?, ?, ?, ?, ?)");
//            
//            ps.setString(1, "a");
//            ps.setString(2, "a");
//            ps.setString(3, "a");
//            ps.setString(4, "a");
//            LocalDate d = null;
//            ps.setDate(5, Date.valueOf(d));
//            ps.executeUpdate();
//            
//        } catch (SQLException ex) {
//            System.err.println(ex.getMessage());
//        }

        Set<Deficiencia> defs = new HashSet<>();
        defs.add(Deficiencia.MENTAL);
        defs.add(Deficiencia.FISICA);
        
        String str = defs.toString();
        str = str.substring(1, str.length() - 1).replace(" ", "");
        System.out.println(str);

//        new AlunoDAOTester().start();
//        new EnderecoDAOTester().start();
//        new EscolaDAOTester().start();
//        new MunicipioDAOTester().start();
        new ProfessorDAOTester().start();
//        new ResponsavelDAOTester().start();
//        new TelefoneDAOTester().start();
//        new TurmaDAOTester().start();
    }

}
