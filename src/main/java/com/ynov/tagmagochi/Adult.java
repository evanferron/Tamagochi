package com.ynov.tagmagochi;

public class Adult extends Tamagochi {
    public String lifePart = "Adult";

    @Override
    public boolean setAge() {
        numberOfGameRoundToday = 0;
        age++;
        changeHappiness(-hunger);
        hunger += 5;
        if (age == 21) {
            return true;
        }
        return false;
    }

    public String displayAgeTamagochi() {
        ageInfo = "Age: 🧑";
        return ageInfo;
    }
    public String displayStateTamagochi() {
        ageInfo = "Age: 🥚";
        return ageInfo;
    }
}
