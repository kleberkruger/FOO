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
import br.ufms.banking.model.domain.Bancario;
import br.ufms.banking.model.domain.CPF;
import br.ufms.banking.model.domain.Municipio;
import br.ufms.banking.model.domain.NumeroBancario;
import br.ufms.banking.model.domain.Telefone;
import br.ufms.banking.model.enumerate.UF;
import br.ufms.kruger.util.persistence.DatabaseManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Kleber Kruger
 */
public class BancarioDAO extends UsuarioDAO<Bancario> {

    public BancarioDAO(DatabaseManager db) {
        super(db, Bancario.class);
    }

    @Override
    protected void insertAbst(Connection conn, Bancario bean) throws SQLException {
        String sql = "INSERT INTO ufms_banking.tb_bancario (br_id, br_nome, br_cpf, ag_numero) "
                + "VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, bean.getID());
            ps.setString(2, bean.getNome());
            ps.setObject(3, bean.getCPF().getNumero());
            ps.setInt(4, bean.getAgencia().getNumero());
            ps.executeUpdate();
        }
    }

    @Override
    protected void updateAbst(Connection conn, Bancario bean) throws SQLException {
        String sql = "UPDATE ufms_banking.tb_bancario SET br_nome = ?, br_cpf = ?, ag_numero = ? "
                + "WHERE br_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, bean.getNome());
            ps.setObject(2, bean.getCPF().getNumero());
            ps.setInt(3, bean.getAgencia().getNumero());
            ps.setLong(4, bean.getID());
            ps.executeUpdate();
        }
    }

    @Override
    protected String sqlToGet(Long id) {
//        // Dessa forma é necessário fazer uma outra consulta para retornar os dados da agência
//        return "SELECT * FROM ufms_banking.tb_usuario u, ufms_banking.tb_bancario b "
//                + "WHERE u.us_id = " + id;

        // Dessa forma os dados da agência virão nessa mesma consulta
        return "SELECT * FROM ufms_banking.tb_usuario u, ufms_banking.tb_bancario b, "
                + "ufms_banking.tb_agencia ag WHERE ag.ag_numero = b.ag_numero AND u.us_id = " + id
                + " AND b.br_id = " + id;
    }

    @Override
    protected String sqlToGetAll() {
        return "SELECT * FROM ufms_banking.tb_usuario u JOIN ufms_banking.tb_bancario b JOIN "
                + "ufms_banking.tb_agencia ag ON b.br_id = u.us_id AND b.ag_numero = ag.ag_numero";
    }

    @Override
    protected Bancario resultSetToBean(Connection conn, ResultSet rs) throws SQLException {
//        AgenciaDAO agenciaDAO = new AgenciaDAO(db);
//        Agencia agencia = agenciaDAO.get(conn, new NumeroBancario(rs.getInt("ag_numero")));

        Agencia agencia = new Agencia(new NumeroBancario(rs.getInt("ag_numero")),
                new Municipio(rs.getString("ag_municipio"), UF.valueOf(rs.getString("ag_estado"))),
                new Telefone(rs.getString("ag_telefone")));

        Bancario bancario = new Bancario(rs.getString("br_nome"), rs.getString("us_usuario"),
                rs.getString("us_senha"), new CPF(rs.getString("br_cpf")), agencia);

        setBeanGeneratedKey(bancario, rs.getLong("us_id"));

        return bancario;
    }

}
