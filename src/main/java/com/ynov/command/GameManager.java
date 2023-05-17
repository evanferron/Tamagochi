package com.ynov.command;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.ynov.tagmagochi.Adult;
import com.ynov.tagmagochi.Baby;
import com.ynov.tagmagochi.Egg;
import com.ynov.tagmagochi.Old;
import com.ynov.tagmagochi.Tamagochi;

public class GameManager {
    private static TimeUnit unitOfTime = TimeUnit.MINUTES;
    private static Tamagochi tamagochi = new Egg();

    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        while (!tamagochi.tamagochiIsDead()) {
            executorService.scheduleAtFixedRate(() -> {
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
            }, 0, 1, unitOfTime);
        }
    }
}
