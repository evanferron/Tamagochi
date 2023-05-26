package com.ynov.tagmagochi;

public class Egg extends Tamagochi {

    public Egg() {
        lifePart = "Egg";
    }

    @Override
    public boolean setAge() {
        age++;
        hunger += 5;
        return true;
    }

    public String displayAgeTamagochi() {
        ageInfo = "Age: 🥚";
        return ageInfo;
    }
}
