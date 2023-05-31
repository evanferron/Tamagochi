package com.ynov;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import com.ynov.command.GameManager;
import com.ynov.fx.GameManagerFX;
import com.ynov.tagmagochi.Tamagochi;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) {
        new GameManagerFX(stage);
    }

    public static void main(String[] args) {
        // if (args.length == 0) {
        // launch();
        // } else if (args[0] == "c") {*
        if (args[0] == "cr") {
            new GameManager();
        } else {
            new GameManager(GameManager.loadTamagochi());
        }
        // new GameManager();
        // } else {
        // System.out.println("please enter argument 'c' for command line tamagochi or
        // nothing for jfx tamagochi");
        // }
    }
}
