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
package br.ufms.kruger.util.persistence;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;

/**
 *
 * @author Kleber Kruger
 *
 * @param <B>
 * @param <T>
 */
public abstract class ReadOnlyDAO<B extends Bean<T>, T extends Serializable> {

    protected final DatabaseManager db;
    protected final Class<B> beanClass;

    /**
     * Cria um ReadOnlyDAO para Beans do tipo B.
     *
     * @param db
     * @param clazz
     */
    public ReadOnlyDAO(DatabaseManager db, Class<B> clazz) {
        this.db = db;
        this.beanClass = clazz;
    }

    /**
     * Retorna o objeto gerenciador do banco de dados.
     *
     * @return the database manager
     */
    protected DatabaseManager getDatabaseManager() {
        return db;
    }

    /**
     * @return the beanClass
     */
    public Class<B> getBeanClass() {
        return beanClass;
    }

    /**
     * Carrega do banco de dados as informações do objeto Bean.
     *
     * @param id
     * @return
     * @throws SQLException
     */
    public B get(T id) throws SQLException {
        B bean;
        try (Connection conn = db.getConnection()) {
            bean = get(conn, id);
        }
        return bean;
    }

    protected abstract B get(Connection conn, T id) throws SQLException;

    /**
     * Carrega do banco de dados a lista de todos os obhetos do tipo Bean.
     *
     * @return
     * @throws SQLException
     */
    public Collection<B> getAll() throws SQLException {
        Collection<B> beans;
        try (Connection conn = db.getConnection()) {
            beans = getAll(conn);
        }
        return beans;
    }

    protected abstract Collection<B> getAll(Connection conn) throws SQLException;

//    protected abstract B resultSetToBean(Connection conn, ResultSet rs) throws SQLException;
//    protected abstract Class<? extends ReadOnlyDAO<B, T>> getDAOClass();
}
