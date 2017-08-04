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
package br.ufms.bank.util;

import java.util.Collection;
import java.util.InputMismatchException;

/**
 *
 * @author Kleber Kruger
 */
public class Validador {

    private static final int TAMANHO_MIN_NOME = 4;
    private static final int TAMANHO_MIN_NOME_FANTASIA = 4;
    private static final int TAMANHO_MIN_RAZAO_SOCIAL = 4;
    private static final int TAMANHO_MIN_USUARIO = 3;
    private static final int TAMANHO_MIN_SENHA = 3;

    /**
     *
     * @param nome
     * @throws IllegalArgumentException
     */
    public static void validarNome(String nome) throws IllegalArgumentException {
        if (nome == null) {
            throw new IllegalArgumentException("O nome não pode ser nulo.");
        }

        // Remove os espaços do início e final da String caso exista.
        nome = nome.trim();

        if (nome.isEmpty()) {
            throw new IllegalArgumentException("O nome não pode ser vazio.");
        } else if (nome.length() < TAMANHO_MIN_NOME) {
            throw new IllegalArgumentException("Nome muito curto.");
        }
        for (char c : nome.toCharArray()) {
            if (!Character.isAlphabetic(c) && !Character.isSpaceChar(c)) {
                throw new IllegalArgumentException("Nome inválido.");
            }
        }
    }

    /**
     *
     * @param nomeFantasia
     * @throws IllegalArgumentException
     */
    public static void validarNomeFantasia(String nomeFantasia) throws IllegalArgumentException {
        if (nomeFantasia == null) {
            throw new IllegalArgumentException("O nome não pode ser nulo");
        }

        // Remove os espaços do início e final da String caso exista.
        nomeFantasia = nomeFantasia.trim();

        if (nomeFantasia.isEmpty()) {
            throw new IllegalArgumentException("O nome não pode ser vazio");
        } else if (nomeFantasia.length() < TAMANHO_MIN_NOME_FANTASIA) {
            throw new IllegalArgumentException("Nome muito curto");
        }
//        for (char c : nomeFantasia.toCharArray()) {
//            if (!Character.isAlphabetic(c) && !Character.isDigit(c) && !Character.isSpaceChar(c)) {
//                throw new IllegalArgumentException("Nome inválido");
//            }
//        }
    }

    public static void validarRazaoSocial(String razaoSocial) {
        if (razaoSocial == null) {
            throw new IllegalArgumentException("O nome não pode ser nulo");
        }

        // Remove os espaços do início e final da String caso exista.
        razaoSocial = razaoSocial.trim();

        if (razaoSocial.isEmpty()) {
            throw new IllegalArgumentException("O nome não pode ser vazio");
        } else if (razaoSocial.length() < TAMANHO_MIN_RAZAO_SOCIAL) {
            throw new IllegalArgumentException("Nome muito curto");
        }
//        for (char c : razaoSocial.toCharArray()) {
//            if (!Character.isAlphabetic(c) && !Character.isDigit(c) && !Character.isSpaceChar(c)) {
//                throw new IllegalArgumentException("Nome inválido");
//            }
//        }
    }

    /**
     * Valida o nome de usuário.
     *
     * @param usuario
     * @throws IllegalArgumentException
     */
    public static void validarUsuario(String usuario) throws IllegalArgumentException {
        validarUsuario(usuario, null);
    }

    /**
     * Valida o nome de usuário.
     *
     * @param usuario
     * @param usuarios
     *
     * @throws IllegalArgumentException
     */
    public static void validarUsuario(String usuario, Collection<String> usuarios) throws IllegalArgumentException {
        if (usuario == null) {
            throw new IllegalArgumentException("O nome de usuário não pode ser nulo.");
        }

        // Remove os espaços do início e final da String caso exista.
        usuario = usuario.trim();

        // Verifica se é vazio ou muito curto
        if (usuario.isEmpty()) {
            throw new IllegalArgumentException("O nome de usuário não pode ser vazio.");
        } else if (usuario.length() < TAMANHO_MIN_USUARIO) {
            throw new IllegalArgumentException("Nome de usuário muito curto.");
        }

        // Verifica se é inválido
        boolean temAlpha = false;
        for (char c : usuario.toCharArray()) {
            if (Character.isAlphabetic(c)) {
                temAlpha = true;
            } else if (!Character.isDigit(c)) {
                throw new IllegalArgumentException("Nome de usuário inválido.");
            }
        }
        if (!temAlpha) {
            throw new IllegalArgumentException("Nome de usuário inválido.");
        }

        // Verifica se o nome de usuário informado já existe
        if (usuarios != null && usuarios.contains(usuario)) {
            throw new IllegalArgumentException("Usuário já existente.");
        }
    }

