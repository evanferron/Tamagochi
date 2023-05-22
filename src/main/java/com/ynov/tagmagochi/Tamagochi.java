package com.ynov.tagmagochi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
            System.out.println("tamagochi has had enough playing today");
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

    public void heal(){
        isSick = true;
    }

    public boolean tamagochiIsDead() {
        return isDead;
    }
}
