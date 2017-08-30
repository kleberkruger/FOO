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
package br.ufms.banking.model.dao.file;

import br.ufms.banking.model.enumerate.CategoriaCorrentista;
import br.ufms.banking.model.enumerate.TipoConta;
import br.ufms.banking.model.domain.ContaBancaria;
import br.ufms.banking.model.domain.ContaCorrente;
import br.ufms.banking.model.domain.ContaPoupanca;
import br.ufms.banking.model.domain.Correntista;
import br.ufms.banking.model.domain.NumeroBancario;
import br.ufms.kruger.util.persistence.FileDAO;
import br.ufms.kruger.util.persistence.PersistenceException;
import java.lang.reflect.Field;
import java.nio.file.Paths;

/**
 *
 * @author Kleber Kruger
 */
public class ContaBancariaDAO extends FileDAO<ContaBancaria, NumeroBancario> {

    private final CorrentistaDAO correntistaDAO;

    public ContaBancariaDAO() {
        super(Paths.get(System.getProperty("user.home"), "ContasBancarias.txt"));

        this.correntistaDAO = new CorrentistaDAO();
    }

    @Override
    protected String encode(ContaBancaria bean) throws PersistenceException {
        String str = (bean.getTipo() == TipoConta.CONTA_CORRENTE)
                ? ((ContaCorrente) bean).getLimite().toString()
                : (bean.getTipo() == TipoConta.CONTA_POUPANCA)
                ? ((ContaPoupanca) bean).getCategoria().toString()
                : null;
        return bean.getClass().getSimpleName() + "|" + bean.getNumero() + "|" + bean.getSaldo() + "|" + str;
    }

    @Override
    protected ContaBancaria decode(String info) throws PersistenceException {
        try {
            String[] infos = info.split("\\|");
            Correntista correntista = correntistaDAO.get(Long.parseLong(infos[1]));
            if (infos[0].equalsIgnoreCase(ContaCorrente.class.getSimpleName())) {
                ContaCorrente cc = new ContaCorrente(correntista, null);

                // uso de relections para acessar os atributos da classe.
                Field atributoSaldo = cc.getClass().getDeclaredField("saldo");
                Field atributoLimite = cc.getClass().getDeclaredField("limite");
                // torna os atributos acessíveis, uma vez que são privados.
                atributoSaldo.setAccessible(true);
                atributoLimite.setAccessible(true);
                // alterando os valores dos atributos.
                atributoSaldo.setDouble(atributoSaldo, Double.parseDouble(infos[2]));
                atributoLimite.setDouble(atributoLimite, Double.parseDouble(infos[3]));

                return cc;

            } else if (infos[0].equalsIgnoreCase(ContaPoupanca.class.getSimpleName())) {
                ContaPoupanca cp = new ContaPoupanca(correntista, null);

                // uso de relections para acessar os atributos da classe.
                Field atributoSaldo = cp.getClass().getDeclaredField("saldo");
                Field atributoCategoria = cp.getClass().getDeclaredField("categoria");
                // torna os atributos acessíveis, uma vez que são privados.
                atributoSaldo.setAccessible(true);
                atributoCategoria.setAccessible(true);
                // alterando os valores dos atributos.
                atributoSaldo.setDouble(atributoSaldo, Double.parseDouble(infos[2]));
                atributoCategoria.set(atributoCategoria, CategoriaCorrentista.valueOf(infos[3]));

                return cp;
            }

        } catch (NoSuchFieldException | SecurityException
                | IllegalArgumentException | IllegalAccessException ex) {

            throw new PersistenceException(ex);
        }

        return null;
    }

}
