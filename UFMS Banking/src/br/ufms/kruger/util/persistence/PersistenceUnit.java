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
package br.ufms.kruger.util.persistence;

import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author Kleber Kruger
 *
 * @param <B extends Bean>
 * @param <T>
 */
public abstract class PersistenceUnit<B extends Bean<T>, T extends Serializable> {

    public abstract B get(T id);

    public abstract Collection<B> getAll();

    public abstract void save(B bean);

    public void delete(B bean) {
        delete(bean.getID());
    }

    public abstract void delete(T id);
}