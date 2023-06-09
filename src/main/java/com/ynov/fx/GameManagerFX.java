package com.ynov.fx;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import com.ynov.tagmagochi.Adult;
import com.ynov.tagmagochi.Baby;
import com.ynov.tagmagochi.Egg;
import com.ynov.tagmagochi.Old;
import com.ynov.tagmagochi.Tamagochi;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
    private Scene mainScene;
    private Scene playScene = play();
    private Scene cleanScene = clean();
    private Scene feedScene = feed();
    private Scene healScene = null;
    
    Stage mainStage = null;

    public GameManagerFX(Stage stage) {
        tamagochi = new Egg();
        this.stage = stage;
        mainScene = createMainScene();
        stage.setScene(mainScene);
        stage.show();
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
        Thread lifeCycle = new Thread(() -> {
            try {
                while (!tamagochi.isTamagochiDead()) {
                    saveTamagochi();
                    Thread.sleep(unitOfTime);
                    boolean needToGrowUp = tamagochi.setAge();
                    if (needToGrowUp) {
                        if (tamagochi.lifePart.equals("Egg")) {
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

    private Scene createMainScene() {
        VBox stats = new VBox(new Label("les stats"));
        stats.setId("stats-tamagochi");
        HBox hbox1 = new HBox(stats);
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
            this.stage.setScene(feedScene);
            tamagochi.feed();
        });
        buttonFeed.getStyleClass().addAll("button-make-action");
        Button buttonPlay = new Button("Play");
        buttonPlay.setOnMouseClicked((e) -> {
            // TO DO launch game of play
            this.stage.setScene(playScene);
            tamagochi.play();
        });
        buttonPlay.getStyleClass().addAll("button-make-action");
        Button buttonClean = new Button("Clean");
        buttonClean.setOnMouseClicked((e) -> {
            // TO DO launch game of Clean
            this.stage.setScene(cleanScene);
            tamagochi.clean();
        });
        buttonClean.getStyleClass().addAll("button-make-action");
        Button buttonHeal = new Button("Heal");
        buttonHeal.setOnMouseClicked((e) -> {
            // TO DO launch game of Heal
            // this.stage.setScene(healScene);
            tamagochi.heal();
        });
        buttonHeal.getStyleClass().addAll("button-make-action");
        HBox hbox3 = new HBox(buttonExit, buttonFeed, buttonPlay, buttonClean, buttonHeal);
        hbox3.setId("button-container");
        VBox vbox = new VBox(hbox1, hbox2, hbox3);
        vbox.setId("main-container");
        Scene scene = new Scene(vbox, 1920, 950);
        scene.getStylesheets().add("/mainScene.css");
        return scene;
    }

    private Scene play() {
        Image image = new Image(getClass().getResourceAsStream("/assets/egg.png"));
        ImageView guy = new ImageView(image);
        guy.getStyleClass().add("guy");
        Label percentage = new Label("0");
        percentage.setStyle("-fx-font-size: 50px; -fx-font-weight: bold; -fx-text-fill: #BD9302; -fx-font-family: Arial;");
        Button leftButton = new Button();
        leftButton.getStyleClass().addAll("button-triangle-left", "button");
        Button middleButton = new Button();
        middleButton.getStyleClass().addAll("button-circular-middle", "button");
        Button rightButton = new Button();
        rightButton.getStyleClass().addAll("button-cube-right", "button");

        leftButton.setOnMouseClicked(e -> {
            percentage.setText(String.valueOf(Integer.parseInt(percentage.getText()) + 5));
            if (Integer.parseInt(percentage.getText()) >= 100) {
                tamagochi.play();
                this.stage.setScene(mainScene);
                playScene = play();
                this.stage.setTitle("Tamagochi");
            }
        });

        middleButton.setOnMouseClicked(e -> {
            percentage.setText(String.valueOf(Integer.parseInt(percentage.getText()) + 10));
            if (Integer.parseInt(percentage.getText()) >= 100) {
                tamagochi.play();
                this.stage.setScene(mainScene);
                playScene = play();
                this.stage.setTitle("Tamagochi");
            }
        });

        rightButton.setOnMouseClicked(e -> {
            percentage.setText(String.valueOf(Integer.parseInt(percentage.getText()) + 5));
            if (Integer.parseInt(percentage.getText()) >= 100) {
                tamagochi.play();
                this.stage.setScene(mainScene);
                playScene = play();
                this.stage.setTitle("Tamagochi");
            }
        });

        HBox buttons = new HBox(leftButton, middleButton, rightButton);
        buttons.getStyleClass().add("buttons");
        VBox infoBox = new VBox(percentage, buttons);
        infoBox.getStyleClass().add("info-box");
        VBox play = new VBox(guy, infoBox);
        play.getStyleClass().add("play");
        Scene scene = new Scene(play, 1920, 950);
        scene.getStylesheets().add(getClass().getResource("/app.css").toExternalForm());
        return scene;
    }

    private Scene clean() {
        Button cleanButtonGuy = new Button("clean");
        cleanButtonGuy.getStyleClass().addAll("button-clean-guy");
        cleanButtonGuy.setOnMouseClicked(e -> {
            tamagochi.clean();
            this.stage.setScene(mainScene);
            cleanScene = clean();
            this.stage.setTitle("Tamagochi");;
        });
        Button guy = new Button("IMG Guy");
        guy.getStyleClass().addAll("guy");
        VBox clean = new VBox(cleanButtonGuy, guy);
        clean.getStyleClass().add("clean");
        Scene scene = new Scene(clean, 1920, 950);
        scene.getStylesheets().add(getClass().getResource("/app.css").toExternalForm());
        return scene;
    }

    private Scene feed() {
        Button cleanButtonGuy = new Button("feed");
        cleanButtonGuy.getStyleClass().addAll("button-feed-guy");
        cleanButtonGuy.setOnMouseClicked(e -> {
            tamagochi.clean();
            this.stage.setScene(mainScene);
            feedScene = feed();
            this.stage.setTitle("Tamagochi");;
        });
        Button guy = new Button("IMG Guy");
        guy.getStyleClass().addAll("guy");
        VBox clean = new VBox(cleanButtonGuy, guy);
        clean.getStyleClass().add("feed");
        Scene scene = new Scene(clean, 1920, 950);
        scene.getStylesheets().add(getClass().getResource("/app.css").toExternalForm());
        return scene;
    }
}