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

    protected String displayAge = "";
    protected String displayHunger = "";
    protected String displayHappiness = "";
    protected String displayHealth = "";
    protected String displayDirty = "";

    public abstract boolean setAge(); // return true if tamagochi evolve

    public void displayAge() {

        if (!isDead) {
            if (age <= 1) {
                displayAge = "Age: " + age + " 🥚";

            } else if (age >= 2 && age <= 5) {
                displayAge = "Age: " + age + " 👶";

            } else if (age >= 6 && age <= 21) {
                displayAge = "Age: " + age + " 🧑";

            } else if (age >= 22) {
                displayAge = "Age: " + age + " 👴";

            }
        }
    }

    public void displayHunger() {
        if (hunger > 0) {
            displayHunger = "Hunger: " + hunger + " 🤤";

        } else if (hunger == 0) {
            displayHunger = "Hunger: " + hunger + " ❌";

        }
    }

    public void displayHappiness() {
        if (happiness >= 40) {
            displayHappiness = "Happiness: " + happiness + " 🙂";

        } else if (happiness > 15 && happiness < 40) {
            displayHappiness = "Happiness: " + happiness + " 😐";

        } else if (happiness < 15) {
            displayHappiness = "Happiness: " + happiness + " 😞";

        }
    }

    public void displayHealth() {
        if (isSick) {
            displayHealth = "Sick: " + isSick + " 🤒";

        } else if (!isSick) {
            displayHealth = "Sick: " + isSick + " ❌";

        }
    }

    public void displayDirty() {
        if (isDirty) {
            displayDirty = "Dirty: " + isDirty + " 🐷";

        } else if (!isDirty) {
            displayDirty = "Dirty: " + isDirty + " ❌";

        }
    }

    public void displayDeath() {
        if (isDead) {
            System.out.println("Your Tamagotchi is DEAD 😵");
        }
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

    public void heal() {
        isSick = false;
    }

    public boolean isTamagochiDead() {
        return isDead;
    }

    public void printStat() {
        displayAge();
        displayDirty();
        displayHappiness();
        displayHealth();
        displayHunger();
        System.out.println(displayAge + "\n" + displayHunger + "\n" + displayHealth + "\n" + displayDirty + "\n"
                + displayHappiness);
        if (isDirty) {
            System.out.println("Tamagochi nedd a cleen up !");
        }
    }
}
