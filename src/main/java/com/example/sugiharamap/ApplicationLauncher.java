package com.example.sugiharamap;

import com.example.sugiharamap.pages.main.MainController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;


public class ApplicationLauncher extends Application {

    private MainController mainController;

    public void printFXVersion() {
        System.out.println(System.getProperty("javafx.version"));
    }

    @Override
    public void start(Stage primaryStage) {
        printFXVersion();
        initClientStage(primaryStage);
        primaryStage.show();
        primaryStage.setFullScreen(false);
        mainController.initViews();
    }

    private void initClientStage(Stage primaryStage) {
        primaryStage.setTitle("Sugihara Map");
        primaryStage.setResizable(true);
        mainController = new MainController();
        Scene primaryScene = new Scene(mainController.getView());
        primaryScene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.F11)
            {
                primaryStage.setFullScreen(!primaryStage.isFullScreen());
            }
        });
        primaryStage.setScene(primaryScene);
    }

}