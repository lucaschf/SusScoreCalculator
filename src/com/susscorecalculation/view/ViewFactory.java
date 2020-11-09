package com.susscorecalculation.view;

import com.susscorecalculation.SusScoreManager;
import com.susscorecalculation.controller.BaseController;
import com.susscorecalculation.controller.MainWindowController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewFactory {
    private final SusScoreManager scoreManager;

    public ViewFactory(SusScoreManager scoreManager) {
        this.scoreManager = scoreManager;
    }

    public void showMainWindow() {
        BaseController controller = new MainWindowController(this, scoreManager, "MainWindow.fxml");
        initializeStage(controller);
    }

    private void initializeStage(BaseController controller) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(controller.getFxmlName()));
        fxmlLoader.setController(controller);
        Parent parent;

        try {
            parent = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        Scene scene = new Scene(parent);
        Stage stage = new Stage();

        stage.setTitle(controller.getTitle());

        stage.setScene(scene);
        stage.show();
    }

    public void closeStage(Stage stageToClose) {
        stageToClose.close();
    }
}
