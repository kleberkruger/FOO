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

import br.ufms.desafio.model.bean.Telefone;
import br.ufms.desafio.model.bean.TipoTelefone;
import br.ufms.desafio.model.bean.Usuario;
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
public class TelefoneDAO extends GenericDAO<Telefone> {

    @Override
    public void save(Telefone bean) throws SQLException {
        String sql = "INSERT INTO desafio.telefone (codigo_usuario, tipo, ddd, numero, principal "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = db.getConnection(); PreparedStatement ps = conn.prepareStatement(
                sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            ps.setLong(1, bean.getUsuario().getCodigo());
            ps.setString(2, bean.getTipo().toString());
            ps.setInt(3, bean.getDDD());
            ps.setInt(4, bean.getNumero());
            ps.setBoolean(5, bean.getPrincipal());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.first()) {
                    bean.setCodigo(rs.getLong(1));
                }
            }
        }
    }

    @Override
    public void update(Telefone bean) throws SQLException {
        String sql = "UPDATE desafio.telefone SET (codigo_usuario = ?, tipo = ?, ddd = ?, "
                + "numero = ?, principal = ?) WHERE codigo = ?";

        try (Connection conn = db.getConnection(); PreparedStatement ps = conn.prepareStatement(
                sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            ps.setLong(1, bean.getUsuario().getCodigo());
            ps.setString(2, bean.getTipo().toString());
            ps.setInt(3, bean.getDDD());
            ps.setInt(4, bean.getNumero());
            ps.setBoolean(5, bean.getPrincipal());
            ps.setLong(6, bean.getCodigo());

            ps.executeUpdate();
        }
    }

    @Override
    public void delete(long codigo) throws SQLException {
        String sql = "DELETE FROM desafio.telefone WHERE codigo = ?";
        try (Connection conn = db.getConnection(); PreparedStatement ps = conn.prepareStatement(
                sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            ps.setLong(1, codigo);
            ps.executeUpdate();
        }
    }

    @Override
    public Telefone get(long codigo) throws SQLException {
        String sql = "SELECT * FROM desafio.telefone WHERE codigo = ?";
        try (Connection conn = db.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, codigo);
            try (ResultSet rs = ps.executeQuery();) {
                UsuarioDAO usuarioDAO = new UsuarioDAO();
                if (rs.first()) {
                    Telefone t = new Telefone();
                    t.setCodigo(rs.getLong("codigo"));
                    t.setTipo(TipoTelefone.valueOf(rs.getString("tipo")));
                    t.setDDD(rs.getInt("ddd"));
                    t.setNumero(rs.getInt("numero"));
                    t.setPrincipal(rs.getBoolean("principal"));
                    t.setUsuario(usuarioDAO.get(rs.getLong("codigo_usuario")));
                    return t;
                }
            }
        }
        return null;
    }

    @Override
    public List<Telefone> getAll() throws SQLException {
        String sql = "SELECT * FROM desafio.telefone";
        List<Telefone> telefones = new ArrayList<>();
        try (Connection conn = db.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            try (ResultSet rs = ps.executeQuery();) {
                UsuarioDAO usuarioDAO = new UsuarioDAO();
                while (rs.next()) {
                    Telefone t = new Telefone();
                    t.setCodigo(rs.getLong("codigo"));
                    t.setTipo(TipoTelefone.valueOf(rs.getString("tipo")));
                    t.setDDD(rs.getInt("ddd"));
                    t.setNumero(rs.getInt("numero"));
                    t.setPrincipal(rs.getBoolean("principal"));
                    t.setUsuario(usuarioDAO.get(rs.getLong("codigo_usuario")));
                    telefones.add(t);
                }
            }
        }
        return telefones;
    }

    public List<Telefone> getAllByUser(Usuario usuario) throws SQLException {
        String sql = "SELECT * FROM desafio.telefone WHERE codigo_usuario = ?";
        List<Telefone> telefones = new ArrayList<>();
        try (Connection conn = db.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, usuario.getCodigo());
            try (ResultSet rs = ps.executeQuery();) {
                UsuarioDAO usuarioDAO = new UsuarioDAO();
                while (rs.next()) {
                    Telefone t = new Telefone();
                    t.setCodigo(rs.getLong("codigo"));
                    t.setTipo(TipoTelefone.valueOf(rs.getString("tipo")));
                    t.setDDD(rs.getInt("ddd"));
                    t.setNumero(rs.getInt("numero"));
                    t.setPrincipal(rs.getBoolean("principal"));
                    t.setUsuario(usuarioDAO.get(rs.getLong("codigo_usuario")));
                    telefones.add(t);
                }
            }
        }
        return telefones;
    }

}
