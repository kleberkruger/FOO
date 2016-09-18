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

import br.ufms.desafio.model.bean.Municipio;
import br.ufms.desafio.model.bean.enumerate.UF;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável pelas operações de leitura na tabela municipio.
 *
 * @author Kleber Kruger
 */
public class MunicipioDAO extends ReadOnlyDAO<Municipio, Long> {

    protected MunicipioDAO() {
        super(Municipio.class);
    }

    /**
     * 
     * @param conn
     * @param codigo
     * @return
     * @throws SQLException 
     */
    @Override
    protected Municipio get(Connection conn, Long codigo) throws SQLException {
        final String sql = "SELECT * FROM desafio.municipio WHERE codigo_ibge = ?";
        Municipio municipio = null;
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, codigo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.first()) {
                    municipio = resultSetToBean(rs);
                }
            }
        }
        return municipio;
    }

    /**
     * 
     * @param conn
     * @return
     * @throws SQLException 
     */
    @Override
    protected List<Municipio> getAll(Connection conn) throws SQLException {
        final String sql = "SELECT * FROM desafio.municipio";
        List<Municipio> municipios = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    municipios.add(resultSetToBean(rs));
                }
            }
        }
        return municipios;
    }

    /**
     * 
     * @param rs
     * @return
     * @throws SQLException 
     */
    private Municipio resultSetToBean(ResultSet rs) throws SQLException {
        Municipio municipio = new Municipio();
        municipio.setCodigo(rs.getLong("codigo_ibge"));
        municipio.setNome(rs.getString("nome"));
        municipio.setUF(UF.valueOf(rs.getString("uf")));

        return municipio;
    }

}
