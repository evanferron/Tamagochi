package com.ynov.command;

import com.ynov.tagmagochi.Adult;
import com.ynov.tagmagochi.Baby;
import com.ynov.tagmagochi.Egg;
import com.ynov.tagmagochi.Old;
import com.ynov.tagmagochi.Tamagochi;

public class GameManager {
    private static Integer unitOfTime;
    private Tamagochi tamagochi;

    public GameManager() {
        tamagochi = new Egg();
        unitOfTime = 1000 * 2;
        game(null);
    }

    public void game(String[] args) {
        System.out.println("game start !!");
        Thread lifeCycle = new Thread(() -> {
            try {
                System.out.println(tamagochi.lifePart);
                while (!tamagochi.isTamagochiDead()) {
                    System.out.println("test timing");
                    Thread.sleep(unitOfTime);
                    boolean needToGrowUp = tamagochi.setAge();
                    if (needToGrowUp) {
                        if (tamagochi.lifePart.equals("Egg")) {
                            tamagochi = new Baby();
                        } else if (tamagochi.lifePart.equals("Baby")) {
                            tamagochi = new Adult();
                        } else {
                            tamagochi = new Old();
                        }
                        ;
                    }
                }
                System.out.println("your tamagochi is dead");
            } catch (Exception e) {
                System.err.println(e);
            }
        });
        lifeCycle.start();
    }
}
