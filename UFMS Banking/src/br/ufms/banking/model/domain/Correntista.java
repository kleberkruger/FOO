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
package br.ufms.banking.model.domain;

import br.ufms.banking.model.enumerate.TipoCorrentista;
import br.ufms.banking.model.enumerate.TipoUsuario;
import br.ufms.banking.util.Validador;

/**
 *
 * @author Kleber Kruger
 */
public abstract class Correntista extends Usuario {

    private static final long serialVersionUID = 1L;
    
    // O número da conta é gerado no momento da inserção na base de dados.
    private NumeroBancario numeroConta;

    private Telefone telefone;
    private Agencia agencia;
    private ContaCorrente contaCorrente;
    private ContaPoupanca contaPoupanca;

    /**
     * Cria um novo objeto Pessoa.
     *
     * @param usuario
     * @param senha
     * @param telefone
     * @param agencia
     */
    public Correntista(Agencia agencia, String usuario, String senha, Telefone telefone) {
        super(usuario, senha);

        setAgencia(agencia);
        setTelefone(telefone);
        this.numeroConta = null;
    }
    
    /**
     * @return the telefone
     */
    public final Telefone getTelefone() {
        return telefone;
    }

    /**
     * @param telefone the telefone to set
     */
    public final void setTelefone(Telefone telefone) {
        this.telefone = telefone;
    }

    /**
     * @return the numeroConta
     */
    public final NumeroBancario getNumeroConta() {
        return numeroConta;
    }

    /**
     * @return the agencia
     */
    public final Agencia getAgencia() {
        return agencia;
    }

    /**
     * @param agencia the agencia to set
     */
    public final void setAgencia(Agencia agencia) {
        this.agencia = agencia;

        if (contaCorrente != null) {
            contaCorrente.setAgencia(numeroConta);
        }
        if (contaPoupanca != null) {
            contaPoupanca.setAgencia(numeroConta);
        }
    }

    /**
     * Registra o número de uma conta para um correntista. Caso o correntista já esteja associado a
     * uma conta, este método lança uma exceção.
     *
     * @param correntista
     * @param numeroConta
     *
     * @throws java.lang.IllegalStateException
     */
    public static void registrarNumeroConta(Correntista correntista,
            NumeroBancario numeroConta) throws IllegalStateException {

        Validador.validarNumeroConta(numeroConta.getNumero());

        if (correntista.numeroConta != null && correntista.numeroConta != numeroConta) {
            throw new IllegalStateException("Este correntista já está associado a um outro número de conta.");
        }
        correntista.numeroConta = numeroConta;
    }

    @Override
    public TipoUsuario getTipoUsuario() {
        return TipoUsuario.CORRENTISTA;
    }

    public abstract TipoCorrentista getTipoCorrentista();

}
