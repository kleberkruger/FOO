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
package br.ufms.desafio.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe que gerencia as conexões com o banco de dados.
 *
 * @author Kleber Kruger
 */
public class DatabaseManager {
    
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/desafio";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    /**
     * Cria um objeto Database.
     *
     * Este construtor é privado, pois esta classe segue o padrão de projeto
     * "Singleton".
     *
     * Na criação deste objeto, o driver do banco de dados é prontamente
     * carregado. Caso ocorra uma exceção neste momento, verifique se a
     * biblioteca do MySQL foi adicionada a este projeto.
     */
    private DatabaseManager() {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException ex) {
            System.err.println(ex.getMessage());
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex.getMessage());
        }
    }

    /**
     * Retorna a única instância desta classe.
     *
     * @return the instance
     */
    public static DatabaseManager getInstance() {
        return DatabaseManagerHolder.INSTANCE;
    }

    /**
     * Estabelece uma conexão com o banco de dados.
     *
     * @return a conexão com o banco de dados.
     *
     * @throws SQLException
     */
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    /**
     * Classe privada que armazena a única instância de DatabaseManager.
     */
    private static class DatabaseManagerHolder {

        private static final DatabaseManager INSTANCE = new DatabaseManager();
    }
    
}
