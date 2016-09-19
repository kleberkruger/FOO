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
package br.ufms.desafio.model.dao;

import br.ufms.desafio.model.bean.Serie;
import br.ufms.desafio.model.bean.enumerate.NivelEnsino;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kleberkruger
 */
public class SerieDAO extends ReadWriteDAO<Serie, Long> {

    public SerieDAO() {
        super(Serie.class);
    }

    /**
     *
     * @param conn
     * @param bean
     * @param dependencies
     * @throws SQLException
     */
    @Override
    protected void insert(Connection conn, Serie bean, Serializable... dependencies) throws SQLException {
        if (dependencies.length < 1) {
            throw new IllegalArgumentException("Dependência 'codigo_usuario' não informada");
        } else if (!(dependencies[0] instanceof Long)) {
            throw new ClassCastException("Dependência 'codigo_usuario' informada com tipo incorreto: "
                    + dependencies[0].getClass().getName() + " cannot be cast to java.lang.Long");
        }

        final String sql = "INSERT INTO desafio.serie (codigo_usuario, ano, nivel) VALUES (?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setLong(1, (long) dependencies[0]);
            ps.setObject(2, bean.getAno());
            ps.setObject(3, bean.getNivel());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.first()) {
                    bean.setCodigo(rs.getLong(1));
                }
            }
        }
    }

    /**
     *
     * @param conn
     * @param bean
     * @throws SQLException
     */
    @Override
    protected void update(Connection conn, Serie bean) throws SQLException {
        final String sql = "UPDATE desafio.serie SET ano = ?, nivel = ? WHERE codigo = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setObject(1, bean.getAno());
            ps.setObject(2, bean.getNivel());
            ps.setLong(3, bean.getCodigo());
            ps.executeUpdate();
        }
    }

    /**
     *
     * @param conn
     * @param codigo
     * @throws SQLException
     */
    @Override
    protected void delete(Connection conn, Long codigo) throws SQLException {
        final String sql = "DELETE FROM desafio.serie WHERE codigo = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, codigo);
            ps.executeUpdate();
        }
    }

    /**
     *
     * @param conn
     * @param codigo
     * @return
     * @throws SQLException
     */
    @Override
    protected Serie get(Connection conn, Long codigo) throws SQLException {
        final String sql = "SELECT * FROM desafio.telefone WHERE codigo = ?";
        Serie serie = null;
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, codigo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.first()) {
                    serie = resultSetToBean(rs);
                }
            }
        }
        return serie;
    }

    /**
     *
     * @param conn
     * @return
     * @throws SQLException
     */
    @Override
    protected List<Serie> getAll(Connection conn) throws SQLException {
        final String sql = "SELECT * FROM desafio.telefone";
        List<Serie> series = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    series.add(resultSetToBean(rs));
                }
            }
        }
        return series;
    }

    /**
     *
     * @param rs
     * @return
     * @throws SQLException
     */
    private Serie resultSetToBean(ResultSet rs) throws SQLException {
        final String nivel = rs.getString("nivel");
        Serie serie = new Serie();
        serie.setCodigo(rs.getLong("codigo"));
        serie.setAno(rs.getShort("ano"));
        serie.setNivel(nivel != null ? NivelEnsino.valueOf(nivel) : null);
        return serie;
    }

    public Serie findByUsuario(Connection conn, Long codigoUsuario) throws SQLException {
        final String sql = "SELECT * FROM desafio.serie WHERE codigo_usuario = ?";
        Serie serie = null;
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, codigoUsuario);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.first()) {
                    serie = resultSetToBean(rs);
                }
            }
        }
        return serie;
    }
}
