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
package br.ufms.banking.view.controller;

import br.ufms.banking.model.domain.Agencia;
import br.ufms.banking.model.domain.CPF;
import br.ufms.banking.model.domain.Correntista;
import br.ufms.banking.model.domain.Municipio;
import br.ufms.banking.model.domain.PessoaFisica;
import br.ufms.banking.model.domain.PessoaJuridica;
import br.ufms.banking.model.enumerate.UF;
import java.beans.PropertyChangeSupport;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Kleber Kruger
 */
public class TabelaCorrentistaController implements Initializable {

    @FXML
    private TableView<Correntista> correntistasTable;
    @FXML
    private TableColumn<Correntista, Integer> colunaNumero;
    @FXML
    private TableColumn<Correntista, String> colunaCorrentista;
    @FXML
    private TableColumn<Correntista, String> colunaTipo;

    private static List<Correntista> getCorrentistas() {
        List<Correntista> lista = new ArrayList<>();
        lista.add(new PessoaFisica(new Agencia(new Municipio("Coxim", UF.MS)), "Kleber Kruger", "kleberkruger", "123", new CPF("02135730165")));
        lista.add(new PessoaFisica(new Agencia(new Municipio("Coxim", UF.MS)), "Pedro Kruger", "pedrokruger", "123", new CPF("02135730165")));
        lista.add(new PessoaFisica(new Agencia(new Municipio("Coxim", UF.MS)), "Tamara Kruger", "tamarakruger", "123", new CPF("02135730165")));
//        lista.add(new PessoaJuridica(new Agencia(new Municipio("Coxim", UF.MS)), "Artcomp", "Artcomp Sistemas LTDA", "artcomp", "123", null));
        return lista;
    }

    private List<MyCorrentista> getItemsToAdd() {
        List<MyCorrentista> list = new ArrayList<>();
        getCorrentistas().forEach((c) -> {
            list.add(new MyCorrentista(c));
        });
        return list;
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        colunaNumero.setCellValueFactory(new PropertyValueFactory<>("ID"));
        colunaTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));

        PropertyValueFactory<Correntista, String> correntistaValueFactory = new PropertyValueFactory("nome") {

            @Override
            public ObservableValue call(TableColumn.CellDataFeatures param) {
                String correntista = param.getValue() instanceof PessoaFisica
                        ? ((PessoaFisica) param.getValue()).getNome()
                        : param.getValue() instanceof PessoaJuridica
                        ? ((PessoaJuridica) param.getValue()).getNomeFantasia() : null;
                return new ReadOnlyObjectWrapper<>(correntista);
            }

        };

        colunaCorrentista.setCellValueFactory(correntistaValueFactory);
        correntistasTable.getItems().setAll(FXCollections.observableList(
                new ArrayList<>(getCorrentistas())));
//        colunaNumero.setCellValueFactory(new PropertyValueFactory<>("numero"));
//        colunaCorrentista.setCellValueFactory(new PropertyValueFactory<>("correntista"));
//        correntistasTable.getItems().setAll(getItemsToAdd());
    }

    public class MyCorrentista {

        private Integer numero;
        private String correntista;

        public MyCorrentista(Correntista c) {
//            setNumero(c.getNumeroConta().getKey());
            setNumero(0);
            String nome = c instanceof PessoaFisica ? ((PessoaFisica) c).getNome()
                    : c instanceof PessoaJuridica ? ((PessoaJuridica) c).getNomeFantasia() : null;
            setCorrentista(nome);
        }

        public final Integer getNumero() {
            return numero;
        }

        public final void setNumero(Integer numero) {
            Integer oldNumero = this.numero;
            this.numero = numero;
            propertyChangeSupport.firePropertyChange(PROP_NUMERO, oldNumero, numero);
        }

        public final String getCorrentista() {
            return correntista;
        }

        public final void setCorrentista(String correntista) {
            String oldCorrentista = this.correntista;
            this.correntista = correntista;
            propertyChangeSupport.firePropertyChange(PROP_CORRENTISTA, oldCorrentista, correntista);
        }

        private final transient PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

        public static final String PROP_NUMERO = "numero";
        public static final String PROP_CORRENTISTA = "correntista";
    }

    public class MyCorrentistaFX {

        private final IntegerProperty numero = new SimpleIntegerProperty();
        private final StringProperty correntista = new SimpleStringProperty();

        public MyCorrentistaFX(Integer numero, String correntista) {
            setNumero(numero);
            setCorrentista(correntista);
        }

        public IntegerProperty numeroProperty() {
            return numero;
        }

        public Integer getNumero() {
            return numero.get();
        }

        public final void setNumero(Integer numero) {
            this.numero.set(numero);
        }

        public StringProperty correntistaProperty() {
            return correntista;
        }

        public String getCorrentista() {
            return correntista.get();
        }

        public final void setCorrentista(String correntista) {
            this.correntista.set(correntista);
        }
    }

}
