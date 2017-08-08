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

import br.ufms.bank.model.enumerate.TipoUsuario;

/**
 *
 * @author Kleber Kruger
 */
public class Correntista extends Usuario {

    private static final long serialVersionUID = 1L;

    private transient ContaCorrente contaCorrente;
    private transient ContaPoupanca contaPoupanca;

    private Telefone telefonePrincipal;
    private Telefone telefoneSecundario;

    /**
     * Método responsável por gerar novos IDs para Pessoas (físicas ou juridicas). Este método deve
     * ser capaz de identificar o último ID gerado e gerar automaticamente novos IDs. Números
     * gerados jamais se repetirão.
     *
     * @return id
     */
    private static long gerarNovoID() {
        throw new UnsupportedOperationException("Implemente este método.");
    }

    /**
     * Cria um novo objeto Pessoa.
     *
     * @param id
     * @param usuario
     * @param senha
     * @param telefonePrincipal
     * @param telefoneSecundario
     */
    protected Correntista(Long id, String usuario, String senha, Telefone telefonePrincipal,
            Telefone telefoneSecundario) {

        super(id == null ? gerarNovoID() : id, usuario, senha);

        this.telefonePrincipal = telefonePrincipal;
        this.telefoneSecundario = telefoneSecundario;
    }

    /**
     * Registra a conta corrente para este correntista.
     *
     * @param cc
     */
    protected void registrarConta(ContaCorrente cc) {
        if (contaCorrente != null) {
            throw new RuntimeException("");
        }
        this.contaCorrente = cc;
    }

    /**
     * Registra a conta poupança para este correntista.
     *
     * @param cp
     */
    protected void registrarConta(ContaPoupanca cp) {
        if (contaPoupanca != null) {
            throw new RuntimeException("");
        }
        this.contaPoupanca = cp;
    }

    /**
     * @return the telefone
     */
    public final Telefone getTelefonePrincipal() {
        return telefonePrincipal;
    }

    /**
     * @param telefone the telefone to set
     */
    public final void setTelefonePrincipal(Telefone telefone) {
        this.telefonePrincipal = telefone;
    }

    /**
     * @return the telefone
     */
    public final Telefone getTelefoneSecundario() {
        return telefoneSecundario;
    }

    /**
     * @param telefone the telefone to set
     */
    public final void setTelefoneSecundario(Telefone telefone) {
        this.telefoneSecundario = telefone;
    }

    @Override
    public TipoUsuario getTipo() {
        return TipoUsuario.CORRENTISTA;
    }

}
