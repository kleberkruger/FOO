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

import br.ufms.kruger.util.persistence.FileDAO;
import br.ufms.banking.model.domain.Bancario;
import br.ufms.kruger.util.persistence.PersistenceException;
import java.nio.file.Paths;

/**
 *
 * @author Kleber Kruger
 */
public class BancarioDAO extends FileDAO<Bancario, Long> {

    public BancarioDAO() throws PersistenceException {
        super(Paths.get(System.getProperty("user.home"), "Bancarios.txt"));
    }

    @Override
    protected String encode(Bancario bean) {
        return bean.getID() + "|" + bean.getNome() + "|" + bean.getUsuario() + "|" + bean.getSenha();
    }

    @Override
    protected Bancario decode(String info) {
        String[] infos = info.split("\\|");
        Bancario bancario = new Bancario(infos[1], infos[2], infos[3]);
        setBeanID(bancario, Long.parseLong(infos[0]));
        return bancario;
    }

}
