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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.LinkedHashSet;

/**
 *
 * @author Kleber Kruger
 *
 * @param <B>
 * @param <T>
 */
public abstract class FileDAO<B extends Bean<T>, T extends Serializable> extends PersistenceUnit<B, T> {

    private final Collection<B> beans;
    private final Path path;
    private volatile boolean loaded;

    public FileDAO(Path path) {
        this.path = path;
        this.beans = new LinkedHashSet<>();
        this.loaded = false;
    }

    protected void insert(B bean) throws PersistenceException {
        getAll().add(bean);
        saveToFile(beans);
    }

    protected void update(B bean) throws PersistenceException {
        if (getAll().remove(bean)) {
            beans.add(bean);
        }
        saveToFile(beans);
    }

    @Override
    public void save(B bean) throws PersistenceException {
        if (!getAll().contains(bean)) {
            insert(bean);
        } else {
            update(bean);
        }
    }

    @Override
    public void delete(T id) throws PersistenceException {
        getAll().removeIf((B bean) -> {
            return id == bean.getID();
        });
        saveToFile(beans);
    }

    @Override
    public B get(T id) throws PersistenceException {
        for (B bean : getAll()) {
            if (bean.getID() == id) {
                return bean;
            }
        }
        return null;
    }

    @Override
    public Collection<B> getAll() throws PersistenceException {
        if (!loaded) {
            loadFromFile(beans);
            loaded = true;
        }
        return beans;
    }

    private void saveToFile(Collection<B> c) throws PersistenceException {
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (B bean : c) {
                writer.append(encode(bean));
                writer.newLine();
            }
        } catch (IOException ex) {
            throw new PersistenceException(ex);
        }
    }

    private void loadFromFile(Collection<B> c) throws PersistenceException {
        c.clear();
        if (Files.exists(path)) {
            try (BufferedReader reader = Files.newBufferedReader(path)) {
                while (reader.ready()) {
                    c.add(decode(reader.readLine()));
                }
            } catch (IOException ex) {
                throw new PersistenceException(ex);
            }
        }
    }

    protected abstract String encode(B bean) throws PersistenceException;

    protected abstract B decode(String info) throws PersistenceException;

}
