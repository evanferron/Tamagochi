package com.ynov.tagmagochi;

public abstract class Tamagochi {
    protected Integer happiness = 15;
    protected Integer age = 0; // in day (time unit)
    protected Boolean isSick = false;
    protected Boolean isDirty = false;
    protected Integer hunger = 0;
    public String lifePart;
    protected Integer numberOfGameRoundToday = 0;
    protected Boolean isDead = false;

    public abstract boolean setAge(); // return true if tamagochi evolve

    protected void changeHappiness(int change) {
        happiness += change;
        if (happiness <= 0) {
            isDead = true;
        }
        if (happiness > 50) {
            happiness = 50;
        }
    }

    public Integer getHappiness() {
        return happiness;
    }

    public Integer getAge() {
        return age;
    }

    public Boolean getIsDirty() {
        return isDirty;
    }

    public Integer getHunger() {
        return hunger;
    }

    public void clean() {
        if (isDirty) {
            isDirty = false;
        } else {
            System.out.println("Tamagochi is already clean");
        }
    }

    public void play() {
        if (numberOfGameRoundToday != 3) {
            numberOfGameRoundToday++;
            changeHappiness(3);
        } else {
            System.out.println("Tamagochi has had enough playing today");
        }
    }

    public void feed() {
        if (hunger > 0) {
            hunger = 0;
            System.out.println("You feed him !!");
        } else {
            System.out.println("He is not hungry ...");
        }
    }

    public void heal() {
        isSick = true;
    }

    public boolean isTamagochiDead() {
        return isDead;
    }

    public void printStat() {
        System.out.println("Age :" + age + " (" + lifePart + ")\nHunger : " + hunger + "\nhapinness : " + happiness);
        if (isDirty) {
            System.out.println("Tamagochi nedd a cleen up !");
        }
    }
}
