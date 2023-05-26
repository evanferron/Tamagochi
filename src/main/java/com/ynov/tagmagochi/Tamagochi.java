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
    protected String ageInfo = "";
    protected String stateInfo = "";

    public abstract boolean setAge(); // return true if tamagochi evolve

    public String displayAgeTamagochi() {

        if(!isDead){
            if(age <= 1){
                ageInfo = "Age: 🥚";
    
            } else if(age >= 2 && age <= 5){
                ageInfo = "Age: 👶";
    
            } else if(age >= 6 && age <= 21){
                ageInfo = "Age: 🧑";
            
            } else if(age >= 22){
                ageInfo = "Age: 👴";
            
            } else {
                ageInfo = "ERROR: NO RECOGNISED AGE !!! ";
            }
        }
        
        return ageInfo;
    }

    public String displayStateTamagochi() {
        if(state == "HAPPY"){
            stateInfo = "State: 🙂";

        } else if(state == "NEUTRE"){
            stateInfo = "State: 😐";

        } else if(state == "UNHAPPY"){
            stateInfo = "State: 😞";
        
        } else if(state == "HUNGRY"){
            stateInfo = "State: 🤤";

        } else if(state == "SICK"){
            stateInfo = "State: 🤒";
            
        } else if(state == "DEAD"){
            stateInfo = "State: 😵";

        } else {
            stateInfo = "ERROR: NO RECOGNISED STATE !!! ";
        }
        
        return stateInfo;
    }

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

    public void heal(){
        isSick = false;
    }

    public boolean isTamagochiDead() {
        return isDead;
    }

    public void printStat() {
        System.out.println("\nAge :" + age + " (" + lifePart + ")\nHunger : " + hunger + "\nhapinness : " + happiness);
        if (isDirty) {
            System.out.println("Tamagochi nedd a cleen up !");
        }
    }
}
