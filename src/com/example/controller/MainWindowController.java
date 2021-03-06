package com.example.controller;

import com.example.model.CivilStatus;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {

    @FXML
    private TextField nameTextField;

    @FXML
    private RadioButton maleRadio;

    @FXML
    private ToggleGroup gender;

    @FXML
    private CheckBox termsAgreementCheckBox;

    @FXML
    private ChoiceBox<CivilStatus> civilStatusChoiceBox;

    @FXML
    private Slider ageSlider;

    @FXML
    private RadioButton femaleRadio;

    @FXML
    void retrieveAndShowAction(ActionEvent event) {
        var name = nameTextField.getText();
        var age = ageSlider.getValue();
        var gender = femaleRadio.isSelected() ? "Female" : "Male";
        var civilStatus = civilStatusChoiceBox.getSelectionModel().getSelectedItem();
        var acceptedTerms = termsAgreementCheckBox.isSelected();

        var message = String.format("Nome: %s\nIdade: %1.0f\nSexo: %s\nEstado civil: %s\nAceitou os termos: %s",
                name, age, gender, civilStatus, acceptedTerms);

        showData(message);
    }

    private void showData(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Dados obtidos");
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setUpCiviStatusChoiceBox();

        termsAgreementCheckBox.selectedProperty().addListener((observable, oldValue, newValue) ->
                System.out.println("Aceitou os termos? " + newValue));

        femaleRadio.selectedProperty().addListener((observable, oldValue, newValue) ->
                System.out.println("Feminino? " + newValue));

        ageSlider.valueProperty().addListener((observable, oldValue, newValue) ->
                System.out.println("New age:" + newValue));

        ageSlider.setOnMouseReleased(event -> {
            System.out.println(ageSlider.getValue());
        });
    }

    private void setUpCiviStatusChoiceBox() {
        civilStatusChoiceBox.setItems(FXCollections.observableArrayList(CivilStatus.values()));
        civilStatusChoiceBox.setValue(CivilStatus.SINGLE);

        civilStatusChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("Novo item selecionado: " + newValue);
        });

        civilStatusChoiceBox.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("Novo indice selecionado: " + newValue);
        });
    }

    @FXML
    void onKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) // crie sua acao
            System.out.println("Enter pressed");
    }
}
