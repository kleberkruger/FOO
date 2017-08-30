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
package examples;

/**
 *
 * @author Kleber Kruger
 */
public abstract class Acessor {

    private static Acessor instance;

    static Acessor getInstance() {
        Acessor a = instance;
        if (a != null) {
            return a;
        }
        return createInstance();
    }

    private static Acessor createInstance() {

        try {
            Class.forName(Exposed.class.getName(), true, Exposed.class.getClassLoader());
        } catch (ClassNotFoundException ex) {
            throw new IllegalStateException(ex);
        }

        return instance;
    }

    public static void setInstance(Acessor acessor) {
        if (instance != null) {
            throw new IllegalStateException("Acessor instance already set");
        }
        instance = acessor;
    }

    protected abstract Exposed createExposed();

    protected abstract void sayHello(Exposed exposed);
}
