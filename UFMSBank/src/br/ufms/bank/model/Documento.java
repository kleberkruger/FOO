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

import br.ufms.bank.model.enumerate.TipoDocumento;
import java.io.Serializable;

/**
 * Classe abstrata que define um documento com número.
 *
 * @author Kleber Kruger
 * @param <T> o tipo do atributo número
 */
public abstract class Documento<T extends Serializable> implements Serializable {

    private static final long serialVersionUID = 1L;

    private T numero;
    private String numeroFormatado;

    /**
     * Cria um documento com número.
     *
     * @param numero
     */
    public Documento(T numero) {
        setNumero(numero);
    }

    /**
     * Retorna o tipo do documento.
     *
     * @return the tipo
     */
    public abstract TipoDocumento getTipo();

    /**
     * Retorna o número do documento com a máscara.
     *
     * @param numero
     * @return
     */
    protected abstract String getNumeroFormatado(T numero);

    /**
     * Valida o número do documento.
     *
     * @param numero
     * @return
     */
    protected abstract boolean validar(T numero);

    /**
     * @return the numero
     */
    public final T getNumero() {
        return numero;
    }

    /**
     * @return the numero formatado
     */
    public final String getNumeroFormatado() {
        return numeroFormatado;
    }

    /**
     * @param numero the numero to set
     */
    public final void setNumero(T numero) {
        if (numero == null) {
            throw new IllegalArgumentException(getMensagemNumeroNulo());
        } else if (!validar(numero)) {
            throw new IllegalArgumentException(getMensagemNumeroInvalido());
        }
        this.numero = numero;
        this.numeroFormatado = getNumeroFormatado(numero);
    }

    /**
     * Retorna a mensagem enviada na exceção quando um número null é informado.
     *
     * @return
     */
    protected String getMensagemNumeroNulo() {
        return "Número de documento nulo (null).";
    }

    /**
     * Retorna a mensagem enviada na exceção quando um número inválido é informado.
     *
     * @return
     */
    protected String getMensagemNumeroInvalido() {
        return "Número de documento inválido.";
    }

    @Override
    public String toString() {
        return getNumeroFormatado();
    }

}
