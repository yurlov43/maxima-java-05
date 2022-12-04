package org.example;


public class Cat {
    private String name;
    private int weight;
    private boolean isAngry;

    public Cat(String name, int weight, boolean isAngry) throws IncorrectCatWeightException {
        setName(name);
        setWeight(weight);
        setAngry(isAngry);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) throws IncorrectCatWeightException {
        if (weight <= 0) throw new IncorrectCatWeightException("Вес не может быть отрицательным!", weight);
        this.weight = weight;
    }

    public boolean isAngry() {
        return isAngry;
    }

    public void setAngry(boolean angry) {
        isAngry = angry;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                ", isAngry=" + isAngry +
                '}';
    }
}
