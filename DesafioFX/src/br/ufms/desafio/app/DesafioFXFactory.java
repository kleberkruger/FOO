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
package br.ufms.desafio.app;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kleberkruger
 */
public class DesafioFXFactory {
    
    private final Map<Class, Object> resourceMap;

    private DesafioFXFactory() {
        resourceMap = new HashMap<>();
    }

    public <T> T get(Class<T> c) {
        if (!resourceMap.containsKey(c)) {
            try {
                resourceMap.put(c, c.newInstance());
            } catch (InstantiationException | IllegalAccessException ex) {
                Logger.getLogger(DesafioFXFactory.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return (T) resourceMap.get(c);
    }

    public static DesafioFXFactory getInstance() {
        return DesafioFXFactoryHolder.INSTANCE;
    }

    private static class DesafioFXFactoryHolder {

        private static final DesafioFXFactory INSTANCE = new DesafioFXFactory();
    }
}
