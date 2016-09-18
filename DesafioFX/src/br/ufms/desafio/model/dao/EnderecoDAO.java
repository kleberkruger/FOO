/*
 * Copyright (C) 2016 Kleber Kruger
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

import br.ufms.desafio.model.bean.Endereco;
import br.ufms.desafio.model.bean.Municipio;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável pelas operações de leitura e escrita na tabela endereco.
 *
 * @author Kleber Kruger
 */
public class EnderecoDAO extends ReadWriteDAO<Endereco, Long> {

    protected EnderecoDAO() {
        super(Endereco.class);
    }

    /**
     *
     * @param conn
     * @param bean
     * @param dependencies
     * @throws SQLException
     */
    @Override
    protected void insert(Connection conn, Endereco bean, Serializable... dependencies) throws SQLException {
        if (dependencies.length < 1) {
            throw new IllegalArgumentException("Dependência 'codigo_usuario' não informada");
        } else if (!(dependencies[0] instanceof Long)) {
            throw new ClassCastException("Dependência 'codigo_usuario' informada com tipo incorreto: "
                    + dependencies[0].getClass().getName() + " cannot be cast to java.lang.Long");
        }

        final String sql = "INSERT INTO desafio.endereco (codigo_usuario, logradouro, numero, s_n, "
                + "complemento, bairro, cep, codigo_municipio) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            final Municipio municipio = bean.getMunicipio();
            ps.setLong(1, (long) dependencies[0]);
            ps.setObject(2, bean.getLogradouro());
            ps.setObject(3, bean.getNumero());
            ps.setObject(4, bean.getSemNumero());
            ps.setObject(5, bean.getComplemento());
            ps.setObject(6, bean.getBairro());
            ps.setObject(7, bean.getCEP());
            ps.setObject(8, municipio != null ? municipio.getCodigo() : null);
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
    protected void update(Connection conn, Endereco bean) throws SQLException {
        final String sql = "UPDATE desafio.endereco SET logradouro = ?, numero = ?, s_n = ?, "
                + "complemento = ?, bairro = ?, cep = ?, codigo_municipio = ? WHERE codigo = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            final Municipio municipio = bean.getMunicipio();
            ps.setObject(1, bean.getLogradouro());
            ps.setObject(2, bean.getNumero());
            ps.setObject(3, bean.getSemNumero());
            ps.setObject(4, bean.getComplemento());
            ps.setObject(5, bean.getBairro());
            ps.setObject(6, bean.getCEP());
            ps.setObject(7, municipio != null ? municipio.getCodigo() : null);
            ps.setLong(8, bean.getCodigo());
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
        final String sql = "DELETE FROM desafio.endereco WHERE codigo = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setObject(1, codigo);
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
    protected Endereco get(Connection conn, Long codigo) throws SQLException {
        final String sql = "SELECT * FROM desafio.endereco WHERE codigo = ?";
        Endereco endereco = null;
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, codigo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.first()) {
                    endereco = resultSetToBean(conn, rs);
                }
            }
        }
        return endereco;
    }

    /**
     *
     * @param conn
     * @return
     * @throws SQLException
     */
    @Override
    protected List<Endereco> getAll(Connection conn) throws SQLException {
        final String sql = "SELECT * FROM desafio.endereco";
        List<Endereco> enderecos = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    enderecos.add(resultSetToBean(conn, rs));
                }
            }
        }
        return enderecos;
    }

    /**
     *
     * @param conn
     * @param codigoUsuario
     * @return
     * @throws SQLException
     */
    protected Endereco findByUsuario(Connection conn, Long codigoUsuario) throws SQLException {
        final String sql = "SELECT * FROM desafio.endereco WHERE codigo_usuario = ?";
        Endereco endereco = null;
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, codigoUsuario);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.first()) {
                    endereco = resultSetToBean(conn, rs);
                }
            }
        }
        return endereco;
    }

    /**
     *
     * @param conn
     * @param rs
     * @return
     * @throws SQLException
     */
    private Endereco resultSetToBean(Connection conn, ResultSet rs) throws SQLException {
        Endereco endereco = new Endereco();
        endereco.setCodigo(rs.getLong("codigo"));
        endereco.setLogradouro(rs.getString("logradouro"));
        endereco.setNumero(rs.getShort("numero"));
        endereco.setSemNumero(rs.getBoolean("s_n"));
        endereco.setComplemento(rs.getString("complemento"));
        endereco.setBairro(rs.getString("bairro"));
        endereco.setCEP(rs.getString("cep"));
        endereco.setMunicipio(getDAOFactory().getMunicipioDAO().get(conn, rs.getLong("codigo_municipio")));

        return endereco;
    }

}
