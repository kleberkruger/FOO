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

import br.ufms.desafio.model.bean.Aluno;
import br.ufms.desafio.model.bean.Escola;
import br.ufms.desafio.model.bean.enumerate.NivelEnsino;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author kleberkruger
 */
public class AlunoDAO extends JogadorDAO<Aluno> {

    public AlunoDAO() {
        super(Aluno.class);
    }
    
    @Override
    protected void insert(Connection conn, Aluno bean, Serializable... dependencies) throws SQLException {
        final String sql = "INSERT INTO desafio.aluno (codigo, codigo_escola, serie, "
                + "nivel, data_inicio) VALUES (?, ?, ?, ?, ?)";
        conn.setAutoCommit(false);

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            final Escola escola = bean.getEscola();
            ps.setLong(1, bean.getCodigo());
            ps.setObject(2, escola != null ? escola.getCodigo() : null);
            ps.setObject(3, bean.getSerie());
            ps.setObject(4, bean.getNivel().toString());
            ps.setDate(5, Date.valueOf(bean.getIngresso()));
            ps.executeUpdate();

            conn.commit();

        } catch (SQLException ex) {
            conn.rollback();
            throw ex;
        }
    }
    
    @Override
    protected void update(Connection conn, Aluno bean) throws SQLException {
        super.update(conn, bean);
        final String sql = "UPDATE desafio.aluno SET (codigo_escola, serie, nivel, "
                + "data_inicio = ?) WHERE codigo = ?";
        conn.setAutoCommit(false);

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            final Escola escola = bean.getEscola();
            ps.setObject(1, escola != null ? escola.getCodigo() : null);
            ps.setObject(2, bean.getSerie());
            ps.setObject(3, bean.getNivel().toString());
            ps.setDate(4, Date.valueOf(bean.getIngresso()));
            ps.setLong(5, bean.getCodigo());
            ps.executeUpdate();

            conn.commit();

        } catch (SQLException ex) {
            conn.rollback();
            throw ex;
        }
    }

    @Override
    protected Aluno populateBean(Aluno aluno, Connection conn, ResultSet rs) throws SQLException {
        // Popula os atributos comum a todos os jogadores
        super.populateBean(aluno, conn, rs);

        // Popula apenas os atributos do aluno
        aluno.setSerie(rs.getShort("serie"));
        aluno.setNivel(NivelEnsino.valueOf(rs.getString("nivel")));
        aluno.setEscola(new EscolaDAO().get(rs.getLong("codigo_escola")));
        aluno.setIngresso(rs.getDate("data_ingresso").toLocalDate());

        return aluno;
    }

    @Override
    protected Aluno resultSetToBean(Connection conn, ResultSet rs) throws SQLException {
        return populateBean(new Aluno(), conn, rs);
    }

    @Override
    protected String sqlToGet(Long codigo) {
        return "SELECT * FROM desafio.usuario u, desafio.jogador j, desafio.aluno a WHERE "
                + "u.codigo = " + codigo;
    }

    @Override
    protected String sqlToGetAll() {
        return "SELECT * FROM desafio.usuario u INNER JOIN desafio.jogador j INNER JOIN "
                + "desafio.aluno a ON p.codigo = j.codigo = u.codigo";
    }

}
