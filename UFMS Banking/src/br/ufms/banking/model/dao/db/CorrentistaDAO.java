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
import br.ufms.banking.model.domain.CNPJ;
import br.ufms.banking.model.domain.CPF;
import br.ufms.banking.model.domain.Correntista;
import br.ufms.banking.model.domain.Municipio;
import br.ufms.banking.model.domain.NumeroBancario;
import br.ufms.banking.model.domain.PessoaFisica;
import br.ufms.banking.model.domain.PessoaJuridica;
import br.ufms.banking.model.domain.Telefone;
import br.ufms.banking.model.enumerate.TipoCorrentista;
import br.ufms.banking.model.enumerate.UF;
import br.ufms.kruger.util.persistence.DatabaseManager;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kleber Kruger
 */
public class CorrentistaDAO extends UsuarioDAO<Correntista> {

    public CorrentistaDAO(DatabaseManager db) {
        super(db, Correntista.class);
    }

    @Override
    protected Correntista resultSetToBean(Connection conn, ResultSet rs) throws SQLException {
        Correntista c = null;
        TipoCorrentista tipoCorrentista = TipoCorrentista.valueOf(rs.getString("co_tipo"));
        if (tipoCorrentista == TipoCorrentista.PESSOA_FISICA) {
            c = new PessoaFisica(new Agencia(new Municipio("Coxim", UF.MS)),
                    rs.getString("co_nome"), rs.getString("us_usuario"),
                    rs.getString("us_senha"), new CPF(rs.getString("co_cpf_cnpj")),
                    new Telefone(rs.getString("co_telefone")));
        } else if (tipoCorrentista == TipoCorrentista.PESSOA_JURIDICA) {
            c = new PessoaJuridica(new Agencia(new Municipio("Coxim", UF.MS)),
                    rs.getString("co_nome"), rs.getString("co_razao_social"),
                    rs.getString("us_usuario"), rs.getString("us_senha"),
                    new CNPJ(rs.getString("co_cpf_cnpj")), new Telefone(rs.getString("co_telefone")));
        }
        setBeanGeneratedKey(c, rs.getLong("us_id"));
        try {
            Correntista.registrarNumeroConta(c, new NumeroBancario(rs.getInt("co_numero_conta")));
        } catch (IllegalStateException ex) {
            Logger.getLogger(CorrentistaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }

    @Override
    protected void insertAbst(Connection conn, Correntista bean) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void updateAbst(Connection conn, Correntista bean) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected String sqlToGet(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected String sqlToGetAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
