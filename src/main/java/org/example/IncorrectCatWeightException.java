package org.example;

public class IncorrectCatWeightException extends Exception {
    private int weight;

    public IncorrectCatWeightException(String message, int weight) {
        super(message);
        setWeight(weight);
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
