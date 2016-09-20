/*
 * Copyright (C) 2016 Kleber Kruger
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
package br.ufms.desafio.app;

import br.ufms.desafio.model.bean.Aluno;
import br.ufms.desafio.model.bean.Escola;
import br.ufms.desafio.model.bean.enumerate.TipoEscola;
import br.ufms.desafio.model.dao.DAOFactory;
import br.ufms.desafio.model.dao.EscolaDAO;
import br.ufms.desafio.model.dao.MunicipioDAO;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Classe Application que contém o método main deste programa.
 *
 * @author Kleber Kruger
 */
public class DesafioFXApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // Exemplo de utilização
        try {
            EscolaDAO escolaDAO = DAOFactory.getInstance().getEscolaDAO();
            System.out.println("Escolas cadastradas: ");
            for (Escola e : escolaDAO.getAll()) {
                System.out.println(e.getNome());
            }
            Escola escola = new Escola();
            escola.setNome("Pedro Mendes Fontoura");
            escola.setUsuario("ee.pmf");
            escola.setSenha("123");
            escola.setEmail("pmf@gmail.com");
            escola.setTipo(TipoEscola.ESTADUAL);

            escolaDAO.save(escola);
            
            System.out.println("Escola inserida com sucesso.");
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

}
