package com.susscorecalculation.model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

public class ScoreTableModel {
    private final SimpleIntegerProperty odd;
    private final SimpleIntegerProperty pairs;
    private final SimpleDoubleProperty score;
    private final SimpleObjectProperty<Grade> grade;

    public ScoreTableModel(Score score) {
        this(score.getOdd(), score.getPairs(), score.getSusScore(), score.getGrade());
    }

    public ScoreTableModel(int odd, int pairs, double score, Grade grade) {
        this.odd = new SimpleIntegerProperty(odd);
        this.pairs = new SimpleIntegerProperty(pairs);
        this.score = new SimpleDoubleProperty(score);
        this.grade = new SimpleObjectProperty<>(grade);
    }

    public int getOdd() {
        return this.odd.get();
    }

    public int getPairs() {
        return this.pairs.get();
    }

    public double getScore() {
        return this.score.get();
    }

    public Grade getGrade() {
        return this.grade.get();
    }
}