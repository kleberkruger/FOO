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
package br.ufms.bank.controller;

import br.ufms.bank.model.ContaBancaria;
import br.ufms.bank.model.ContaCorrente;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kleber Kruger
 */
public class Banco {
    
    private final List<ContaBancaria> contas;

    public Banco() {
        this(new ArrayList<>());
    }

    public Banco(List<ContaBancaria> contas) {
        this.contas = contas;
    }
    
    public List<ContaBancaria> login(String usuario, String senha) {
        List<ContaBancaria> lista = new ArrayList<>();
        for (ContaBancaria conta : contas) {
            if (conta.getCorrentista().getUsuario().equalsIgnoreCase(usuario)) {
                lista.add(conta);
            }
        }
        return lista;
    }
    
    public void criarConta() {
        
        
    }
    
    public void excluirConta() {
        
    }
}
