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

import br.ufms.desafio.model.bean.Responsavel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author kleberkruger
 */
public class ResponsavelDAO extends JogadorDAO<Responsavel> {

    protected ResponsavelDAO() {
        super(Responsavel.class);
    }

    @Override
    protected void insertAbst(Connection conn, Responsavel bean) throws SQLException {
        final String sql = "INSERT INTO desafio.responsavel (codigo, cpf) VALUES (?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, bean.getCodigo());
            ps.setObject(2, bean.getCPF());
            ps.executeUpdate();
        }
    }

    @Override
    protected void updateAbst(Connection conn, Responsavel bean) throws SQLException {
        final String sql = "UPDATE desafio.responsavel SET cpf = ? WHERE codigo = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setObject(1, bean.getCPF());
            ps.setLong(2, bean.getCodigo());
            ps.executeUpdate();
        }
    }

    @Override
    protected Responsavel populateBean(Responsavel jogador, Connection conn, ResultSet rs) throws SQLException {
        // Popula os atributos comum a todos os jogadores
        super.populateBean(jogador, conn, rs);
        // Popula apenas os atributos do responsável
        jogador.setCPF(rs.getString("cpf"));

        return jogador;
    }

    @Override
    protected Responsavel resultSetToBean(Connection conn, ResultSet rs) throws SQLException {
        return populateBean(new Responsavel(), conn, rs);
    }

    @Override
    protected String sqlToGet(Long codigo) {
        StringBuilder command = new StringBuilder("SELECT * FROM desafio.usuario u, desafio.jogador j, desafio.responsavel r");
        command.append(" WHERE u.codigo = ").append(codigo);
        command.append(" and j.codigo = ").append(codigo);
        command.append(" and r.codigo = ").append(codigo);
        return command.toString();
    }

    @Override
    protected String sqlToGetAll() {
        return "SELECT * FROM desafio.usuario u JOIN desafio.jogador j ON u.codigo = j.codigo "
                + "JOIN desafio.responsavel r ON j.codigo = r.codigo";
    }

}
