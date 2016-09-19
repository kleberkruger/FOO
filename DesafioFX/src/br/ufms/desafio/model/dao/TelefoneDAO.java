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

import br.ufms.desafio.model.bean.Telefone;
import br.ufms.desafio.model.bean.enumerate.TipoTelefone;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável pelas operações de leitura e escrita na tabela telefone.
 *
 * @author Kleber Kruger
 */
public class TelefoneDAO extends ReadWriteDAO<Telefone, Long> {

    protected TelefoneDAO() {
        super(Telefone.class);
    }

    /**
     *
     * @param conn - conexão com o banco de dados
     * @param bean - objeto telefone
     * @param dependencies - código do usuário
     * @throws SQLException
     */
    @Override
    protected void insert(Connection conn, Telefone bean, Serializable... dependencies) throws SQLException {
        if (dependencies.length < 1) {
            throw new IllegalArgumentException("Dependência 'codigo_usuario' não informada");
        } else if (!(dependencies[0] instanceof Long)) {
            throw new ClassCastException("Dependência 'codigo_usuario' informada com tipo incorreto: "
                    + dependencies[0].getClass().getName() + " cannot be cast to java.lang.Long");
        }
        
        final String sql = "INSERT INTO desafio.telefone (codigo_usuario, tipo, ddd, numero, principal) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            final TipoTelefone tipo = bean.getTipo();
            ps.setLong(1, (long) dependencies[0]);
            ps.setObject(2, tipo != null ? tipo.toString() : null);
            ps.setObject(3, bean.getDDD());
            ps.setObject(4, bean.getNumero());
            ps.setObject(5, bean.getPrincipal());
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
     * @param conn - conexão com o banco de dados
     * @param bean - objeto telefone
     * @throws SQLException
     */
    @Override
    protected void update(Connection conn, Telefone bean) throws SQLException {
        final String sql = "UPDATE desafio.telefone SET tipo = ?, ddd = ?, numero = ?, "
                + "principal = ? WHERE codigo = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            final TipoTelefone tipo = bean.getTipo();
            ps.setObject(1, tipo != null ? tipo.toString() : null);
            ps.setObject(2, bean.getDDD());
            ps.setObject(3, bean.getNumero());
            ps.setObject(4, bean.getPrincipal());
            ps.setLong(5, bean.getCodigo());
            ps.executeUpdate();
        }
    }

    /**
     * 
     * @param conn - conexão com o banco de dados
     * @param codigo - código do telefone
     * @throws SQLException 
     */
    @Override
    protected void delete(Connection conn, Long codigo) throws SQLException {
        final String sql = "DELETE FROM desafio.telefone WHERE codigo = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, codigo);
            ps.executeUpdate();
        }
    }

    /**
     * 
     * @param conn - conexão com o banco de dados
     * @param codigo - código do telefone
     * @return
     * @throws SQLException 
     */
    @Override
    protected Telefone get(Connection conn, Long codigo) throws SQLException {
        final String sql = "SELECT * FROM desafio.telefone WHERE codigo = ?";
        Telefone telefone = null;
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, codigo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.first()) {
                    telefone = resultSetToBean(rs);
                }
            }
        }
        return telefone;
    }

    /**
     * 
     * @param conn - conexão com o banco de dados
     * @return
     * @throws SQLException 
     */
    @Override
    protected List<Telefone> getAll(Connection conn) throws SQLException {
        final String sql = "SELECT * FROM desafio.telefone";
        List<Telefone> telefones = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    telefones.add(resultSetToBean(rs));
                }
            }
        }
        return telefones;
    }

    /**
     * 
     * @param conn - conexão com o banco de dados
     * @param codigoUsuario - código do usuário
     * @return
     * @throws SQLException 
     */
    public List<Telefone> findByUsuario(Connection conn, Long codigoUsuario) throws SQLException {
        final String sql = "SELECT * FROM desafio.telefone WHERE codigo_usuario = ?";
        List<Telefone> telefones = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, codigoUsuario);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    telefones.add(resultSetToBean(rs));
                }
            }
        }
        return telefones;
    }

    /**
     * 
     * @param rs - ResultSet com as informações vindas do banco
     * @return
     * @throws SQLException 
     */
    private Telefone resultSetToBean(ResultSet rs) throws SQLException {
        final String tipo = rs.getString("tipo");
        Telefone telefone = new Telefone();
        telefone.setCodigo(rs.getLong("codigo"));
        telefone.setTipo(tipo != null ? TipoTelefone.valueOf(tipo) : null);
        telefone.setDDD(rs.getString("ddd"));
        telefone.setNumero(rs.getString("numero"));
        telefone.setPrincipal(rs.getBoolean("principal"));

        return telefone;
    }

}
