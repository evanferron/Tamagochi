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
        unitOfTime = 1000 * 2;
        game(null);
    }

    public String menu() {
        System.out.println("1 - Feed");
        System.out.println("2 - Play");
        System.out.println("3 - Clean");
        if (tamagochi.lifePart.equals("Old")) {
            System.out.println("4 - Heal");
        }
        System.out.println("0 - Exit");
        System.out.print("Choose an action : ");
        /*  Prompt  */
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader buffer = new BufferedReader(reader);
        try {
            String answer = buffer.readLine();
            if(answer.length() == 0 || !answer.matches("[0-4]")){ // if answer is empty or not a number between 0 and 4
                System.err.println("Veuillez saisir une réponse correcte .");
                return menu();
            }
            return answer;
        } catch (IOException e) {
            System.err.println("Quelque chose s'est mal passé, recommencez :");
            return menu();
        }
    }

    public void game(String[] args) {
        System.out.println("game start !!");
        Thread lifeCycle = new Thread(() -> {
            try {
                while (!tamagochi.isTamagochiDead()) {
                    Thread.sleep(unitOfTime);
                    boolean needToGrowUp = tamagochi.setAge();
                    tamagochi.printStat();
                    if (needToGrowUp) {
                        if (tamagochi.lifePart.equals("Egg")) {
                            tamagochi = new Baby();
                        } else if (tamagochi.lifePart.equals("Baby")) {
                            tamagochi = new Adult();
                        } else {
                            tamagochi = new Old();
                        }
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
