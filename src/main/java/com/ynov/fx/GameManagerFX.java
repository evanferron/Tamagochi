package com.ynov.fx;

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

import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class GameManagerFX {
    private static Integer unitOfTime;
    private Tamagochi tamagochi;
    private static final transient Path DB_PATH = Path.of("./src/main/java/com/ynov/data/tamagochi.dat");
    private Stage stage;
    // private Scene mainScene;

    public GameManagerFX(Stage stage) {
        tamagochi = new Egg();
        this.stage = stage;
        unitOfTime = 1000 * 5;
        game();
    }

    public GameManagerFX(Tamagochi tamagochi, Stage stage) {
        this.stage = stage;
        this.tamagochi = tamagochi;
        tamagochi.isDead = false;
        unitOfTime = 1000 * 10;
        game();
    }

    public void game() {
        // clearConsole();
        System.out.println("game start !!");
        // Thread gameThread = new Thread(() -> {
        // while (!tamagochi.isTamagochiDead()) {
        // // clearConsole();
        // // makeAction(Integer.parseInt(menu()));
        // }
        // });
        if (tamagochi.lifePart == "Egg") {
            System.out.println("For the moment your tamagochi is an egg, please wait " + unitOfTime / 1000 + " s");
        }
        Thread lifeCycle = new Thread(() -> {
            try {
                while (!tamagochi.isTamagochiDead()) {
                    saveTamagochi();
                    Thread.sleep(unitOfTime);
                    boolean needToGrowUp = tamagochi.setAge();
                    // clearConsole();
                    tamagochi.printStat();
                    // printMenu();
                    if (needToGrowUp) {
                        if (tamagochi.lifePart.equals("Egg")) {
                            // clearConsole();
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
                // endMenu();
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