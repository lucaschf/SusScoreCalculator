package com.susscorecalculation.model;

public class Answers {
    private final int firstAnswer;
    private final int secondAnswer;
    private final int thirdAnswer;
    private final int fourthAnswer;
    private final int fifthAnswer;
    private final int sixthAnswer;
    private final int seventhAnswer;
    private final int octaveAnswer;
    private final int ninthAnswer;
    private final int tenthAnswer;

    public Answers(int firstAnswer, int secondAnswer, int thirdAnswer, int fourthAnswer, int fifthAnswer, int sixthAnswer,
                   int seventhAnswer, int octaveAnswer, int ninthAnswer, int tenthAnswer) {
        this.firstAnswer = firstAnswer;
        this.secondAnswer = secondAnswer;
        this.thirdAnswer = thirdAnswer;
        this.fourthAnswer = fourthAnswer;
        this.fifthAnswer = fifthAnswer;
        this.sixthAnswer = sixthAnswer;
        this.seventhAnswer = seventhAnswer;
        this.octaveAnswer = octaveAnswer;
        this.ninthAnswer = ninthAnswer;
        this.tenthAnswer = tenthAnswer;
    }

    public int getFirstAnswer() {
        return firstAnswer;
    }

    public int getSecondAnswer() {
        return secondAnswer;
    }

    public int getThirdAnswer() {
        return thirdAnswer;
    }

    public int getFourthAnswer() {
        return fourthAnswer;
    }

    public int getFifthAnswer() {
        return fifthAnswer;
    }

    public int getSixthAnswer() {
        return sixthAnswer;
    }

    public int getSeventhAnswer() {
        return seventhAnswer;
    }

    public int getOctaveAnswer() {
        return octaveAnswer;
    }

    public int getNinthAnswer() {
        return ninthAnswer;
    }

    public int getTenthAnswer() {
        return tenthAnswer;
    }

    public int getOddsSum() {
        return Math.abs((firstAnswer - 1) + (thirdAnswer - 1) + (fifthAnswer - 1) + (seventhAnswer - 1) + (ninthAnswer - 1));
    }

    public int getPairsSum() {
        return Math.abs((5 - secondAnswer) + (5 - fourthAnswer) + (5 - sixthAnswer) + (5 - octaveAnswer) + (5 - tenthAnswer));
    }

    @Override
    public String toString() {
        return String.format("%d, %d, %d, %d, %d, %d, %d, %d, %d, %d", firstAnswer, secondAnswer, thirdAnswer, fourthAnswer,
                fifthAnswer, sixthAnswer, seventhAnswer, octaveAnswer, ninthAnswer, tenthAnswer);
    }
}
