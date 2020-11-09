package com.susscorecalculation.model;

public class Score {
    private final Answers answers;

    private double susScore;
    private final Grade grade;

    public Score(Answers answers) {
        this.answers = answers;
        calculateSusScore();
        grade = Grade.from(susScore);
    }

    public int getPairs() {
        return answers.getPairsSum();
    }

    public int getOdd() {
        return answers.getOddsSum();
    }

    public double getSusScore() {
        return susScore;
    }

    public Grade getGrade() {
        return grade;
    }

    private void calculateSusScore() {
        susScore = (answers.getPairsSum() + answers.getOddsSum()) * 2.5;
    }

    @Override
    public String toString() {
        return String.format("%s, %s", susScore, grade);
    }
}
