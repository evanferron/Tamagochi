package com.ynov;

import javafx.application.Application;
import javafx.stage.Stage;

import com.ynov.command.GameManager;
import com.ynov.fx.GameManagerFX;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        new GameManagerFX(stage);
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            launch();
        } else if (args[0].equals("c")) {
            new GameManager(GameManager.loadTamagochi());
        } else if (args[0].equals("cr")) {
            new GameManager();
        } else {
            System.out.println("please enter argument 'c' for command line tamagochi or nothing for jfx tamagochi");
        }
    }
}
