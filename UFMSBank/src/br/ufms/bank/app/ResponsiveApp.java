/*
 * Copyright (C) 2017 http://www.guigarage.com
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

import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author Guigarage
 */
public class ResponsiveApp extends Application {

    private GridPane grid;
    private TextField txtField1;
    private TextField txtField2;
    private TextField txtField3;
    private TextField txtField4;
    private BooleanProperty smallProperty;

    private TextField createTextField(String input) {
        TextField txt = new TextField();
        txt.setPromptText(input);
        txt.setMinWidth(100);
        txt.setPrefWidth(400);
        txt.setMaxWidth(400);
        GridPane.setMargin(txt, new Insets(3));
        return txt;
    }

    public Node getNodeByRowColumnIndex(final int row, final int column, GridPane gridPane) {
        Node result = null;
        ObservableList<Node> childrens = gridPane.getChildren();
        for (Node node : childrens) {
            if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column) {
                result = node;
                break;
            }
        }
        return result;
    }

    private void changeToSmallLayout() {
        System.out.println("Small Layout: " + txtField1.getWidth());
//        GridPane.setConstraints(txtField1, 0, 0);
        GridPane.setConstraints(txtField2, 0, 1);
        GridPane.setConstraints(txtField3, 0, 2);
        GridPane.setConstraints(txtField4, 0, 3);
    }

    private void changeToLargeLayout() {
        System.out.println("Large Layout: " + txtField1.getWidth());
//        GridPane.setConstraints(txtField1, 0, 0);
        GridPane.setConstraints(txtField2, 1, 0);
        GridPane.setConstraints(txtField3, 0, 1);
        GridPane.setConstraints(txtField4, 1, 1);
    }

    @Override
    public void start(Stage stage) throws Exception {

        smallProperty = new SimpleBooleanProperty(false);
        smallProperty.bind(stage.widthProperty().lessThan(400));
        smallProperty.addListener((observable, oldValue, newValue) -> {
            if (newValue == true) {
                changeToSmallLayout();
            } else {
                changeToLargeLayout();
            }
        });

        txtField1 = createTextField("Nome Completo");
        txtField2 = createTextField("Nome de Usuário");
        txtField3 = createTextField("Senha");
        txtField4 = createTextField("Confirme sua senha");

        grid = new GridPane();
        grid.setAlignment(Pos.TOP_CENTER);
        grid.setStyle("-fx-background-color: blueviolet;");
        grid.setPadding(new Insets(20));

        grid.add(txtField1, 0, 0);
        grid.add(txtField2, 1, 0);
        grid.add(txtField3, 0, 1);
        grid.add(txtField4, 1, 1);

        Scene scene = new Scene(grid, 600, 300);
        stage.setScene(scene);
//        stage.widthProperty().addListener(e -> {
//            if (stage.getWidth() < 400) {
//                changeToSmallLayout();
//            } else {
//                changeToLargeLayout();
//            }
//        });

//        ResponsiveHandler.addResponsiveToWindow(stage);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
