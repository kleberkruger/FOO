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
package br.ufms.desafio.model.dao;

import br.ufms.desafio.model.bean.Bean;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Kleber Kruger
 *
 * @param <B>
 * @param <T>
 */
public abstract class ReadWriteDAO<B extends Bean<T>, T extends Serializable> extends ReadOnlyDAO<B, T> {

    public ReadWriteDAO(Class<B> clazz) {
        super(clazz);
    }

    /**
     * Insere o objeto Bean no banco de dados.
     *
     * @param bean
     * @param dependencies
     * @throws SQLException
     */
    public void insert(B bean, Serializable... dependencies) throws SQLException {
        try (Connection conn = db.getConnection()) {
            insert(conn, bean, dependencies);
        }
    }

    /**
     * Insere o objeto Bean no banco de dados.
     *
     * @param conn
     * @param bean
     * @param dependencies
     * @throws SQLException
     */
    protected abstract void insert(Connection conn, B bean, Serializable... dependencies) throws SQLException;

    /**
     * Atualiza o objeto Bean no banco de dados.
     *
     * @param bean
     * @throws SQLException
     */
    public void update(B bean) throws SQLException {
        try (Connection conn = db.getConnection()) {
            update(conn, bean);
        }
    }

    /**
     * Atualiza o objeto Bean no banco de dados.
     *
     * @param conn
     * @param bean
     * @throws SQLException
     */
    protected abstract void update(Connection conn, B bean) throws SQLException;

    /**
     * Salva (insere/atualiza) o objeto Bean no banco de dados.
     *
     * Para decidir se o objeto deve ser inserido ou atualizado, verifica-se o retorno do método
     * getCodigo(). Caso nulo, assume-se que o bean não existe no banco de dados, e portanto, deve
     * ser inserido. Caso contrário (já possui um código), apenas o atualiza.
     *
     * @param bean
     * @param dependencies
     *
     * @throws SQLException
     */
    public void save(B bean, Serializable... dependencies) throws SQLException {
        try (Connection conn = db.getConnection()) {
            save(conn, bean, dependencies);
        }
    }

    /**
     * Salva (insere/atualiza) o objeto Bean no banco de dados.
     *
     * Para decidir se o objeto deve ser inserido ou atualizado, verifica-se o retorno do método
     * getCodigo(). Caso nulo, assume-se que o bean não existe no banco de dados, e portanto, deve
     * ser inserido. Caso contrário (já possui um código), apenas o atualiza.
     *
     * @param conn
     * @param bean
     * @param dependencies
     *
     * @throws SQLException
     */
    public void save(Connection conn, B bean, Serializable... dependencies) throws SQLException {
        if (bean.getCodigo() == null) {
            insert(conn, bean, dependencies);
        } else {
            update(conn, bean);
        }
    }

    /**
     * Deleta o objeto Bean do banco de dados.
     *
     * @param bean
     * @throws SQLException
     */
    public void delete(B bean) throws SQLException {
        delete(bean.getCodigo());
    }

    /**
     * Deleta o objeto Bean do banco de dados.
     *
     * @param codigo
     * @throws SQLException
     */
    public void delete(T codigo) throws SQLException {
        try (Connection conn = db.getConnection()) {
            delete(conn, codigo);
        }
    }

    protected void delete(Connection conn, B bean) throws SQLException {
        delete(conn, bean.getCodigo());
    }

    /**
     * Deleta o objeto Bean do banco de dados.
     *
     * @param conn
     * @param codigo
     * @throws SQLException
     */
    protected abstract void delete(Connection conn, T codigo) throws SQLException;

}
