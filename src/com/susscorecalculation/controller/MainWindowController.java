package com.susscorecalculation.controller;

import com.susscorecalculation.Constants;
import com.susscorecalculation.SusScoreManager;
import com.susscorecalculation.exception.MalformedInputException;
import com.susscorecalculation.model.Grade;
import com.susscorecalculation.model.ScoreTableModel;
import com.susscorecalculation.view.ViewFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController extends BaseController implements Initializable {

    @FXML
    private TableView<ScoreTableModel> evaluationResultTableView;

    @FXML
    private TableColumn<ScoreTableModel, Integer> pairsCol;

    @FXML
    private TableColumn<ScoreTableModel, Integer> oddCol;

    @FXML
    private TableColumn<ScoreTableModel, Double> susScoreCol;

    @FXML
    private TableColumn<ScoreTableModel, Grade> gradeCol;

    @FXML
    private Label averageScoreLabel;

    @FXML
    private Label guessProximityLabel;

    @FXML
    private Label csvNameLabel;

    @FXML
    private Label messageLabel;

    @FXML
    private Slider scoreSlider;

    @FXML
    private Label userGuessLabel;

    @FXML
    private Label userGuessLabel2;

    @FXML
    private Label finalGradeLabel;

    private final ObservableList<ScoreTableModel> scores;

    public MainWindowController(ViewFactory viewFactory, SusScoreManager scoreManager, String fxmlName) {
        super(viewFactory, scoreManager, fxmlName);
        scores = FXCollections.observableArrayList();
    }

    @Override
    public String getTitle() {
        return Constants.SUS_SCORE_CALCULATION;
    }

    @FXML
    void readCsvAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        var stage = (Stage) averageScoreLabel.getScene().getWindow();

        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        scoreManager.setSusReportFile(fileChooser.showOpenDialog(stage));

        onFileChosen();
    }

    private void onFileChosen() {
        resetFields();

        if (scoreManager.getSusReportFile() == null) {
            return;
        }

        try {
            scoreManager.readCsv();
            scores.clear();
            scoreManager.getScores().forEach(it -> scores.add(new ScoreTableModel(it)));
            averageScoreLabel.setText(String.format("%s: %1.2f", Constants.AVERAGE_SCORE, scoreManager.getSusScore()));
            finalGradeLabel.setText(String.format("%s: %s", Constants.FINAL_GRADE, Grade.from(scoreManager.getSusScore())));

            guessProximityLabel.setText(String.format("%s: %1.2f%%", Constants.ANSWER_PROXIMITY,
                    scoreManager.getProximity()));
        } catch (MalformedInputException e) {
            e.printStackTrace();
        }
    }

    private void resetFields() {
        csvNameLabel.setText(scoreManager.getFileName());
        averageScoreLabel.setText(String.format("%s: -", Constants.AVERAGE_SCORE));
        finalGradeLabel.setText(String.format("%s: -", Constants.FINAL_GRADE));
        guessProximityLabel.setText(String.format("%s: -", Constants.ANSWER_PROXIMITY));

        scores.clear();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupFinalScoreTableView();
        setUpScoreSlider();
    }

    private void setUpScoreSlider() {
        scoreSlider.setMin(0);
        scoreSlider.setMax(100);

        scoreSlider.setMajorTickUnit(1);
        scoreSlider.setSnapToTicks(true);
        scoreSlider.setShowTickMarks(true);
        scoreSlider.setShowTickLabels(true);

        scoreSlider.valueProperty().addListener((observableValue, oldValue, newValue) -> {
            String guess = String.format("%s: %1.2f", Constants.STIPULATED_GRADE, newValue.doubleValue());
            scoreManager.setUserGuess(newValue.doubleValue());
            userGuessLabel.setText(guess);
            userGuessLabel2.setText(guess);
        });

        scoreSlider.setValue(0);
    }

    private void setupFinalScoreTableView() {
        pairsCol.setCellValueFactory(new PropertyValueFactory<>("pairs"));
        oddCol.setCellValueFactory(new PropertyValueFactory<>("odd"));
        susScoreCol.setCellValueFactory(new PropertyValueFactory<>("score"));
        gradeCol.setCellValueFactory(new PropertyValueFactory<>("grade"));

        evaluationResultTableView.setItems(scores);
    }
}