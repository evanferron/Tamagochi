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
    private static Integer unitOfTime = 1000 * 30;
    private static Tamagochi tamagochi = new Egg();

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
