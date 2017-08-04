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

/**
 *
 * @author Kleber Kruger
 */
public class PessoaJuridica extends Correntista {

    private static final long serialVersionUID = 1L;
    private static final int TAMANHO_MIN_NOME = 2;

    private String nomeFantasia;
    private String razaoSocial;
    private CNPJ cnpj;

    public PessoaJuridica(String nomeFantasia, String razaoSocial, 
            String usuario, String senha, CNPJ cnpj) {
        
        super(usuario, senha);
        setNomeFantasia(nomeFantasia);
        setRazaoSocial(razaoSocial);
        setCNPJ(cnpj);
    }

    /**
     * @return the nomeFantasia
     */
    public final String getNomeFantasia() {
        return nomeFantasia;
    }

    /**
     * @param nomeFantasia the nomeFantasia to set
     */
    public final void setNomeFantasia(String nomeFantasia) {
        if (nomeFantasia == null) {
            throw new IllegalArgumentException("O nome não pode ser nulo");
        }

        // Remove os espaços do início e final da String caso exista.
        nomeFantasia = nomeFantasia.trim();

        if (nomeFantasia.isEmpty()) {
            throw new IllegalArgumentException("O nome não pode ser vazio");
        } else if (nomeFantasia.length() < TAMANHO_MIN_NOME) {
            throw new IllegalArgumentException("Nome muito curto");
        }
//        for (char c : nomeFantasia.toCharArray()) {
//            if (!Character.isAlphabetic(c) && !Character.isDigit(c) && !Character.isSpaceChar(c)) {
//                throw new IllegalArgumentException("Nome inválido");
//            }
//        }

        this.nomeFantasia = nomeFantasia;
    }
    
    /**
     * @return the razaoSocial
     */
    public final String getRazaoSocial() {
        return razaoSocial;
    }

    /**
     * @param razaoSocial the razaoSocial to set
     */
    public final void setRazaoSocial(String razaoSocial) {
        if (razaoSocial == null) {
            throw new IllegalArgumentException("O nome não pode ser nulo");
        }

        // Remove os espaços do início e final da String caso exista.
        razaoSocial = razaoSocial.trim();

        if (razaoSocial.isEmpty()) {
            throw new IllegalArgumentException("O nome não pode ser vazio");
        } else if (razaoSocial.length() < TAMANHO_MIN_NOME) {
            throw new IllegalArgumentException("Nome muito curto");
        }
//        for (char c : razaoSocial.toCharArray()) {
//            if (!Character.isAlphabetic(c) && !Character.isDigit(c) && !Character.isSpaceChar(c)) {
//                throw new IllegalArgumentException("Nome inválido");
//            }
//        }

        this.razaoSocial = razaoSocial;
    }

    /**
     * @return the cnpj
     */
    public final CNPJ getCNPJ() {
        return cnpj;
    }

    /**
     * @param cnpj the cnpj to set
     */
    public final void setCNPJ(CNPJ cnpj) {
        this.cnpj = cnpj;
    }

}
