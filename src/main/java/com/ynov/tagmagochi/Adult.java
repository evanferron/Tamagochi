package com.ynov.tagmagochi;

public class Adult extends Tamagochi {
    public String lifePart = "Adult";

    @Override
    public boolean setAge() {
        age++;
        changeHappiness(-hunger);
        hunger += 5;
        if (age == 21) {
            return true;
        }
        return false;
    }

}
