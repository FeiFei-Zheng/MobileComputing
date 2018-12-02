package ca.roumani.rex2;

import java.util.Locale;

/**
 * Created by sophia on 2017-03-05.
 */
public class ScoreModel {
    private double attempts;
    private double successes;
    private long startTime = System.currentTimeMillis(); //current time
    private double avScore;

    public ScoreModel()
    {
        this.attempts = 0;
        this.successes = 0;
        this.avScore = 0;
        getStart();
    }

    public double getAttempts()
    {
        return attempts;
    }

    public double getSuccess()
    {
        return successes;
    }

    public long getStart()
    {
        return startTime;
    }

    public long getElapsedTime()
    {
        long elapsedTime;
        elapsedTime = System.currentTimeMillis() - startTime;
        elapsedTime = elapsedTime / 1000;
        return elapsedTime;
    }

    public void record(boolean success)
    {
        if(success)
        {
            successes++;
        }
        attempts++;
    }

    public double getAverageScore()
    {
        if (attempts == 0)
        {
            return avScore;
        } else {
            avScore = (successes / attempts) * 100;
        }
        return avScore;
    }


    public static void main(String[] args) {
        ScoreModel k = new ScoreModel();
        k.successes = 3;
        k.attempts = 7;
        System.out.println(k.getAverageScore());
    }

}