    /**
     *
     * @param senha
     * @throws IllegalArgumentException
     */
    public static void validarSenha(String senha) throws IllegalArgumentException {
        if (senha == null) {
            throw new IllegalArgumentException("A senha não pode ser nula.");
        } else if (senha.isEmpty()) {
            throw new IllegalArgumentException("A senha não pode ser vazia.");
        } else if (senha.length() < TAMANHO_MIN_SENHA) {
            throw new IllegalArgumentException("Senha muito curta.");
        }
    }

    /**
     *
     * @param cpf
     * @return
     */
    public static boolean validarCPF(String cpf) {
        // considera-se erro CPF's formados por uma sequencia de numeros iguais
        if (cpf.equals("00000000000") || cpf.equals("11111111111") || cpf.equals("22222222222")
                || cpf.equals("33333333333") || cpf.equals("44444444444") || cpf.equals("55555555555")
                || cpf.equals("66666666666") || cpf.equals("77777777777") || cpf.equals("88888888888")
                || cpf.equals("99999999999") || (cpf.length() != 11)) {

            return false;
        }

        char dig10, dig11;
        int sm, i, r, num, peso;

        // "try" - protege o codigo para eventuais erros de conversao de tipo (int)
        try {
            // Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 10;
            for (i = 0; i < 9; i++) {
                // converte o i-esimo caractere do CPF em um numero: 
                // por exemplo, transforma o caractere '0' no inteiro 0 
                // (48 eh a posicao de '0' na tabela ASCII)         
                num = (int) (cpf.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11)) {
                dig10 = '0';
            } else {
                dig10 = (char) (r + 48); // converte no respectivo caractere numerico
            }
            // Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 11;
            for (i = 0; i < 10; i++) {
                num = (int) (cpf.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11)) {
                dig11 = '0';
            } else {
                dig11 = (char) (r + 48);
            }

            // Verifica se os digitos calculados conferem com os digitos informados.
            return (dig10 == cpf.charAt(9)) && (dig11 == cpf.charAt(10));

        } catch (InputMismatchException ex) {
            return false;
        }
    }

    /**
     *
     * @param cnpj
     * @return
     */
    public static boolean validarCNPJ(String cnpj) {
        // considera-se erro CNPJ's formados por uma sequencia de numeros iguais
        if (cnpj.equals("00000000000000") || cnpj.equals("11111111111111")
                || cnpj.equals("22222222222222") || cnpj.equals("33333333333333")
                || cnpj.equals("44444444444444") || cnpj.equals("55555555555555")
                || cnpj.equals("66666666666666") || cnpj.equals("77777777777777")
                || cnpj.equals("88888888888888") || cnpj.equals("99999999999999")
                || (cnpj.length() != 14)) {

            return false;
        }

        char dig13, dig14;
        int sm, i, r, num, peso;

        // "try" - protege o código para eventuais erros de conversao de tipo (int)
        try {
            // Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 2;
            for (i = 11; i >= 0; i--) {
                // converte o i-ésimo caractere do CNPJ em um número:
                // por exemplo, transforma o caractere '0' no inteiro 0
                // (48 eh a posição de '0' na tabela ASCII)
                num = (int) (cnpj.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso + 1;
                if (peso == 10) {
                    peso = 2;
                }
            }

            r = sm % 11;
            if ((r == 0) || (r == 1)) {
                dig13 = '0';
            } else {
                dig13 = (char) ((11 - r) + 48);
            }

            // Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 2;
            for (i = 12; i >= 0; i--) {
                num = (int) (cnpj.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso + 1;
                if (peso == 10) {
                    peso = 2;
                }
            }

            r = sm % 11;
            if ((r == 0) || (r == 1)) {
                dig14 = '0';
            } else {
                dig14 = (char) ((11 - r) + 48);
            }

            // Verifica se os dígitos calculados conferem com os dígitos informados.
            return (dig13 == cnpj.charAt(12)) && (dig14 == cnpj.charAt(13));

        } catch (InputMismatchException ex) {
            return false;
        }
    }

}
