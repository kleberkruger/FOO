/*
 * Copyright (C) 2016 Kleber Kruger
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
package br.ufms.desafio.model.bean;

import br.ufms.desafio.model.bean.enumerate.TipoEscola;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe que mapeia a tabela Escola do banco de dados.
 *
 * @author Kleber Kruger
 */
public class Escola extends Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    private TipoEscola tipo;
    private List<Professor> professores;

    /**
     * Cria um novo objeto Escola com o tipo nulo e a lista de professores vazia. O restante dos
     * atributos são inicializados de acordo com o construtor da superclasse Usuario.
     */
    public Escola() {
        this.professores = new ArrayList<>();
    }

    /**
     * @return the tipo
     */
    public TipoEscola getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(TipoEscola tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the professores
     */
    public List<Professor> getProfessores() {
        return professores;
    }

    /**
     * @param professores the professores to set
     */
    public void setProfessores(List<Professor> professores) {
        this.professores = professores;
    }

}
