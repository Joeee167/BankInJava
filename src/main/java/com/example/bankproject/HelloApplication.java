package com.example.bankproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class HelloApplication extends Application
{
    @Override
    public void start(Stage stage) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("LoginScreen.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 1100, 600);
        scene.getStylesheets().add(HelloApplication.class.getResource("style.css").toExternalForm());

        stage.setTitle("Login Screen");
        stage.setScene(scene);
        stage.show();

        // Load branches from database at start
        Branch.getBranchesFromDatabase();

    }

    public static void main(String[] args)
    {
        launch();
    }
}