module SusScoreCalculation {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
    requires java.logging;

    opens com.susscorecalculation.model;
    opens com.susscorecalculation;
    opens com.susscorecalculation.view;
    opens com.susscorecalculation.controller;
}