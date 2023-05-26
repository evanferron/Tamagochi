package com.ynov.tagmagochi;

public class Old extends Tamagochi {
    public String lifePart = "old tamagochi";

    @Override
    public boolean setAge() {
        numberOfGameRoundToday = 0;
        age++;
        changeHappiness(-hunger);
        hunger += 5;
        if (happiness == 0 || age == 26 || isSick) {
            isDead = true;
        }
        if (Math.random() < 0.33) {
            isSick = true;
        }
        return false;
    }
    public String displayAgeTamagochi() {
        ageInfo = "Age: 👶";
        return ageInfo;
    }
    public String displayStateTamagochi() {
        ageInfo = "Age: 🥚";
        return ageInfo;
    }
}
