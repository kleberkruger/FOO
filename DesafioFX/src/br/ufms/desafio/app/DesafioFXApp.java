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
package br.ufms.desafio.app;

import br.ufms.desafio.model.bean.Endereco;
import br.ufms.desafio.model.bean.Telefone;
import br.ufms.desafio.model.bean.TipoTelefone;
import br.ufms.desafio.model.bean.Usuario;
import br.ufms.desafio.model.dao.MunicipioDAO;
import br.ufms.desafio.model.dao.UsuarioDAO;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Kleber Kruger
 */
public class DesafioFXApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader();

        // Carrega arquivo fxml do pacote view.fxml
        Parent root = (Parent) loader.load(getClass().getClassLoader().getResourceAsStream(
                "br/ufms/desafio/view/fxml/Login.fxml"));

        Scene scene = new Scene(root);

        // Adiciona o arquivo CSS para a personalização desta cena
        scene.getStylesheets().add(getClass().getClassLoader().getResource(
                "br/ufms/desafio/view/css/desafio.css").toExternalForm());

        // Altera o título da janela para "DesafioFX", define a cena e a exibe na tela
        stage.setTitle("DesafioFX");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);

        try {
            MunicipioDAO mDao = new MunicipioDAO();
            Endereco e = new Endereco();
            e.setLogradouro("Rua Vale da Esperança");
            e.setNumero(new Short("95"));
            e.setSn(true);
            e.setBairro("Jardim das Acácias");
            e.setCep("79400-000");
            e.setMunicipio(mDao.get(5452L));

            Usuario u = new Usuario();
            u.setNome("Kleber Kruger");
            u.setUsuario("kleberkruger");
            u.setSenha("teste");
            u.setEmail("kleberkruger@gmail.com");
            u.setEndereco(e);
            u.setCriacao(LocalDate.now());

            List<Telefone> tels = new ArrayList<>();
            Telefone t1 = new Telefone();
            t1.setDDD(67);
            t1.setNumero(999395298);
            t1.setTipo(TipoTelefone.CELULAR);
            tels.add(t1);

            Telefone t2 = new Telefone();
            t2.setDDD(67);
            t2.setNumero(998393619);
            t2.setTipo(TipoTelefone.CELULAR);
            tels.add(t2);

            u.setTelefone(tels);

            UsuarioDAO uDao = new UsuarioDAO();
            uDao.save(u);

            Usuario u2 = uDao.get(u.getCodigo());
            System.out.println(u2.getCodigo());

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

}
