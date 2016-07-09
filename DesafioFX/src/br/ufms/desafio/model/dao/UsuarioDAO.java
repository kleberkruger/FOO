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
import br.ufms.desafio.model.bean.Usuario;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kleber Kruger
 */
public class UsuarioDAO extends GenericDAO<Usuario> {

    @Override
    public void save(Usuario bean) throws SQLException {
        String sql = "INSERT INTO desafio.usuario (codigo_endereco, nome, email, "
                + "usuario, senha, data_criacao) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = db.getConnection(); PreparedStatement ps = conn.prepareStatement(
                sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            if (bean.getEndereco() == null) {
                bean.setEndereco(new Endereco());
            }

            EnderecoDAO enderecoDAO = new EnderecoDAO();
            enderecoDAO.save(bean.getEndereco());

            ps.setLong(1, bean.getEndereco().getCodigo());
            ps.setString(2, bean.getNome());
            ps.setString(3, bean.getEmail());
            ps.setString(4, bean.getUsuario());
            ps.setString(5, bean.getSenha());
            ps.setDate(6, Date.valueOf(bean.getCriacao()));

            try {
                
                ps.executeUpdate();
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.first()) {
                        bean.setCodigo(rs.getLong(1));
                    }
                }
            } catch (SQLException ex) {
                enderecoDAO.delete(bean.getEndereco());
                throw ex;
            }
        }
    }

    @Override
    public void update(Usuario bean) throws SQLException {
        String sql = "UPDATE desafio.usuario SET (codigo_endereco = ?, nome = ?, email = ?, "
                + "usuario = ?, senha = ?, data_criacao = ?) WHERE codigo = ?";

        try (Connection conn = db.getConnection(); PreparedStatement ps = conn.prepareStatement(
                sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            ps.setLong(1, bean.getEndereco().getCodigo());
            ps.setString(2, bean.getNome());
            ps.setString(3, bean.getEmail());
            ps.setString(4, bean.getUsuario());
            ps.setString(5, bean.getSenha());
            ps.setDate(6, Date.valueOf(bean.getCriacao()));
            ps.setLong(7, bean.getCodigo());

            ps.executeUpdate();
        }
    }

    @Override
    public void delete(long codigo) throws SQLException {
        String sql = "DELETE FROM desafio.usuario WHERE codigo = ?";
        try (Connection conn = db.getConnection(); PreparedStatement ps = conn.prepareStatement(
                sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            ps.setLong(1, codigo);
            ps.executeUpdate();
        }
    }

    @Override
    public Usuario get(long codigo) throws SQLException {
        String sql = "SELECT * FROM desafio.usuario WHERE codigo = ?";
        try (Connection conn = db.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, codigo);
            try (ResultSet rs = ps.executeQuery();) {
                EnderecoDAO enderecoDAO = new EnderecoDAO();
                TelefoneDAO telefoneDAO = new TelefoneDAO();
                while (rs.next()) {
                    Usuario u = new Usuario();
                    u.setCodigo(rs.getLong("codigo"));
                    u.setEndereco(enderecoDAO.get(rs.getLong("codigo_endereco")));
                    u.setNome(rs.getString("nome"));
                    u.setUsuario(rs.getString("usuario"));
                    u.setSenha(rs.getString("senha"));
                    u.setCriacao(rs.getDate("data_criacao").toLocalDate());
                    u.setTelefone(telefoneDAO.getAllByUser(u));
                    return u;
                }
            }
        }
        return null;
    }

    @Override
    public List<Usuario> getAll() throws SQLException {
        String sql = "SELECT * FROM desafio.usuario";
        List<Usuario> usuarios = new ArrayList<>();
        try (Connection conn = db.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            try (ResultSet rs = ps.executeQuery();) {
                EnderecoDAO enderecoDAO = new EnderecoDAO();
                TelefoneDAO telefoneDAO = new TelefoneDAO();
                while (rs.next()) {
                    Usuario u = new Usuario();
                    u.setCodigo(rs.getLong("codigo"));
                    u.setEndereco(enderecoDAO.get(rs.getLong("codigo_endereco")));
                    u.setNome(rs.getString("nome"));
                    u.setUsuario(rs.getString("usuario"));
                    u.setSenha(rs.getString("senha"));
                    u.setCriacao(rs.getDate("data_criacao").toLocalDate());
                    u.setTelefone(telefoneDAO.getAllByUser(u));
                    usuarios.add(u);
                }
            }
        }
        return usuarios;
    }

}
