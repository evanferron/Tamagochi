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

    public void heal(){
        isSick = false;
    }

    public boolean isTamagochiDead() {
        return isDead;
    }

    public void printStat() {
        System.out.println("========================= CHARACTERISTICS =========================");
        System.out.println(setColorText());
        //System.out.println("Age: " + age + "\n" + "Hunger: " + hunger + "\n" + "Happiness: " + happiness + "\n" + "Sick: " + isSick + "\n" + "Dirty: " + isDirty);
        System.out.println("");
        if (isDirty) {
            System.out.println("Tamagochi nedd a cleen up !");
        }
    }

    protected String setColorText(){
        String caracteristics = "Age: " + age + "\n";

        String RED_COLOR = "\u001B[31m";
        String GREEN_COLOR = "\u001B[32m";
        String YELLOW_COLOR = "\u001B[33m";
        String WHITE_COLOR = "\u001B[37m";

        if(happiness > 40){
            caracteristics += "Happiness: " + GREEN_COLOR + happiness + WHITE_COLOR + "\n ";

        } else if(happiness <= 39 && happiness > 15){
            caracteristics += "Happiness: " + YELLOW_COLOR + happiness + WHITE_COLOR + "\n ";

        } else if(happiness < 15){
            caracteristics += "Happiness: " + RED_COLOR + happiness + WHITE_COLOR + "\n ";
        }

        if(hunger <= 0){
            caracteristics += "Hunger: " + GREEN_COLOR + hunger + WHITE_COLOR + "\n ";

        } else if(hunger >= 1 && hunger < 3){
            caracteristics += "Hunger: " + YELLOW_COLOR + hunger + WHITE_COLOR + "\n ";

        } else if(hunger > 3){
            caracteristics += "Hunger: " + RED_COLOR + hunger + WHITE_COLOR + "\n ";
        }

        if(isSick){
            caracteristics += "Sick: " + GREEN_COLOR + isSick + WHITE_COLOR + "\n ";

        } else if(!isSick){
            caracteristics += "Sick: " + RED_COLOR + isSick + WHITE_COLOR + "\n ";
        }

        if(isDirty){
            caracteristics += "Dirty: " + GREEN_COLOR + isDirty + WHITE_COLOR + "\n ";

        } else if(!isDirty){
            caracteristics += "Dirty: " + RED_COLOR + isDirty + WHITE_COLOR + "\n ";
        }

        return caracteristics;
    }
}
