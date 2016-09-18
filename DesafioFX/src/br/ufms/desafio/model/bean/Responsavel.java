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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe que mapeia a tabela Responsavel do banco de dados.
 *
 * @author Kleber Kruger
 */
public class Responsavel extends Jogador implements Serializable {

    private static final long serialVersionUID = 1L;

    private String cpf;
    private List<Dependencia> dependencias;

    /**
     * Cria um novo objeto Responsavel com o CPF nulo e a lista de dependências vazia. O restante
     * dos atributos são inicializados conforme os construtores das superclasses Jogador e Usuario.
     */
    public Responsavel() {
        this.dependencias = new ArrayList<>();
    }

    /**
     * @return the cpf
     */
    public String getCPF() {
        return cpf;
    }

    /**
     * @param cpf the cpf to set
     */
    public void setCPF(String cpf) {
        this.cpf = cpf;
    }

    /**
     * @return the dependencias
     */
    public List<Dependencia> getDependencias() {
        return dependencias;
    }

    /**
     * @param dependencias the dependencias to set
     */
    public void setDependencias(List<Dependencia> dependencias) {
        this.dependencias = dependencias;
    }

}
