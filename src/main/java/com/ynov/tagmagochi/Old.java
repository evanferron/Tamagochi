package com.ynov.tagmagochi;

public class Old extends Tamagochi {

    public Old(Integer happiness, Integer age, Boolean isDirty, Integer hunger) {
        lifePart = "old tamagochi";
        this.happiness = happiness;
        this.age = age;
        this.isDirty = isDirty;
        this.hunger = hunger;
    }

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

}
