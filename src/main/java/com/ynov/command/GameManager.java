package com.ynov.command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
        unitOfTime = 1000 * 5;
        game();
    }

    public String menu() {
        tamagochi.printStat();
        printMenu();
        /* Prompt */
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader buffer = new BufferedReader(reader);
        try {
            String answer = buffer.readLine();
            if (answer.length() == 0 || !answer.matches("[0-4]")) { // if answer is empty or not a number between 0 and
                                                                    // 4
                System.err.println("Veuillez saisir une réponse correcte .");
                return menu();
            }
            return answer;
        } catch (IOException e) {
            System.err.println("Quelque chose s'est mal passé, recommencez :");
            return menu();
        }
    }

    public void game() {
        clearConsole();
        System.out.println("game start !!");
        if (tamagochi.lifePart == "Egg") {
            System.out.println("For the moment your tamagochi is an egg, please wait " + unitOfTime / 1000 + " s");
        }
        Thread gameThread = new Thread(() -> {
            while (!tamagochi.isTamagochiDead()) {
                clearConsole();
                makeAction(Integer.parseInt(menu()));
            }
        });
        Thread lifeCycle = new Thread(() -> {
            try {
                while (!tamagochi.isTamagochiDead()) {
                    Thread.sleep(unitOfTime);
                    boolean needToGrowUp = tamagochi.setAge();
                    clearConsole();
                    tamagochi.printStat();
                    printMenu();
                    if (needToGrowUp) {
                        if (tamagochi.lifePart.equals("Egg")) {
                            gameThread.start();
                            clearConsole();
                            tamagochi = new Baby();
                        } else if (tamagochi.lifePart.equals("Baby")) {
                            tamagochi = new Adult(tamagochi.getHappiness(), tamagochi.getAge(), tamagochi.getIsDirty(),
                                    tamagochi.getHunger());
                        } else {
                            tamagochi = new Old(tamagochi.getHappiness(), tamagochi.getAge(), tamagochi.getIsDirty(),
                                    tamagochi.getHunger());
                        }
                    }
                }
                System.out.println("\nyour tamagochi is dead");
            } catch (Exception e) {
                System.err.println(e);
            }
        });
        lifeCycle.start();
    }

    private void makeAction(int choice) {
        if (!tamagochi.isTamagochiDead()) {
            if (choice == 0) {
                System.exit(0);
            } else if (choice == 1) {
                tamagochi.feed();
            } else if (choice == 2) {
                tamagochi.play();
            } else if (choice == 3) {
                tamagochi.clean();
            } else {
                tamagochi.heal();
            }
        }
    }

    private void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private void printMenu() {
        System.out.println("1 - Feed");
        System.out.println("2 - Play");
        System.out.println("3 - Clean");
        if (tamagochi.lifePart.equals("Old")) {
            System.out.println("4 - Heal");
        }
        System.out.println("0 - Exit");
        System.out.print("Choose an action : ");
    }
}
