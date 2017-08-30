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

import br.ufms.banking.model.domain.Agencia;
import br.ufms.banking.model.domain.Banco;
import br.ufms.banking.model.domain.Municipio;
import br.ufms.banking.model.domain.NumeroBancario;
import br.ufms.banking.model.domain.Telefone;
import br.ufms.banking.model.enumerate.UF;
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
public class AgenciaDAO extends ReadWriteDAO<Agencia, NumeroBancario> {

    public AgenciaDAO(DatabaseManager db) {
        super(db, Agencia.class);
    }

    @Override
    protected void insert(Connection conn, Agencia bean, Serializable... dependencies)
            throws SQLException {

        String sql = "INSERT INTO ufms_banking.tb_agencia (ag_telefone, ag_municipio, ag_estado, "
                + "bc_codigo) VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setObject(1, bean.getTelefone() != null ? bean.getTelefone().getNumero() : null);
            ps.setString(2, bean.getMunicipio().getNome());
            ps.setString(3, bean.getMunicipio().getUF().toString());
            ps.setShort(4, Banco.UFMSBANKING_CODIGO);
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.first()) {
                    NumeroBancario numeroBancario = new NumeroBancario(rs.getInt(1));
                    setBeanGeneratedKey(bean, numeroBancario);
                    updateDigito(conn, numeroBancario);
                }
            }
        }
    }

    @Override
    protected void update(Connection conn, Agencia bean) throws SQLException {
        String sql = "UPDATE ufms_banking.tb_agencia SET ag_digito = ?, ag_telefone = ?, "
                + "ag_municipio = ?, ag_estado = ? WHERE ag_numero = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, bean.getDigito());
            ps.setObject(2, bean.getTelefone() != null ? bean.getTelefone().getNumero() : null);
            ps.setString(3, bean.getMunicipio().getNome());
            ps.setString(4, bean.getMunicipio().getUF().toString());
            ps.setInt(5, bean.getNumero());
            ps.executeUpdate();
        }
    }

    /**
     * Este método serve para atualizar o dígito da agência, uma vez que o dígito verificador é
     * gerado após a inserção da agência no banco de dados pelo algoritmo módulo 10.
     *
     * Observação: o dígito não necessariamente precisa estar armazenado no banco de dados, pois é
     * usado somente dentro da aplicação, sendo gerado toda vez que se cria o objeto NumeroBancario.
     * Logo, se o algoritmo for alterado, os dígitos verificadores também serão.
     *
     * @param conn
     * @param agencia
     * @throws SQLException
     */
    private void updateDigito(Connection conn, NumeroBancario agencia) throws SQLException {
        String sql = "UPDATE ufms_banking.tb_agencia SET ag_digito = ? WHERE ag_numero = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, agencia.getDigito());
            ps.setInt(2, agencia.getNumero());
            ps.executeUpdate();
        }
    }

    @Override
    protected void delete(Connection conn, NumeroBancario agencia) throws SQLException {
        String sql = "DELETE FROM ufms_banking.tb_agencia WHERE ag_numero = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, agencia.getNumero());
            ps.executeUpdate();
        }
    }

    @Override
    protected Agencia get(Connection conn, NumeroBancario numero) throws SQLException {
        String sql = "SELECT * FROM ufms_banking.tb_agencia WHERE ag_numero = ?";
        Agencia agencia = null;
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, numero.getNumero());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.first()) {
                    agencia = resultSetToBean(rs);
                }
            }
        }
        return agencia;
    }

    @Override
    protected Collection<Agencia> getAll(Connection conn) throws SQLException {
        String sql = "SELECT * FROM ufms_banking.tb_agencia";
        Collection<Agencia> agencias = new LinkedHashSet<>();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    agencias.add(resultSetToBean(rs));
                }
            }
        }
        return agencias;
    }

    private Agencia resultSetToBean(ResultSet rs) throws SQLException {

        Agencia agencia = new Agencia(new Municipio(rs.getString("ag_municipio"),
                UF.valueOf(rs.getString("ag_estado"))), new Telefone(rs.getString("ag_telefone")));

        setBeanGeneratedKey(agencia, new NumeroBancario(rs.getInt("ag_numero")));

        return agencia;
    }

}
