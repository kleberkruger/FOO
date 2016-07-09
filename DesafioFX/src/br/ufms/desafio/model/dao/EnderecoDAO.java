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

import br.ufms.desafio.model.bean.Endereco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kleber Kruger
 */
public class EnderecoDAO extends GenericDAO<Endereco> {

    @Override
    public void save(Endereco bean) throws SQLException {
        String sql = "INSERT INTO desafio.endereco (logradouro, numero, s_n, complemento, "
                + "bairro, cep, codigo_municipio) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = db.getConnection(); PreparedStatement ps = conn.prepareStatement(
                sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, bean.getLogradouro());
            ps.setInt(2, bean.getNumero());
            ps.setBoolean(3, bean.getSn());
            ps.setString(4, bean.getComplemento());
            ps.setString(5, bean.getComplemento());
            ps.setString(6, bean.getComplemento());
            ps.setLong(7, bean.getMunicipio().getCodigo());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.first()) {
                    bean.setCodigo(rs.getLong(1));
                }
            }
        }
    }

    @Override
    public void update(Endereco bean) throws SQLException {
        String sql = "UPDATE desafio.endereco SET (logradouro = ?, numero = ?, s_n = ?, "
                + "complemento = ?, bairro = ?, cep = ?, codigo_municipio = ?) WHERE codigo = ?";
        
        try (Connection conn = db.getConnection(); PreparedStatement ps = conn.prepareStatement(
                sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, bean.getLogradouro());
            ps.setInt(2, bean.getNumero());
            ps.setBoolean(3, bean.getSn());
            ps.setString(4, bean.getComplemento());
            ps.setString(5, bean.getComplemento());
            ps.setString(6, bean.getComplemento());
            ps.setLong(7, bean.getMunicipio().getCodigo());
            ps.setLong(8, bean.getCodigo());
            
            ps.executeUpdate();
        }
    }

    @Override
    public void delete(long codigo) throws SQLException {
        String sql = "DELETE FROM desafio.endereco WHERE codigo = ?";
        try (Connection conn = db.getConnection(); PreparedStatement ps = conn.prepareStatement(
                sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            
            ps.setLong(1, codigo);
            ps.executeUpdate();
        }
    }

    @Override
    public Endereco get(long codigo) throws SQLException {
        String sql = "SELECT * FROM desafio.endereco WHERE codigo = ?";
        try (Connection conn = db.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {  
            ps.setLong(1, codigo);            
            try (ResultSet rs = ps.executeQuery();) {
                MunicipioDAO municipioDAO = new MunicipioDAO();
                if (rs.first()) {
                    Endereco e = new Endereco();
                    e.setCodigo(rs.getLong("codigo"));
                    e.setLogradouro(rs.getString("logradouro"));
                    e.setNumero(rs.getShort("numero"));
                    e.setSn(rs.getBoolean("s_n"));
                    e.setComplemento(rs.getString("complemento"));
                    e.setBairro(rs.getString("bairro"));
                    e.setCep(rs.getString("cep"));
                    e.setMunicipio(municipioDAO.get(rs.getLong("codigo_municipio")));
                    return e;
                }
            }
        }
        return null;
    }

    @Override
    public List<Endereco> getAll() throws SQLException {
        String sql = "SELECT * FROM desafio.endereco";
        List<Endereco> enderecos = new ArrayList<>();
        try (Connection conn = db.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {             
            try (ResultSet rs = ps.executeQuery();) {
                MunicipioDAO municipioDAO = new MunicipioDAO();
                while(rs.next()) {
                    Endereco e = new Endereco();
                    e.setCodigo(rs.getLong("codigo"));
                    e.setLogradouro(rs.getString("logradouro"));
                    e.setNumero(rs.getShort("numero"));
                    e.setSn(rs.getBoolean("s_n"));
                    e.setComplemento(rs.getString("complemento"));
                    e.setBairro(rs.getString("bairro"));
                    e.setCep(rs.getString("cep"));
                    e.setMunicipio(municipioDAO.get(rs.getLong("codigo_municipio")));
                    enderecos.add(e);
                }
            }
        }
        return enderecos;
    }

}
