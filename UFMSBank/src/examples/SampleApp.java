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

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.beans.InvalidationListener;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;

public class SampleApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        final Label lbl1 = new Label("content");
        final TitledPane tp1 = new TitledPane("First TP", lbl1);

        final Label lbl2 = new Label("more content");
        final TitledPane tp2 = new TitledPane("Second TP", lbl2);

        final VBox rootPane = new VBox(tp1, tp2);

        tp1.heightProperty().addListener((InvalidationListener) observable -> {
            updateWindowHeight(rootPane);
        });

        tp2.heightProperty().addListener((InvalidationListener) observable -> {
            updateWindowHeight(rootPane);
        });

        final Scene scene = new Scene(rootPane);
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private void updateWindowHeight(final VBox rootPane) {
        final Scene scene = rootPane.getScene();
        if (scene == null) {
            return;
        }
        final Window window = scene.getWindow();
        if (window == null) {
            return;
        }
        final double rootPrefHeight = rootPane.prefHeight(-1);
        final double decorationHeight = window.getHeight() - scene.getHeight(); // window decorations
        window.setHeight(rootPrefHeight + decorationHeight);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
