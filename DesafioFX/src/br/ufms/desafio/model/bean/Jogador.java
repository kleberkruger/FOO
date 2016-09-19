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

import br.ufms.desafio.model.bean.enumerate.Deficiencia;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * Classe que mapeia a tabela Jogador do banco de dados.
 *
 * @author Kleber Kruger
 */
public class Jogador extends Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    private LocalDate nascimento;
    private Set<Deficiencia> deficiencias;

    /**
     * Cria um novo objeto Jogador com a data de nascimento nula e o HashSet de deficiências vazio.
     * O restante dos atributos são inicializados de acordo com o construtor da superclasse Usuario.
     */
    protected Jogador() {
        this.deficiencias = new HashSet<>();
    }

    /**
     * @return the nascimento
     */
    public LocalDate getNascimento() {
        return nascimento;
    }

    /**
     * @param nascimento the nascimento to set
     */
    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }

    /**
     * @return the deficiencias
     */
    public Set<Deficiencia> getDeficiencias() {
        return deficiencias;
    }

    /**
     * @return the deficiencias
     */
    public String getDeficienciasString() {
        if (deficiencias == null) {
            return null;
        } else if (deficiencias.isEmpty()) {
            return "";
        }

        Deficiencia array[] = deficiencias.toArray(new Deficiencia[deficiencias.size()]);
        StringBuilder str = new StringBuilder(array[0].toString());
        for (int i = 1; i < array.length; i++) {
            str.append(",").append(array[i]);
        }
        return str.toString();
    }

    /**
     * @param deficiencias the deficiencias to set
     */
    public void setDeficiencias(Set<Deficiencia> deficiencias) {
        this.deficiencias = deficiencias;
    }

    /**
     * @param deficiencias the deficiencias to set
     */
    public void setDeficiencias(String[] deficiencias) {
        if (deficiencias == null) {
            this.deficiencias = null;
        } else {
            this.deficiencias.clear();
            for (String deficiencia : deficiencias) {
                if (!deficiencia.isEmpty()) {
                    this.deficiencias.add(Deficiencia.valueOf(deficiencia));
                }
            }
        }
    }

    /**
     * @return the deficiente
     */
    public boolean isDeficiente() {
        return deficiencias.size() > 0;
    }

}
