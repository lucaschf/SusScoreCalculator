package com.susscorecalculation.controller;

import com.susscorecalculation.SusScoreManager;
import com.susscorecalculation.view.ViewFactory;

public abstract class BaseController {
    private final String fxmlName;
    protected SusScoreManager scoreManager;
    protected ViewFactory viewFactory;

    public BaseController(ViewFactory viewFactory, SusScoreManager scoreManager, String fxmlName) {
        this.fxmlName = fxmlName;
        this.scoreManager = scoreManager;
        this.viewFactory = viewFactory;
    }

    public String getFxmlName() {
        return fxmlName;
    }

    public abstract String getTitle();
}
