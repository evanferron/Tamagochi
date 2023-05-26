package com.ynov.tagmagochi;

public class Adult extends Tamagochi {

    public Adult() {
        lifePart = "Adult";
    }

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

}
