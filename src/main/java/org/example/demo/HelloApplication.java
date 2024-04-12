package org.example.demo;


import javafx.application.Application;
import org.example.FX.LoginWindow;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) {
        LoginWindow loginWindow = new LoginWindow(stage);
        loginWindow.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}


