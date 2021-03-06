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
package br.ufms.banking.model.enumerate;

/**
 *
 * @author Kleber Kruger
 */
public enum TipoUsuario {

    BANCARIO("Bancário"),
    CORRENTISTA("Correntista");

    private String tipo;

    private TipoUsuario(String tipo) {
        this.tipo = tipo;
    }
    
    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

//    @Override
//    public String toString() {
//        switch (this) {
//            case BANCARIO:
//                return "Bancário";
//            case CORRENTISTA:
//                return "Correntista";
//        }
//        return super.toString();
//    }
}
