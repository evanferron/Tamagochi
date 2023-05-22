package com.ynov.tagmagochi;

public class Egg extends Tamagochi {
    public String lifePart = "Egg";

    @Override
    public boolean setAge() {
        age++;
        hunger+=5;
        return true;
    }
}
