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

import br.ufms.bank.model.enumerate.TipoUsuario;

/**
 *
 * @author Kleber Kruger
 */
public class Bancario extends Usuario {
    
    private static final long serialVersionUID = 1L;
    
    private static final int TAMANHO_MIN_NOME = 4;

    private String nome;

    /**
     * Método responsável por gerar novos IDs para Bancários. Este método deve ser capaz de
     * identificar o último ID gerado e gerar automaticamente novos IDs. Números gerados jamais se
     * repetirão.
     *
     * @return id
     */
    private static long gerarNovoID() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Cria um novo objeto Bancario.
     *
     * @param nome
     * @param usuario
     * @param senha
     */
    public Bancario(String nome, String usuario, String senha) {
        super(gerarNovoID(), usuario, senha);

        setNome(nome);
    }

    /**
     * @return the nome
     */
    public final String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public final void setNome(String nome) {
        if (nome == null) {
            throw new IllegalArgumentException("O nome não pode ser nulo");
        }

        // Remove os espaços do início e final da String caso exista.
        nome = nome.trim();

        if (nome.isEmpty()) {
            throw new IllegalArgumentException("O nome não pode ser vazio");
        } else if(nome.length() < TAMANHO_MIN_NOME) {
            throw new IllegalArgumentException("Nome muito curto");
        }
        for (char c : nome.toCharArray()) {
            if (!Character.isAlphabetic(c) && !Character.isSpaceChar(c)) {
                throw new IllegalArgumentException("Nome inválido");
            }
        }

        this.nome = nome;
    }

    @Override
    public TipoUsuario getTipo() {
        return TipoUsuario.BANCARIO;
    }

}
