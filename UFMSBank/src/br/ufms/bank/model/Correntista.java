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
public class Correntista extends Usuario {

    private static final long serialVersionUID = 1L;
    
    private ContaCorrente contaCorrente;
    private ContaPoupanca contaPoupanca;

    private Telefone telefone;

    /**
     * Método responsável por gerar novos IDs para Pessoas (físicas ou juridicas). Este método deve
     * ser capaz de identificar o último ID gerado e gerar automaticamente novos IDs. Números
     * gerados jamais se repetirão.
     *
     * @return id
     */
    private static long gerarNovoID() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Cria um novo objeto Pessoa.
     *
     * @param usuario
     * @param senha
     */
    public Correntista(String usuario, String senha) {
        super(gerarNovoID(), usuario, senha);
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
    public final Telefone getTelefone() {
        return telefone;
    }

    /**
     * @param telefone the telefone to set
     */
    public final void setTelefone(Telefone telefone) {
        this.telefone = telefone;
    }

    @Override
    public TipoUsuario getTipo() {
        return TipoUsuario.CORRENTISTA;
    }

}
