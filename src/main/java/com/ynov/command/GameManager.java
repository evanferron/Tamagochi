package com.ynov.command;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;

import com.ynov.tagmagochi.Adult;
import com.ynov.tagmagochi.Baby;
import com.ynov.tagmagochi.Egg;
import com.ynov.tagmagochi.Old;
import com.ynov.tagmagochi.Tamagochi;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class GameManager {
    private static Integer unitOfTime;
    private Tamagochi tamagochi;
    private static final transient Path DB_PATH = Path.of("./src/main/java/com/ynov/data/tamagochi.dat");

    public GameManager() {
        tamagochi = new Egg();
        unitOfTime = 1000 * 5;
        game();
    }

    public GameManager(Tamagochi tamagochi) {
        this.tamagochi = tamagochi;
        tamagochi.isDead = false;
        unitOfTime = 1000 * 10;
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

    private void endMenu() {
        System.out.println("\nyour tamagochi is dead");
        System.out.println("1 - retry \n2 - leave");
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader buffer = new BufferedReader(reader);
        try {
            String answer = buffer.readLine();
            if (answer.equals("1")) {
                tamagochi = new Egg();
                game();
            } else if (answer.equals("2")) {

            } else {
                System.err.println("Quelque chose s'est mal passé, recommencez :");
                endMenu();
            }
        } catch (IOException e) {
            System.err.println("Quelque chose s'est mal passé, recommencez :");
            endMenu();
        }
    }

    public void game() {
        // clearConsole();
        System.out.println("game start !!");
        Thread gameThread = new Thread(() -> {
            while (!tamagochi.isTamagochiDead()) {
                clearConsole();
                makeAction(Integer.parseInt(menu()));
            }
        });
        if (tamagochi.lifePart == "Egg") {
            System.out.println("For the moment your tamagochi is an egg, please wait " + unitOfTime / 1000 + " s");
        } else {
            gameThread.start();
        }
        Thread lifeCycle = new Thread(() -> {
            try {
                while (!tamagochi.isTamagochiDead()) {
                    saveTamagochi();
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
                endMenu();
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

    private void saveTamagochi() {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(tamagochi);
            byte[] data = baos.toByteArray();
            Files.write(DB_PATH, data);
            baos.close();
            oos.close();
        } catch (IOException e) {
            System.err.println("La sauvegarde a échoué." + e.getMessage());
        }
    }

    public static Tamagochi loadTamagochi() {
        if (!Files.exists(DB_PATH)) {
            System.err.println("Aucune sauvegarde n'a été trouvée.");
            return new Egg();
        }
        try {
            byte[] data = Files.readAllBytes(DB_PATH);
            ByteArrayInputStream bais = new ByteArrayInputStream(data);
            ObjectInputStream ois = new ObjectInputStream(bais);
            Tamagochi tamagochi = (Tamagochi) ois.readObject();
            bais.close();
            ois.close();
            System.out.println(tamagochi.lifePart);
            return tamagochi;
        } catch (IOException e) {
            System.err.println("Le fichier n'a pas pu etre lu :" + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err
                    .println("La sauvegarde n'est pas compatible avec cette version du programme : " + e.getMessage());
        }
        System.err.println("Lancement d'un nouveau tamagochi.");
        return new Egg();
    }
}
