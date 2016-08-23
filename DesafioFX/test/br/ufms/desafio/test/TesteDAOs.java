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
import br.ufms.desafio.model.bean.Escola;
import br.ufms.desafio.model.bean.Municipio;
import br.ufms.desafio.model.dao.EnderecoDAO;
import br.ufms.desafio.model.dao.EscolaDAO;
import br.ufms.desafio.model.dao.MunicipioDAO;
import java.sql.SQLException;

/**
 *
 * @author kleberkruger
 */
public class TesteDAOs {

    public void inicializar() {
//        testeMunicipioDAO();
        testeEnderecoDAO();
//        testeEscolaDAO();
//        testeAlunoDAO();
//        testeResponsavelDAO();
//        testeProfessorDAO();
//        testeTurmaDAO();
    }

    protected void testeMunicipioDAO() {
        try {
            MunicipioDAO municipioDAO = new MunicipioDAO();
            Municipio municipio = municipioDAO.get(1L);
            System.out.println((municipio != null) ? municipio.getNome()
                    : "Município inexistente.");

            for (Municipio m : municipioDAO.getAll()) {
                System.out.println(m.getNome() + " - " + m.getUF());
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    protected void testeEnderecoDAO() {
        try {
            EnderecoDAO enderecoDAO = new EnderecoDAO();
            Endereco endereco = enderecoDAO.get(4L);
            System.out.println((endereco != null) ? endereco.getLogradouro()
                    : "Endereço inexistente.");

            for (Endereco m : enderecoDAO.getAll()) {
                System.out.println(m.getLogradouro() + " - " + m.getBairro());
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    protected void testeEscolaDAO() {
        try {
            EscolaDAO escolaDAO = new EscolaDAO();
            
//            Telefone tel = new Telefone();
//            tel.setDDD("67");
//            tel.setNumero("9-9939-5298");
//            List<Telefone> telList = new ArrayList<>();
//            telList.add(tel);
//            
//            Escola escola = new Escola();
//            escola.setEmail("pmf2@gmail.com");
//            escola.setNome("Pedro Mendes Fontoura 2");
//            escola.setSenha("123");
//            escola.setTipo(TipoEscola.ESTADUAL);
//            escola.setUsuario("pmf2");
//            escola.setTelefones(telList);
//            escolaDAO.save(escola);

            Escola escola = escolaDAO.get(2L);
            System.out.println(escola.getNome());

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    protected void testeAlunoDAO() {
    }

    protected void testeResponsavelDAO() {
    }

    protected void testeProfessorDAO() {
    }

    protected void testeTurmaDAO() {
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new TesteDAOs().inicializar();
    }

}
