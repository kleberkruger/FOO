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
import br.ufms.bank.util.Validador;
import javax.xml.bind.annotation.XmlAttribute;

/**
 *
 * @author Kleber Kruger
 */
public class Bancario extends Usuario {

    private static final long serialVersionUID = 1L;

    private String nome;

    /**
     * Método responsável por gerar novos IDs para Bancários. Este método deve ser capaz de
     * identificar o último ID gerado e gerar automaticamente novos IDs. Números gerados jamais se
     * repetirão.
     *
     * @return id
     */
    private static long gerarNovoID() {
        throw new UnsupportedOperationException("Implemente este método.");
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
        Validador.validarNome(nome);
        this.nome = nome;
    }

    @Override
    public TipoUsuario getTipo() {
        return TipoUsuario.BANCARIO;
    }

}
