package com.ynov.tagmagochi;

public class Baby extends Tamagochi {
    public String lifePart = "Baby";

    @Override
    public boolean setAge() {
        numberOfGameRoundToday = 0;
        age++;
        if (hunger != 0) {
            isDead = true;
        }
        hunger+=5;
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

}
