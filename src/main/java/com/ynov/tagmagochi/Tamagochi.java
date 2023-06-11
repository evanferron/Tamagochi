package com.ynov.tagmagochi;

import java.io.Serializable;

public abstract class Tamagochi implements Serializable {
    protected Integer happiness = 15;
    protected Integer age = 0; // in day (time unit)
    protected Boolean isSick = false;
    protected Boolean isDirty = false;
    protected Integer hunger = 0;
    public String lifePart;
    protected Integer numberOfGameRoundToday = 0;
    public transient Boolean isDead = false;
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
            isDirty = true;
            System.out.println("You feed him !!");
        } else {
            System.out.println("He is not hungry ...");
        }
    }

    public void heal() {
        isSick = false;
    }

    public boolean isTamagochiDead() {
        return isDead;
    }

    public String getStat() {
        String result = ("Age: " + age + " : " + lifePart + "\n" + "Hunger: " + hunger + "\n" + "Happiness: "
                + happiness
                + "\n"
                + "Sick: "
                + isSick
                + "\n" + "Dirty: " + isDirty + "\n");
        if (isDirty) {
            result += "Tamagochi nedd a cleen up !";
        }
        return result;
    }
}
