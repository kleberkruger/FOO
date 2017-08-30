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
package br.ufms.banking.model.dao.db;

import br.ufms.banking.model.domain.Usuario;
import br.ufms.kruger.util.persistence.DatabaseManager;
import br.ufms.kruger.util.persistence.ReadWriteDAO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedHashSet;

/**
 *
 * @author Kleber Kruger
 * @param <U>
 */
public abstract class UsuarioDAO<U extends Usuario> extends ReadWriteDAO<U, Long> {

    public UsuarioDAO(DatabaseManager db, Class<U> clazz) {
        super(db, clazz);
    }

    @Override
    protected void insert(Connection conn, U bean, Serializable... dependencies) throws SQLException {
        String sql = "INSERT INTO ufms_banking.tb_usuario (us_usuario, us_senha, us_tipo) VALUES (?, ?, ?)";

        conn.setAutoCommit(false);

        try (PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, bean.getUsuario());
            ps.setString(2, bean.getSenha());
            ps.setString(3, bean.getTipoUsuario().toString());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.first()) {
                    setBeanGeneratedKey(bean, rs.getLong(1));
                }
            }

            insertAbst(conn, bean);
            conn.commit();

        } catch (SQLException ex) {
            conn.rollback();
            throw ex;
        } finally {
            conn.setAutoCommit(true);
        }
    }

    @Override
    protected void update(Connection conn, U bean) throws SQLException {
        String sql = "UPDATE ufms_banking.tb_usuario SET us_usuario = ?, us_senha = ? WHERE us_id = ?";

        conn.setAutoCommit(false);

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, bean.getUsuario());
            ps.setString(2, bean.getSenha());
            ps.setLong(3, bean.getID());
            ps.executeUpdate();

            updateAbst(conn, bean);

        } catch (SQLException ex) {
            conn.rollback();
            throw ex;
        } finally {
            conn.setAutoCommit(true);
        }
    }

    @Override
    protected void delete(Connection conn, Long id) throws SQLException {
        String sql = "DELETE FROM ufms_banking.tb_usuario WHERE us_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        }
    }

    @Override
    protected U get(Connection conn, Long id) throws SQLException {
        U usuario = null;
        try (PreparedStatement ps = conn.prepareStatement(sqlToGet(id))) {
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.first()) {
                    usuario = resultSetToBean(conn, rs);
                }
            }
        }
        return usuario;
    }

    @Override
    protected Collection<U> getAll(Connection conn) throws SQLException {
        Collection<U> usuarios = new LinkedHashSet<>();
        try (PreparedStatement ps = conn.prepareStatement(sqlToGetAll())) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    usuarios.add(resultSetToBean(conn, rs));
                }
            }
        }
        return usuarios;
    }

    protected abstract void insertAbst(Connection conn, U bean) throws SQLException;

    protected abstract void updateAbst(Connection conn, U bean) throws SQLException;
    
    protected abstract String sqlToGet(Long id);
    
    protected abstract String sqlToGetAll();

    protected abstract U resultSetToBean(Connection conn, ResultSet rs) throws SQLException;

}
