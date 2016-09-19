/*
 * Copyright (C) 2016 kleberkruger
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
package br.ufms.desafio.test;

import br.ufms.desafio.model.bean.Aluno;
import br.ufms.desafio.model.bean.Endereco;
import br.ufms.desafio.model.bean.Serie;
import br.ufms.desafio.model.bean.Telefone;
import br.ufms.desafio.model.bean.enumerate.NivelEnsino;
import br.ufms.desafio.model.dao.DAOFactory;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kleberkruger
 */
public class AlunoTester extends JogadorTester<Aluno> {

    public AlunoTester() {
        super(DAOFactory.getInstance().getAlunoDAO());
    }

    @Override
    protected void printBean(Aluno bean) {
        super.printBean(bean);
        
        if (bean.getSerie() != null) {
            new SerieTester().printBean(bean.getSerie());
        }
        if (bean.getEscola()!= null) {
            new EscolaTester().printBean(bean.getEscola());
        }
    }

    @Override
    protected Aluno createBean() {
        
        Telefone t1 = new Telefone();
        t1.setDDD("67");
        t1.setNumero("9-9845-1863");
        List<Telefone> tels = new ArrayList<>();
        tels.add(t1);
        
        Serie s = new Serie();
        s.setAno((short) 2);
        s.setNivel(NivelEnsino.SUPERIOR);
        
        Aluno a = new Aluno();
        a.setNome("Rafael Viana");
        a.setEmail("rafaelgov95@gmail.com");
        a.setUsuario("rafaelgov");
        a.setSenha("123");
        a.setCriacao(LocalDate.now());
        a.setNascimento(LocalDate.of(1994, 06, 18));
        a.setSerie(s);
        a.setTelefones(tels);
        
        return a;
    }

    @Override
    protected void updateBean(Aluno bean) {
        Endereco e = new Endereco();
        e.setLogradouro("Rua da Casa do Rafael");
        e.setNumero((short) 24);
        
        bean.setNome("Rafael Gouveia Viana");
        bean.setEndereco(e);
    }

}
