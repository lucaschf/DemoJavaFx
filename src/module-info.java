module Demo {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;

    opens com.example;
    opens com.example.controller;
    opens com.example.model;
    opens com.example.view;
}