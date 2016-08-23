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

import br.ufms.desafio.model.bean.Escola;
import br.ufms.desafio.model.bean.enumerate.TipoEscola;
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
public class EscolaDAO extends UsuarioDAO<Escola> {

    public EscolaDAO() {
        super(Escola.class);
    }

    private void insertEscola(Connection conn, Escola bean) throws SQLException {
        final String sql = "INSERT INTO desafio.escola (codigo, tipo) VALUES (?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, bean.getCodigo());
            ps.setString(2, bean.getTipo().toString());
            ps.executeUpdate();
        }
    }

    private void updateEscola(Connection conn, Escola bean) throws SQLException {
        final String sql = "UPDATE desafio.escola SET (tipo = ?) WHERE codigo = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, bean.getTipo().toString());
            ps.setLong(2, bean.getCodigo());
            ps.executeUpdate();
        }
    }

    protected void saveEscola(Connection conn, Escola bean) throws SQLException {
        if (bean.getCodigo() == null) {
            insertEscola(conn, bean);
        } else {
            updateEscola(conn, bean);
        }
    }

    @Override
    protected void insert(Connection conn, Escola bean, Serializable... dependencies) throws SQLException {
        conn.setAutoCommit(false);
        try {
            super.insert(conn, bean, dependencies);
            insertEscola(conn, bean);
            conn.commit();

        } catch (SQLException ex) {
            conn.rollback();
            throw ex;
        } finally {
            conn.setAutoCommit(true);
        }
    }

    @Override
    protected void update(Connection conn, Escola bean) throws SQLException {
        conn.setAutoCommit(false);
        try {
            super.update(conn, bean);
            updateEscola(conn, bean);
            conn.commit();

        } catch (SQLException ex) {
            conn.rollback();
            throw ex;
        } finally {
            conn.setAutoCommit(true);
        }
    }

    protected List<Escola> findByProfessor(Connection conn, Long codigo) throws SQLException {
        final String sql = "SELECT * FROM desafio.usuario u JOIN desafio.escola e ON u.codigo = e.codigo "
                + "JOIN desafio.escola_professor ep ON e.codigo = ep.codigo_escola WHERE ep.codigo_professor = ?";
        List<Escola> beans = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, codigo);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    beans.add(resultSetToBean(conn, rs));
                }
            }
        }
        return beans;
    }

    @Override
    protected String sqlToGet(Long codigo) {
        return "SELECT * FROM desafio.usuario u, desafio.escola e WHERE u.codigo = " + codigo;
    }

    @Override
    protected String sqlToGetAll() {
        return "SELECT * FROM desafio.usuario u JOIN desafio.escola e ON e.codigo = u.codigo";
    }

    @Override
    protected Escola populateBean(Escola escola, Connection conn, ResultSet rs) throws SQLException {
        // Popula os atributos comum a todos os usuários
        super.populateBean(escola, conn, rs);

        // Popula apenas os atributos da escola
        escola.setTipo(TipoEscola.valueOf(rs.getString("tipo")));
//        escola.setProfessores(new ProfessorDAO().findByEscola(conn, escola.getCodigo()));

        return escola;
    }

    @Override
    protected Escola resultSetToBean(Connection conn, ResultSet rs) throws SQLException {
        return populateBean(new Escola(), conn, rs);
    }

}
