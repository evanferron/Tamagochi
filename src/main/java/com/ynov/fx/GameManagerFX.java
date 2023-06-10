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

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.lang.ref.Reference;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class GameManagerFX {
    private static Integer unitOfTime;
    private Tamagochi tamagochi;
    private static final transient Path DB_PATH = Path.of("./src/main/java/com/ynov/data/tamagochi.dat");
    private Stage stage;
    private Scene mainScene;
    private Label stats;

    public GameManagerFX(Stage stage) {
        tamagochi = new Egg();
        this.stage = stage;
        stats = new Label(tamagochi.getStat());
        mainScene = createMainScene();
        stage.setScene(mainScene);
        stage.show();
        unitOfTime = 1000 * 10;
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
        System.out.println("game start !!");
        new Thread(() -> {
            try {
                while (!tamagochi.isTamagochiDead()) {
                    System.out.println(tamagochi.getStat());
                    saveTamagochi();
                    Platform.runLater(() -> {
                        refreshStats();
                    });
                    Thread.sleep(unitOfTime);
                    boolean needToGrowUp = tamagochi.setAge();
                    if (needToGrowUp) {
                        if (tamagochi.lifePart.equals("Egg")) {
                            tamagochi = new Baby();
                        } else if (tamagochi.lifePart.equals("Baby")) {
                            tamagochi = new Adult(tamagochi.getHappiness(), tamagochi.getAge(),
                                    tamagochi.getIsDirty(),
                                    tamagochi.getHunger());
                        } else {
                            tamagochi = new Old(tamagochi.getHappiness(), tamagochi.getAge(),
                                    tamagochi.getIsDirty(),
                                    tamagochi.getHunger());
                        }
                    }
                }
                System.exit(0);
            } catch (Exception e) {
                System.err.println(e);
            }
        }).start();
    }

    private void makeAction(int choice) {
        if (!tamagochi.isTamagochiDead()) {
            refreshStats();
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

    private Scene createMainScene() {
        VBox statsBox = new VBox(stats);
        statsBox.setId("stats-tamagochi");
        HBox hbox1 = new HBox(statsBox);
        hbox1.setId("stats-container");
        HBox hbox2 = new HBox(new Label("image du tamagochi"));
        hbox2.setId("tamagochi-img-container");
        Button buttonExit = new Button("Exit");
        buttonExit.setOnMouseClicked((e) -> {
            System.exit(0);
        });
        buttonExit.getStyleClass().addAll("button-make-action");
        Button buttonFeed = new Button("Feed");
        buttonFeed.setOnMouseClicked((e) -> {
            // TO DO launch game of feed
            makeAction(1);
            tamagochi.feed();
        });
        buttonFeed.getStyleClass().addAll("button-make-action");
        Button buttonPlay = new Button("Play");
        buttonPlay.setOnMouseClicked((e) -> {
            // TO DO launch game of play
            makeAction(2);
            tamagochi.play();
        });
        buttonPlay.getStyleClass().addAll("button-make-action");
        Button buttonClean = new Button("Clean");
        buttonClean.setOnMouseClicked((e) -> {
            // TO DO launch game of Clean
            makeAction(3);
            tamagochi.clean();
        });
        buttonClean.getStyleClass().addAll("button-make-action");
        Button buttonHeal = new Button("Heal");
        buttonHeal.setOnMouseClicked((e) -> {
            // TO DO launch game of Heal
            makeAction(4);
            tamagochi.heal();
        });
        buttonHeal.getStyleClass().addAll("button-make-action");
        HBox hbox3 = new HBox(buttonExit, buttonFeed, buttonPlay, buttonClean, buttonHeal);
        hbox3.setId("button-container");
        VBox vbox = new VBox(hbox1, hbox2, hbox3);
        vbox.setId("main-container");
        Scene scene = new Scene(vbox, 1000, 750);
        scene.getStylesheets().add("/mainScene.css");
        return scene;
    }

    private void refreshStats() {
        stats.setText(tamagochi.getStat());
    }
}
