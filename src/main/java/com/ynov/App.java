package com.ynov;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

import com.ynov.command.GameManager;
import com.ynov.fx.GameManagerFX;
import com.ynov.tagmagochi.Tamagochi;

/**
 * JavaFX App
 */
public class App extends Application {

    Scene playScene = null;
    Scene cleanScene = null;
    Scene feedScene = null;
    Scene menuScene = null;
    Stage mainStage = null;

    private Scene play() {
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
            // if (Integer.parseInt(percentage.getText()) >= 100) {
            //     tamagochi.play();
            //     mainStage.setScene(menuScene);
            //     mainStage.setTitle("Tamagochi");
            // }
        });

        middleButton.setOnMouseClicked(e -> {
            percentage.setText(String.valueOf(Integer.parseInt(percentage.getText()) + 10));
            // if (Integer.parseInt(percentage.getText()) >= 100) {
            //     tamagochi.play();
            //     mainStage.setScene(menuScene);
            //     mainStage.setTitle("Tamagochi");
            // }
        });

        rightButton.setOnMouseClicked(e -> {
            percentage.setText(String.valueOf(Integer.parseInt(percentage.getText()) + 5));
            // if (Integer.parseInt(percentage.getText()) >= 100) {
            //     tamagochi.play();
            //     mainStage.setScene(menuScene);
            //     mainStage.setTitle("Tamagochi");
            // }
        });

        HBox buttons = new HBox(leftButton, middleButton, rightButton);
        buttons.getStyleClass().add("buttons");
        VBox infoBox = new VBox(percentage, buttons);
        infoBox.getStyleClass().add("info-box");
        HBox guy = new HBox();
        guy.getStyleClass().add("guy");
        VBox play = new VBox(guy, infoBox);
        play.getStyleClass().add("play");
        Scene scene = new Scene(play, 1920, 950);
        scene.getStylesheets().add(getClass().getResource("/app.css").toExternalForm());
        return scene;
    }

    private Scene clean() {
        Button cleanButtonGuy = new Button();
        cleanButtonGuy.getStyleClass().addAll("button-clean-guy");
        cleanButtonGuy.setOnMouseClicked(e -> {
            // tamagochi.clean();
            // mainStage.setScene(menuScene);
            // mainStage.setTitle("Tamagochi");
            mainStage.setScene(playScene);
            mainStage.setTitle("Tamagochi");
        });
        VBox clean = new VBox(cleanButtonGuy);
        Scene scene = new Scene(clean, 1920, 950);
        scene.getStylesheets().add(getClass().getResource("/app.css").toExternalForm());
        return scene;
    }

    @Override
    public void start(Stage stage) {
        mainStage = stage;
        playScene = play();
        cleanScene = clean();
        mainStage.setScene(cleanScene);
        mainStage.setTitle("Tamagochi");
        new GameManagerFX(stage);
        stage.show();
    }

    public static void main(String[] args) {
        // if (args.length == 0) {
        launch();
        // } else if (args[0] == "c") {*
        // if (args[0] == "cr") {
        // new GameManager();
        // } else {
        // new GameManager(GameManager.loadTamagochi());
        // }
        // new GameManager();
        // } else {
        // System.out.println("please enter argument 'c' for command line tamagochi or
        // nothing for jfx tamagochi");
        // }
    }

    // public static void main(String[] args) {
    //     // if (args.length == 0) {
    //     // launch();
    //     // } else if (args[0] == "c") {*
    //     if (args[0] == "cr") {
    //         new GameManager();
    //     } else {
    //         new GameManager(GameManager.loadTamagochi());
    //     }
    //     // new GameManager();
    //     // } else {
    //     // System.out.println("please enter argument 'c' for command line tamagochi or
    //     // nothing for jfx tamagochi");
    //     // }
    // }
}
