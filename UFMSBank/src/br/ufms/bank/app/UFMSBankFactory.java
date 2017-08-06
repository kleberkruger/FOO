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

import br.ufms.bank.view.controller.LoginController;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;

/**
 *
 * @author Kleber Kruger
 */
public class UFMSBankFactory {

    private LoginController loginController;

    private FXMLLoader createFXMLLoader(URL location) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(location);
//        loader.setResources(ResourceBundle.getBundle(MFXResources.properties));
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        return loader;
    }

    public LoginController getLoginController() throws IOException {
        if (loginController == null) {
            URL location = this.getClass().getResource("");
            try (InputStream inputStream = location.openStream()) {
                FXMLLoader loader = createFXMLLoader(location);
                loader.load(inputStream);
                loginController = (LoginController) loader.getController();
            }
        }
        return loginController;
    }

}
