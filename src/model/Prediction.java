package model;

/**
 * Created by fauno on 2/17/16.
 */
public class Prediction {

    private String correctValue;
    private String precitedValue;
    private Double votesPercentage;

    public Prediction(String correctValue, String precitedValue) {
        this.correctValue = correctValue;
        this.precitedValue = precitedValue;
    }

    public Prediction(String correctValue, String precitedValue, Double votesPercentage) {
        this.correctValue = correctValue;
        this.precitedValue = precitedValue;
        this.votesPercentage = votesPercentage;
    }

    public String getCorrectValue() {
        return correctValue;
    }

    public void setCorrectValue(String correctValue) {
        this.correctValue = correctValue;
    }

    public String getPrecitedValue() {
        return precitedValue;
    }

    public void setPrecitedValue(String precitedValue) {
        this.precitedValue = precitedValue;
    }

    public boolean isCorrect() {
        return this.correctValue.equals(this.precitedValue);
    }

    public Double getVotesPercentage() {
        return votesPercentage;
    }

    public void setVotesPercentage(Double votesPercentage) {
        this.votesPercentage = votesPercentage;
    }

    public String toString() {
        return "{True value: " + this.correctValue
                + ", Predicted value:  " + this.precitedValue
                + "(" + this.votesPercentage +"%)"
                + ", Result: " + (this.isCorrect() ? "CORRECT}" : "FALSE}");
    }
}
