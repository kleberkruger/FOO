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

import br.ufms.desafio.model.bean.Bean;
import br.ufms.desafio.util.Database;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Kleber Kruger
 * @param <T>
 */
public abstract class ReadOnlyDAO<T extends Bean> {

    protected Database db = new Database();
    
    public abstract T get(long codigo) throws SQLException;

    public abstract List<T> getAll() throws SQLException;
}
