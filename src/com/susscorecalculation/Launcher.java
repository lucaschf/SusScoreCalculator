package com.susscorecalculation;

import com.susscorecalculation.view.ViewFactory;
import javafx.application.Application;
import javafx.stage.Stage;

public class Launcher extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        ViewFactory viewFactory = new ViewFactory(new SusScoreManager());
        viewFactory.showMainWindow();
    }
}
