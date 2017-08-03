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
package br.ufms.bank.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * Classe base para qualquer "bean".
 *
 * @author Kleber Kruger
 * @param <T> tipo da chave identificadora
 */
public abstract class Bean<T extends Serializable> implements Serializable {

    private static final long serialVersionUID = 1L;

    private T id;

    /**
     * Cria um novo objeto Bean. O construtor desta classe é protegido porque um objeto Bean só
     * existirá por meio de classes derivadas.
     * 
     * @param id
     */
    protected Bean(T id) {
        this.id = id;
    }

    /**
     * @return the id
     */
    public T getID() {
        return id;
    }
    
    /**
     * @param id the id to set
     */
    protected void setID(T id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Bean) {
            Bean x = (Bean) obj;
            return getClass() == x.getClass() && id.equals(x.id);
        }
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.id);
        return hash;
    }

//    protected abstract T gerarNovoID();
}
