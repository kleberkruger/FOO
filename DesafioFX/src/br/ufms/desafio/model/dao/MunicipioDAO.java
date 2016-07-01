/*
 * Copyright (C) 2016 angelino.caon
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

import br.ufms.desafio.model.bean.Municipio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author angelino.caon
 */
public class MunicipioDAO extends GenericDAO<Municipio> {

    @Override
        public void save(Municipio bean) throws SQLException {
        String sql = "INSERT INTO desafio.municipio (codigo_ibge, nome, uf) VALUES (?, ?, ?)";
        try (Connection conn = db.getConnection(); PreparedStatement ps = conn.prepareStatement(
                sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setLong(1, bean.getCodigoIBGE());
            ps.setString(2, bean.getNome());
            ps.setString(3, bean.getUF());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.first()) {
                    bean.setCodigo(rs.getLong(1));
                }
            }
        }
    }

    @Override
    public void update(Municipio bean) throws SQLException {
        String sql = "UPDATE desafio.municipio SET (codigo_ibge = ?, nome = ?, uf = ?) WHERE codigo = ?";
        try (Connection conn = db.getConnection(); PreparedStatement ps = conn.prepareStatement(
                sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            
            ps.setLong(1, bean.getCodigoIBGE());
            ps.setString(2, bean.getNome());
            ps.setString(3, bean.getUF());
            ps.setLong(4, bean.getCodigo());
            ps.executeUpdate();
        }
    }

    @Override
    public void delete(long codigo) throws SQLException {
        String sql = "DELETE FROM desafio.municipio WHERE codigo = ?";
        try (Connection conn = db.getConnection(); PreparedStatement ps = conn.prepareStatement(
                sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            
            ps.setLong(1, codigo);
            ps.executeUpdate();
        }
    }

    @Override
    public Municipio get(long codigo) throws SQLException {
        String sql = "SELECT * FROM desafio.municipio WHERE codigo = ?";
        try (Connection conn = db.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {  
            ps.setLong(1, codigo);            
            try (ResultSet rs = ps.executeQuery();) {
                if (rs.first()) {
                    Municipio m = new Municipio();
                    m.setCodigo(rs.getLong("codigo"));
                    m.setCodigoIBGE(rs.getLong("codigo_ibge"));
                    m.setNome(rs.getString("nome"));
                    m.setUF(rs.getString("uf"));
                    return m;
                }
            }
        }
        return null;
    }

    @Override
    public List<Municipio> getAll() throws SQLException {
        String sql = "SELECT * FROM desafio.municipio";
        List<Municipio> municipios = new ArrayList<>();
        try (Connection conn = db.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {             
            try (ResultSet rs = ps.executeQuery();) {
                while(rs.next()) {
                    Municipio m = new Municipio();
                    m.setCodigo(rs.getLong("codigo"));
                    m.setCodigoIBGE(rs.getLong("codigo_ibge"));
                    m.setNome(rs.getString("nome"));
                    m.setUF(rs.getString("uf"));
                    municipios.add(m);
                }
            }
        }
        return municipios;
    }

}
