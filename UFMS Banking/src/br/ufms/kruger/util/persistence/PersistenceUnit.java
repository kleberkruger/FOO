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

    /**
     * Sets the bean ID.
     *
     * @param bean
     * @param id
     */
    protected void setBeanID(B bean, T id) {
        bean.setID(id);
    }

    /**
     * Saves the bean object.
     *
     * @param bean
     * @throws br.ufms.kruger.util.persistence.PersistenceException
     */
    public abstract void save(B bean) throws PersistenceException;

    /**
     * Deletes the bean object.
     *
     * @param bean
     * @throws br.ufms.kruger.util.persistence.PersistenceException
     */
    public void delete(B bean) throws PersistenceException {
        delete(bean.getID());
    }

    /**
     * Deletes the bean object.
     *
     * @param id
     * @throws br.ufms.kruger.util.persistence.PersistenceException
     */
    public abstract void delete(T id) throws PersistenceException;

    /**
     * Gets the bean object.
     *
     * @param id
     * @return
     * @throws br.ufms.kruger.util.persistence.PersistenceException
     */
    public abstract B get(T id) throws PersistenceException;

    /**
     * Gets all bean objects.
     *
     * @return
     * @throws br.ufms.kruger.util.persistence.PersistenceException
     */
    public abstract Collection<B> getAll() throws PersistenceException;

}
