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
            // if (Integer.parseInt(leftButton.getText()) < 0) {
            //     menuScene.show();
            // }
        });

        middleButton.setOnMouseClicked(e -> {
            percentage.setText(String.valueOf(Integer.parseInt(percentage.getText()) + 10));
            // if (Integer.parseInt(leftButton.getText()) < 0) {
            //     menuScene.show();
            // }
        });

        rightButton.setOnMouseClicked(e -> {
            percentage.setText(String.valueOf(Integer.parseInt(percentage.getText()) + 5));
            // if (Integer.parseInt(leftButton.getText()) < 0) {
            //     menuScene.show();
            // }
        });

        HBox buttons = new HBox(leftButton, middleButton, rightButton);
        buttons.getStyleClass().add("buttons");
        // buttons.setAlignment(Pos.CENTER);
        VBox infoBox = new VBox(percentage, buttons);
        infoBox.getStyleClass().add("info-box");
        // infoBox.setAlignment(Pos.CENTER);
        Scene scene = new Scene(infoBox, 1920, 950);
        scene.getStylesheets().add(getClass().getResource("/app.css").toExternalForm());
        return scene;
    }

    // @Override
    // public void start(Stage stage) {
    //     new GameManagerFX(stage);
    // }

    @Override
    public void start(Stage stage) {
        mainStage = stage;
        playScene = play();
        stage.setScene(playScene);
        stage.setTitle("Tamagochi");
        new GameManagerFX(stage);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
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
