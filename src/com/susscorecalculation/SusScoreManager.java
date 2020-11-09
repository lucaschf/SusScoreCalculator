package com.susscorecalculation;

import com.susscorecalculation.exception.MalformedInputException;
import com.susscorecalculation.model.Answers;
import com.susscorecalculation.model.Score;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class SusScoreManager {
    private double userGuess = 0;
    private File susReportFile;
    private double susScore;
    private List<Score> scores;

    public double getUserGuess() {
        return userGuess;
    }

    public void setUserGuess(double userGuess) {
        this.userGuess = userGuess;
    }

    public void setSusReportFile(File sourceFile) {
        this.susReportFile = sourceFile;
    }

    public File getSusReportFile() {
        return susReportFile;
    }

    public String getFileName() {
        if (susReportFile == null)
            return "Nenhum arquivo selecionado...";

        return susReportFile.getName();
    }

    public void readCsv() throws MalformedInputException {
        scores = new ArrayList<>();
        BufferedReader bufferedReader;

        try {
            bufferedReader = new BufferedReader(new FileReader(susReportFile));

            String line;
            int current = 0;

            while ((line = bufferedReader.readLine()) != null) {
                try {
                    scores.add(new Score(extractAnswer(line)));
                } catch (MalformedInputException ex) {
                    if (current != 0)
                        throw ex;
                }

                current++;
            }

            susScore = scores.stream().collect(Collectors.averagingDouble(Score::getSusScore));
        } catch (IOException ex) {
            susScore = 0;
            Logger.getLogger(SusScoreManager.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }

    private Answers extractAnswer(String line) throws MalformedInputException {
        final String delimiter = ",";
        var columns = line.replace('"', ' ').trim().split(delimiter);

        try {
            return new Answers(
                    Integer.parseInt(columns[1].trim()),
                    Integer.parseInt(columns[2].trim()),
                    Integer.parseInt(columns[3].trim()),
                    Integer.parseInt(columns[4].trim()),
                    Integer.parseInt(columns[5].trim()),
                    Integer.parseInt(columns[6].trim()),
                    Integer.parseInt(columns[7].trim()),
                    Integer.parseInt(columns[8].trim()),
                    Integer.parseInt(columns[9].trim()),
                    Integer.parseInt(columns[10].trim())
            );
        } catch (NumberFormatException | IndexOutOfBoundsException ex) {
            throw new MalformedInputException("Wrong columns value format");
        }
    }

    public List<Score> getScores() {
        return scores;
    }

    public double getSusScore() {
        return susScore;
    }

    public double getProximity() {
        var greater = Math.max(userGuess, susScore);
        var smaller = Math.min(susScore, userGuess);

        var proximity = 100 - (((greater - smaller) / smaller) * 100);
        System.out.println(proximity);
        return proximity;
    }
}
