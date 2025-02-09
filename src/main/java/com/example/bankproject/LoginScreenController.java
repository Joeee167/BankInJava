package com.example.bankproject;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginScreenController
{

    @FXML
    private TextField UsernameField;

    @FXML
    private PasswordField PasswordField;

    @FXML
    private Label StatusLabel;

    @FXML
    private void handleLoginButtonAction()
    {
        String username = UsernameField.getText();
        String password = PasswordField.getText();

        try
        {
           Person person = Person.login(username, password);

           if (person != null)
           {
               ShowMainMenu(person);
           }

           else
           {
               showAlertAndResetFields();
           }
        }
        catch (IOException ex)
        {
            StatusLabel.setText("Login failed: " + ex.getMessage());
        }
    }

    private void showAlertAndResetFields() {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Login failed");
        alert.setHeaderText(null);
        alert.setContentText("Invalid username or password");

        alert.showAndWait();
        UsernameField.clear();
        PasswordField.clear();

    }

    private void ShowMainMenu(Person person)
    {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("MainMenu.fxml"));
            Parent mainMenu = fxmlLoader.load();

            MainMenuController mainMenuController = fxmlLoader.getController();
            mainMenuController.setPerson(person);

            boolean flag = false;

            if(person instanceof Admin)
            {
                mainMenuController.disableButtonsForAdmin();
                flag = true;
            }

            if (person instanceof Client && !flag)
            {
                mainMenuController.disableButtonsForClient();
            }

            if(person instanceof Employee && !flag)
            {
                mainMenuController.disableButtonsForEmployee();
            }

            Stage stage = (Stage) UsernameField.getScene().getWindow();
            Scene scene = new Scene(mainMenu,1300,700);
            scene.getStylesheets().add(HelloApplication.class.getResource("style.css").toExternalForm());
            stage.setScene(scene);
            stage.setTitle("Main Menu");
            stage.show();

        }

        catch (IOException ex)
        {
            StatusLabel.setText("Failed to load main menu: " + ex.getMessage());
        }
    }
}