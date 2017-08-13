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
package br.ufms.banking.model.dao;

import br.ufms.banking.model.domain.CNPJ;
import br.ufms.banking.model.domain.CPF;
import br.ufms.banking.model.domain.Correntista;
import br.ufms.banking.model.domain.PessoaFisica;
import br.ufms.banking.model.domain.PessoaJuridica;
import br.ufms.banking.model.domain.Telefone;
import br.ufms.kruger.util.persistence.FileDAO;
import br.ufms.kruger.util.persistence.PersistenceException;
import java.nio.file.Paths;

/**
 *
 * @author Kleber Kruger
 */
public class CorrentistaDAO extends FileDAO<Correntista, Long> {

    public CorrentistaDAO() {
        super(Paths.get(System.getProperty("user.home"), "Correntistas.txt"));
    }

    @Override
    protected String encode(Correntista bean) throws PersistenceException {
        if (bean instanceof PessoaFisica) {
            PessoaFisica pf = (PessoaFisica) bean;
            return pf.getClass().getSimpleName() + "|"
                    + pf.getID() + "|" + pf.getNome() + "|" + pf.getCPF() + "|"
                    + pf.getTelefonePrincipal() + "|" + pf.getTelefoneSecundario() + "|"
                    + pf.getUsuario() + "|" + pf.getSenha();
        } else if (bean instanceof PessoaJuridica) {
            PessoaJuridica pj = (PessoaJuridica) bean;
            return pj.getClass().getSimpleName() + "|"
                    + pj.getID() + "|" + pj.getNomeFantasia() + "|" + pj.getRazaoSocial() + pj.getCNPJ() + "|"
                    + pj.getTelefonePrincipal() + "|" + pj.getTelefoneSecundario() + "|"
                    + pj.getUsuario() + "|" + pj.getSenha();
        }
        return null;
    }

    @Override
    protected Correntista decode(String info) throws PersistenceException {
        String[] infos = info.split("\\|");
        if (infos[0].equalsIgnoreCase(PessoaFisica.class.getSimpleName())) {
            PessoaFisica pf = new PessoaFisica(infos[2], infos[6], infos[7], new CPF(infos[3]),
                    new Telefone(infos[4]), new Telefone(infos[5]));
            setBeanID(pf, Long.parseLong(infos[1]));
            return pf;
        } else if (infos[0].equalsIgnoreCase(PessoaFisica.class.getSimpleName())) {
            PessoaJuridica pf = new PessoaJuridica(infos[2], infos[3], infos[7], infos[8],
                    new CNPJ(infos[4]), new Telefone(infos[5]), new Telefone(infos[6]));
            setBeanID(pf, Long.parseLong(infos[1]));
            return pf;
        }
        return null;
    }

}
