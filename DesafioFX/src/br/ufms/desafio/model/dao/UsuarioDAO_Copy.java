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
import br.ufms.desafio.model.bean.Telefone;
import br.ufms.desafio.model.bean.Usuario;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável pelas operações de leitura e escrita na tabela usuário.
 *
 * @author Kleber Kruger
 * @param <B>
 */
public abstract class UsuarioDAO_Copy<B extends Usuario> extends ReadWriteDAO<B, Long> {
    
    protected UsuarioDAO_Copy(Class<B> clazz) {
        super(clazz);
    }

    /**
     * Insere o elemento na tabela usuario.
     *
     * @param conn
     * @param bean
     * @throws SQLException
     */
    private void insertUsuario(Connection conn, B bean) throws SQLException {
        final String sql = "INSERT INTO desafio.usuario (nome, email, usuario, senha, data_criacao) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            final LocalDate criacao = bean.getCriacao();
            ps.setString(1, bean.getNome());
            ps.setString(2, bean.getEmail());
            ps.setString(3, bean.getUsuario());
            ps.setString(4, bean.getSenha());
            ps.setDate(5, (criacao != null) ? Date.valueOf(criacao) : null);
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.first()) {
                    bean.setCodigo(rs.getLong(1));
                }
            }
        }
    }

    /**
     * Atualiza os dados do elemento na tabela usuario.
     *
     * @param conn
     * @param bean
     * @throws SQLException
     */
    private void updateUsuario(Connection conn, B bean) throws SQLException {
        final String sql = "UPDATE desafio.usuario SET nome = ?, email = ?, usuario = ?, senha = ?, "
                + "data_criacao = ? WHERE codigo = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            final LocalDate criacao = bean.getCriacao();
            ps.setString(1, bean.getNome());
            ps.setString(2, bean.getEmail());
            ps.setString(3, bean.getUsuario());
            ps.setString(4, bean.getSenha());
            ps.setDate(5, (criacao != null) ? Date.valueOf(criacao) : null);
            ps.setLong(6, bean.getCodigo());
            ps.executeUpdate();
        }
    }

    /**
     * Salva (insere ou atualiza) o elemento na tabela usuario.
     *
     * @param conn
     * @param bean
     * @throws SQLException
     */
    protected void saveUsuario(Connection conn, B bean) throws SQLException {
        if (bean.getCodigo() == null) {
            insertUsuario(conn, bean);
        } else {
            updateUsuario(conn, bean);
        }
    }

    /**
     * Salva (insere ou atualiza) o elemento na tabela usuario.
     *
     * @param conn
     * @param bean
     * @throws SQLException
     */
    protected void saveEndereco(Connection conn, B bean) throws SQLException {
        if (bean.getEndereco() != null) {
            getDAOFactory().getEnderecoDAO().save(conn, bean.getEndereco(), bean.getCodigo());
        }
    }

    /**
     * Salva (insere ou atualiza) o elemento na tabela usuario.
     *
     * @param conn
     * @param bean
     * @throws SQLException
     */
    protected void saveTelefones(Connection conn, B bean) throws SQLException {
        DAOFactory factory = getDAOFactory();
        if (bean.getTelefones().size() > 0) {
            for (Telefone telefone : bean.getTelefones()) {
                factory.getTelefoneDAO().save(conn, telefone, bean.getCodigo());
            }
        }
    }

    /**
     * Insere o objeto bean na tabela "usuario" do banco de dados. Caso os campos endereço e
     * telefones tenham algum valor, insere-os também em suas respectivas tabelas.
     *
     * @param conn
     * @param bean
     * @param dependencies
     * @throws SQLException
     */
    @Override
    protected void insert(Connection conn, B bean, Serializable... dependencies) throws SQLException {
        conn.setAutoCommit(false);
        
        insertUsuario(conn, bean);
        saveEndereco(conn, bean);
        saveTelefones(conn, bean);
    }

    @Override
    protected void update(Connection conn, B bean) throws SQLException {
        conn.setAutoCommit(false);
        
        updateUsuario(conn, bean);
        saveEndereco(conn, bean);
        saveTelefones(conn, bean);
    }

    @Override
    protected void delete(Connection conn, Long codigo) throws SQLException {
        final String sql = "DELETE FROM desafio.usuario WHERE codigo = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setObject(1, codigo);
            ps.executeUpdate();
        }
    }

    @Override
    protected B get(Connection conn, Long codigo) throws SQLException {
        B bean = null;
        try (PreparedStatement ps = conn.prepareStatement(sqlToGet(codigo))) {
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.first()) {
                    bean = resultSetToBean(conn, rs);
                }
            }
        }
        return bean;
    }

    @Override
    protected List<B> getAll(Connection conn) throws SQLException {
        List<B> beans = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sqlToGetAll())) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    beans.add(resultSetToBean(conn, rs));
                }
            }
        }
        return beans;
    }

    protected B populateBean(B usuario, Connection conn, ResultSet rs) throws SQLException {
        final Endereco endereco = getDAOFactory().getEnderecoDAO().findByUsuario(conn, rs.getLong("codigo"));
        final List<Telefone> telefones = getDAOFactory().getTelefoneDAO().findByUsuario(conn, usuario.getCodigo());
        final Date criacao = rs.getDate("data_criacao");
        usuario.setCodigo(rs.getLong("codigo"));
        usuario.setNome(rs.getString("nome"));
        usuario.setUsuario(rs.getString("senha"));
        usuario.setSenha(rs.getString("senha"));
        usuario.setEmail(rs.getString("email"));
        usuario.setTelefones(telefones != null ? telefones : new ArrayList<>());
        usuario.setEndereco(endereco != null ? endereco : new Endereco());
        usuario.setCriacao(criacao != null ? criacao.toLocalDate() : null);
        return usuario;
    }

    protected abstract String sqlToGet(Long codigo);

    protected abstract String sqlToGetAll();

    protected abstract B resultSetToBean(Connection conn, ResultSet rs) throws SQLException;

//    /**
//     * -------------------------------------------------------------------------------------------
//     * Este método usa reflections para instanciar um objeto (derivado de Usuario) B. Como
//     * reflections gera um custo adicional no desempenho, vou deixar este método comentado e usar
//     * outra abordagem.
//     * -------------------------------------------------------------------------------------------
//     */
//    protected B resultSetToBean(Connection conn, ResultSet rs) throws SQLException {
//        B jogador = null;
//        try {
//            return populateBean(getBeanClass().newInstance(), conn, rs);
//        } catch (InstantiationException | IllegalAccessException ex) {
//            Logger.getLogger(JogadorDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return jogador;
//    }
}
