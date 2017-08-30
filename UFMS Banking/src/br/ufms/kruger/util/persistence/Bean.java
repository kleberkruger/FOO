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
import java.util.Objects;

/**
 * Classe base para qualquer objeto serializável.
 *
 * @author Kleber Kruger
 * @param <T> o tipo do atributo id
 */
public class Bean<T extends Serializable> implements Serializable {

    private static final long serialVersionUID = 1L;

    protected T id;

    /**
     * Cria um novo objeto Bean. O construtor desta classe é protegido porque um objeto Bean só
     * existirá por meio de classes derivadas.
     */
    protected Bean() {
        this.id = null;
    }

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
     * @return o id
     */
    public T getID() {
        return id;
    }

    /**
     * @param id o novo id
     */
    protected void setID(T id) {
        this.id = id;
    }

    /**
     * Indica quando outro objeto é igual a este. Nesta implementaçao, qualquer objeto derivado de
     * Bean é igual a este desde que seja exatamente da mesma classe e tenha o mesmo ID. Caso
     * precise de outra lógica, sobrescreva este método.
     *
     * @param obj o objeto a comparar com este
     * @return {@code true} se este objeto é igual ao do argumento; {@code false} caso contrário.
     */
    @Override
    public boolean equals(Object obj) {
        if (id != null && obj instanceof Bean) {
            Bean x = (Bean) obj;
            return getClass() == x.getClass() && id.equals(x.id);
        }
        return super.equals(obj);
    }

    /**
     * Retorna um valor de hash code para este objeto. Nesta implementação, este valor é gerado por
     * uma combinação do hash code da classe (getClass().hashCode()) somado ao hash code do atributo
     * id (id.hashCode()).
     *
     * Classes semelhantes terão o mesmo valor base de hash code, que quando somado ao hash code do
     * id, terá o valor: valorBaseHashCodeClasse + idHashCode. Por exemplo, se o valor base do hash
     * code da classe {@code Cachorro} é 300 e o objeto {@code Cachorro c1} tem o id = 1, o retorno
     * deste método será 301.
     *
     * @return um valor de hash code para este objeto
     */
    @Override
    public int hashCode() {
        if (id != null) {
            int hash = 7;
            hash = 43 * hash + Objects.hashCode(getClass().hashCode() + id.hashCode());
            return hash;
        }
        return super.hashCode();
    }

}
