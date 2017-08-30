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

import br.ufms.banking.model.domain.ContaBancaria;
import br.ufms.banking.model.domain.ContaCorrente;
import br.ufms.banking.model.domain.ContaPoupanca;
import br.ufms.banking.model.domain.NumeroBancario;
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
 */
public abstract class ContaBancariaDAO extends ReadWriteDAO<ContaBancaria, NumeroBancario> {

    public ContaBancariaDAO(DatabaseManager db) {
        super(db, ContaBancaria.class);
    }

    @Override
    protected void insert(Connection conn, ContaBancaria bean, Serializable... dependencies) throws SQLException {
        String sql = "INSERT INTO ufms_banking.tb_conta_bancaria ("
                + "cb_operacao, cb_numero, cb_digito, cb_saldo, cb_limite, cb_tipo, cg_id, ag_numero) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, bean.getTipo().getOperacao());
            ps.setLong(2, bean.getNumero());
            ps.setInt(3, bean.getDigito());
            ps.setDouble(4, bean.getSaldo());
            ps.setObject(5, bean instanceof ContaCorrente ? ((ContaCorrente) bean).getLimite() : null);
            ps.setString(6, bean.getTipo().toString());
            ps.setObject(7, bean instanceof ContaPoupanca ? ((ContaPoupanca) bean).getCategoria().toString() : null);
            ps.setInt(8, bean.getAgencia().getNumero());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.first()) {
                    NumeroBancario numero = new NumeroBancario(rs.getInt("us_id"));
                    setBeanGeneratedKey(bean, numero);
                }
            }
        }
    }

    @Override
    protected void update(Connection conn, ContaBancaria bean) throws SQLException {
        String sql = "UPDATE ufms_banking.tb_conta_bancaria SET cb_operacao = ?, cb_numero = ?, cb_digito = ?, "
                + "cb_saldo = ?, cb_limite = ?, cb_tipo = ?, cg_id = ?, ag_numero = ? WHERE cb_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, bean.getTipo().getOperacao());
            ps.setLong(2, bean.getNumero());
            ps.setInt(3, bean.getDigito());
            ps.setDouble(4, bean.getSaldo());
            ps.setObject(5, bean instanceof ContaCorrente ? ((ContaCorrente) bean).getLimite() : null);
            ps.setString(6, bean.getTipo().toString());
            ps.setObject(7, bean instanceof ContaPoupanca ? ((ContaPoupanca) bean).getCategoria().toString() : null);
            ps.setInt(8, bean.getAgencia().getNumero());
            ps.setLong(9, bean.getID().getNumero());
            ps.executeUpdate();
        }
    }

    @Override
    protected void delete(Connection conn, NumeroBancario id) throws SQLException {
        final String sql = "DELETE FROM ufms_banking.tb_conta_bancaria WHERE cb_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id.getNumero());
            ps.executeUpdate();
        }
    }

    @Override
    protected ContaBancaria get(Connection conn, NumeroBancario id) throws SQLException {
        String sql = "SELECT * FROM ufms_banking.tb_usuario WHERE us_id = ?";
        ContaBancaria conta = null;
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id.getNumero());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.first()) {
                    conta = resultSetToBean(rs);
                }
            }
        }
        return conta;
    }

    @Override
    protected Collection<ContaBancaria> getAll(Connection conn) throws SQLException {
        String sql = "SELECT * FROM ufms_banking.tb_conta_bancaria";
        Collection<ContaBancaria> contas = new LinkedHashSet<>();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    contas.add(resultSetToBean(rs));
                }
            }
        }
        return contas;
    }

    protected abstract ContaBancaria resultSetToBean(ResultSet rs);

}
