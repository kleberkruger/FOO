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
package br.ufms.bank.app;

import br.ufms.bank.model.Bancario;
import br.ufms.bank.model.CPF;
import br.ufms.bank.model.ContaCorrente;
import br.ufms.bank.model.Correntista;
import br.ufms.bank.model.PessoaFisica;
import br.ufms.bank.model.Telefone;

/**
 *
 * @author Kleber Kruger
 */
public class _Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Bancario b1 = new Bancario("  Kleber Kruger", " kleberkruger  ", "123");
        System.out.println(b1.hashCode());

        Bancario b2 = new Bancario("  Kleber Kruger", " kleberkruger  ", "123");
        System.out.println(b2.hashCode());

        PessoaFisica pf = new PessoaFisica("Kleber Kruger", "kleberkruger", "123", new CPF("02135730165"));
        System.out.println(pf.hashCode());

        ContaCorrente<PessoaFisica> cc = new ContaCorrente<>(pf);
    }

}
