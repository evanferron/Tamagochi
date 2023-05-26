package com.ynov.tagmagochi;

public class Baby extends Tamagochi {

    public Baby() {
        lifePart = "Baby";
        age = 1;
        hunger = 5;
    }

    @Override
    public boolean setAge() {
        numberOfGameRoundToday = 0;
        age++;
        if (hunger != 0) {
            isDead = true;
        }
        hunger += 5;
        if (age == 6) {
            if (happiness >= 40) {
                return true;
            } else {
                System.out.println("your tamagochi is not enough happy ...");
                isDead = true;
            }
        }
        return false;
    }

    public String displayAgeTamagochi() {
        ageInfo = "Age: 👴";
        return ageInfo;
    }
    public String displayStateTamagochi() {
        ageInfo = "Age: 🥚";
        return ageInfo;
    }
}
