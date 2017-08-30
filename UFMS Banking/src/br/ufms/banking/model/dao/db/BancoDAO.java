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

import br.ufms.banking.model.domain.Banco;
import br.ufms.kruger.util.persistence.DatabaseManager;
import br.ufms.kruger.util.persistence.ReadOnlyDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedHashSet;

/**
 *
 * @author Kleber Kruger
 */
public class BancoDAO extends ReadOnlyDAO<Banco, Short> {

    public BancoDAO(DatabaseManager db) {
        super(db, Banco.class);
    }

    @Override
    protected Banco get(Connection conn, Short codigo) throws SQLException {
        String sql = "SELECT * FROM ufms_banking.tb_banco WHERE bc_codigo = ?";
        Banco banco = null;
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setShort(1, codigo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.first()) {
                    banco = resultSetToBean(rs);
                }
            }
        }
        return banco;
    }

    @Override
    protected Collection<Banco> getAll(Connection conn) throws SQLException {
        String sql = "SELECT * FROM ufms_banking.tb_banco";
        Collection<Banco> bancos = new LinkedHashSet<>();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    bancos.add(resultSetToBean(rs));
                }
            }
        }
        return bancos;
    }

    private Banco resultSetToBean(ResultSet rs) throws SQLException {
        return new Banco(rs.getShort("bc_codigo"), rs.getString("bc_nome"));
    }

}
