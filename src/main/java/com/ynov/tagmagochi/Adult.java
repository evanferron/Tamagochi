package com.ynov.tagmagochi;

public class Adult extends Tamagochi {

    public Adult(Integer happiness, Integer age, Boolean isDirty, Integer hunger) {
        lifePart = "Adult";
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
