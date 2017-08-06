package com.guigarage.responsive;

import javafx.beans.property.StringProperty;
import javafx.collections.ListChangeListener;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.stage.Window;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * A util class that defines some static methods to work with the JavaFX Scene
 * Graph.
 */
public class Util {

    public static List<Node> getAllNodesInWindow(Window window) {
        Parent root = Optional.of(window).map(w -> w.getScene()).map(s -> s.getRoot()).get();
        if (root == null) {
            return new ArrayList<>();
        } else {
            List<Node> ret = new ArrayList<>();
            ret.add(root);
            ret.addAll(getAllNodesInParent(root));
            return ret;
        }
    }

    public static List<Node> getAllNodesInParent(Parent parent) {
        List<Node> ret = new ArrayList<>();
        parent.getChildrenUnmodifiable().stream().map((child) -> {
            ret.add(child);
            return child;
        }).filter((child) -> (child instanceof Parent)).forEachOrdered((child) -> {
            ret.addAll(getAllNodesInParent((Parent) child));
        });
        return ret;
    }

    private static ListChangeListener<Node> createRecursiveChildObserver(Consumer<Node> onRemove, Consumer<Node> onAdd) {
        return new ListChangeListener<Node>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends Node> change) {
                while (change.next()) {
                    if (change.wasAdded()) {
                        change.getAddedSubList().stream().map((addedChild) -> {
                            if (addedChild instanceof Parent) {
                                ((Parent) addedChild).getChildrenUnmodifiable().addListener(this);
                            }
                            return addedChild;
                        }).forEachOrdered((addedChild) -> {
                            onAdd.accept(addedChild);
                        });
                    }
                    if (change.wasRemoved()) {
                        change.getRemoved().stream().map((removedChild) -> {
                            if (removedChild instanceof Parent) {
                                ((Parent) removedChild).getChildrenUnmodifiable().removeListener(this);
                            }
                            return removedChild;
                        }).forEachOrdered((removedChild) -> {
                            onRemove.accept(removedChild);
                        });
                    }
                }
            }
        };
    }

    public static void registerRecursiveChildObserver(Window window, Consumer<Node> onRemove, Consumer<Node> onAdd) {
        List<Node> allNodes = getAllNodesInWindow(window);
        ListChangeListener<Node> listener = createRecursiveChildObserver(onRemove, onAdd);
        allNodes.stream().filter((child) -> (child instanceof Parent)).map((child) -> (Parent) child).forEachOrdered((parent) -> {
            parent.getChildrenUnmodifiable().addListener(listener);
        });
    }

    public static void bindStyleSheetToWindow(Window window, StringProperty stylesheet) {
        window.sceneProperty().addListener(e -> {
            if (window.getScene() != null) {
                window.getScene().getStylesheets().add(stylesheet.get());
            }
        });
        if (window.getScene() != null) {
            window.getScene().getStylesheets().add(stylesheet.get());
        }

        stylesheet.addListener((obs, o, n) -> {
            if (window.getScene() != null) {
                int oldPos = -1;
                if (o != null) {
                    oldPos = window.getScene().getStylesheets().indexOf(o);
                    window.getScene().getStylesheets().remove(o);
                }
                if (n != null) {
                    if (oldPos >= 0) {
                        window.getScene().getStylesheets().add(oldPos, n);
                    } else {
                        window.getScene().getStylesheets().add(n);
                    }
                }
            }
        });
    }
}
