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
package br.ufms.banking.model.dao;

import br.ufms.banking.model.domain.Transacao;
import br.ufms.kruger.util.persistence.FileDAO;
import br.ufms.kruger.util.persistence.PersistenceException;
import java.nio.file.Paths;

/**
 *
 * @author Kleber Kruger
 */
public class TransacaoDAO extends FileDAO<Transacao, Long> {

    public TransacaoDAO() {
        super(Paths.get(System.getProperty("user.home"), "Transacoes.txt"));
    }

    @Override
    protected String encode(Transacao bean) throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected Transacao decode(String info) throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
