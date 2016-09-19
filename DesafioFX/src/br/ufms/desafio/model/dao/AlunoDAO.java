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
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 *
 * @author kleberkruger
 */
public class AlunoDAO extends JogadorDAO<Aluno> {

    protected AlunoDAO() {
        super(Aluno.class);
    }

    protected void insertAluno(Connection conn, Aluno bean) throws SQLException {
        final String sql = "INSERT INTO desafio.aluno (codigo, codigo_escola, data_inicio) VALUES (?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            final Escola escola = bean.getEscola();
            final LocalDate ingresso = bean.getIngresso();
            ps.setLong(1, bean.getCodigo());
            ps.setObject(2, escola != null ? escola.getCodigo() : null);
            ps.setDate(3, ingresso != null ? Date.valueOf(bean.getIngresso()) : null);
            ps.executeUpdate();
        }
    }

    protected void updateAluno(Connection conn, Aluno bean) throws SQLException {
        final String sql = "UPDATE desafio.aluno SET codigo_escola = ?, data_inicio = ? "
                + "WHERE codigo = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            final Escola escola = bean.getEscola();
            final LocalDate ingresso = bean.getIngresso();
            ps.setObject(1, escola != null ? escola.getCodigo() : null);
            ps.setDate(2, ingresso != null ? Date.valueOf(bean.getIngresso()) : null);
            ps.setLong(3, bean.getCodigo());
            ps.executeUpdate();
        }
    }

    /**
     * Salva (insere ou atualiza) a serie.
     *
     * @param conn
     * @param bean
     * @throws SQLException
     */
    protected void saveSerie(Connection conn, Aluno bean) throws SQLException {
        if (bean.getSerie() != null) {
            getDAOFactory().getSerieDAO().save(conn, bean.getSerie(), bean.getCodigo());
        }
    }

    @Override
    protected void insertAbst(Connection conn, Aluno bean) throws SQLException {
        insertAluno(conn, bean);
        saveSerie(conn, bean);
    }

    @Override
    protected void updateAbst(Connection conn, Aluno bean) throws SQLException {
        updateAluno(conn, bean);
        saveSerie(conn, bean);
    }

    @Override
    protected Aluno populateBean(Aluno aluno, Connection conn, ResultSet rs) throws SQLException {
        // Popula os atributos comum a todos os jogadores
        super.populateBean(aluno, conn, rs);
        
        final Date ingresso = rs.getDate("data_inicio");

//        // Popula apenas os atributos do aluno
        aluno.setSerie(getDAOFactory().getSerieDAO().findByAluno(conn, aluno.getCodigo()));
        aluno.setEscola(getDAOFactory().getEscolaDAO().get(rs.getLong("codigo_escola")));
        aluno.setIngresso(ingresso != null ? ingresso.toLocalDate() : null);
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
                + "desafio.aluno a ON a.codigo = j.codigo = u.codigo";
    }

}
