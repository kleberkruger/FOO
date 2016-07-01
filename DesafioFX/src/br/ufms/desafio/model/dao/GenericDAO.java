/*
 * Copyright (C) 2016 angelino.caon
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
package br.ufms.desafio.model.dao;

import br.ufms.desafio.model.bean.Bean;
import br.ufms.desafio.util.Database;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kleber Kruger
 * @param <T>
 */
public abstract class GenericDAO<T extends Bean> {

    protected Database db = new Database();
    protected Class<T> clazz;

    public GenericDAO() {

    }

    public GenericDAO(Class<T> clazz) {
        this.clazz = clazz;
    }

    /**
     * @return the db
     */
    public Database getDatabase() {
        return db;
    }

    public abstract void save(T bean) throws SQLException;

    public abstract void update(T bean) throws SQLException;

    public void saveOrUpdate(T bean) throws SQLException {
        if (bean.getCodigo() == null) {
            save(bean);
        } else {
            update(bean);
        }
    }

    public void delete(T bean) throws SQLException {
        delete(bean.getCodigo());
    }

    public abstract void delete(long codigo) throws SQLException;

    public abstract T get(long codigo) throws SQLException;

    public abstract List<T> getAll() throws SQLException;
}
