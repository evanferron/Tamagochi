package com.ynov;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tamagochi {
    private Integer happiness = 15;
    private Integer age = 0; // in day (time unit)
    private Boolean isSick = false;
    private Boolean isDirty = false;
    private Integer hunger = 0;
    private List<String> stages = new ArrayList<String>();
    private Integer currentStage = 0;
    private Boolean isDead = false;
    private Integer numberOfGameRoundToday = 0;

    public Tamagochi() {
        stages.addAll(Arrays.asList("Egg", "Baby", "Adulte", "old"));
    }

    private void setStage() {
        currentStage++;
        if (currentStage == 4) {
            isDead = true;
        }
    }

    private void setAge() {
        age++;
    }

    private void changeHappiness(int change) {
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
        }else{
            System.out.println("tamagochi has had enough playing today");
        }
    }
    
}
