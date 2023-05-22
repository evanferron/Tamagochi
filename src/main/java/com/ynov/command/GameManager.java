package com.ynov.command;


import com.ynov.tagmagochi.Adult;
import com.ynov.tagmagochi.Baby;
import com.ynov.tagmagochi.Egg;
import com.ynov.tagmagochi.Old;
import com.ynov.tagmagochi.Tamagochi;

public class GameManager {
    private static Integer unitOfTime = 1000 * 30;
    private static Tamagochi tamagochi = new Egg();

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                while (!tamagochi.isTamagochiDead()) {
                    Thread.sleep(unitOfTime);
                    boolean needToGrowUp = tamagochi.setAge();
                    if (needToGrowUp) {
                        switch (tamagochi.lifePart) {
                            case "Egg":
                                tamagochi = new Baby();
                            case "Baby":
                                tamagochi = new Adult();
                            default:
                                tamagochi = new Old();
                        }
                    }
                }
            } catch (Exception e) {
                System.err.println(e);
            }
        }).start();
        while (!tamagochi.isTamagochiDead()) {

        }
    }
}
