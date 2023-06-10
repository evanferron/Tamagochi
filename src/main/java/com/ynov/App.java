package com.ynov;

import javafx.application.Application;
import javafx.stage.Stage;
import com.ynov.fx.GameManagerFX;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        
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
