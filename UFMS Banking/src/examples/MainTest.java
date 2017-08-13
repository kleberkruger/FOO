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

import br.ufms.banking.model.dao.BancarioDAO;
import br.ufms.banking.model.domain.Bancario;
import br.ufms.kruger.util.persistence.PersistenceException;

/**
 *
 * @author Kleber Kruger
 */
public class MainTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        try {
            Bancario bancario = new Bancario("Kleber Kr", "kleberkruger", "123");
            new BancarioDAO().save(bancario);
            
            bancario = new BancarioDAO().get(1L);
            System.out.println(bancario.getNome());
            
            bancario.setNome("Kleber Kruger");
            new BancarioDAO().save(bancario);
            
            for (Bancario b : new BancarioDAO().getAll()) {
                System.out.println(b.getNome());
            }
            
            BancarioDAO dao = new BancarioDAO();
            dao.delete(bancario);
            
            System.out.println("Bancarios: ");
            for (Bancario b : dao.getAll()) {
                System.out.println(b.getNome());
            }
            System.out.println("---");
            
        } catch (PersistenceException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
}
