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
package examples;

import br.ufms.bank.model.Bancario;
import br.ufms.bank.model.CPF;
import br.ufms.bank.model.ContaCorrente;
import br.ufms.bank.model.PessoaFisica;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class JAXBExample {

    public static void main(String[] args) {

        Customer customer = new Customer("Kleber", 28, 1);
        ContaCorrente<PessoaFisica> cc = new ContaCorrente<>(
                new PessoaFisica("Kleber Kruger", "kleberkruger", "123", new CPF("02135730165")));
//        customer.setId(100);
//        customer.setName("mkyong");
//        customer.setAge(29);

        try {
            PessoaFisica pf = new PessoaFisica("Kleber Kruger", "kleberkruger", "123", new CPF("02135730165"));
            Bancario b = new Bancario("Kleber", "kleber", "123");

            Path path = Paths.get(System.getProperty("user.home"), "Teste.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(PessoaFisica.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            jaxbMarshaller.marshal(pf, Files.newOutputStream(path));
            jaxbMarshaller.marshal(pf, System.out);

        } catch (JAXBException | IOException e) {
            System.err.println(e.getMessage());
        }

    }
}
