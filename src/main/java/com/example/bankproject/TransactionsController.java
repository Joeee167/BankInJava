package com.example.bankproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class TransactionsController {

    @FXML
    private Button Depositbt;
    @FXML
    private Button Withdrawbt;
    @FXML
    private TextField AmountTextField;
    @FXML
    private CheckBox DebitCardCheckBox;
    @FXML
    private CheckBox SavingsAccountCheckBox;
    private Person person;

    public void setPerson(Person person) {
        this.person = person;
    }

    @FXML
    private Button BackToMain;

    @FXML
    public void handleBackToMainMenuButtonAction(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("MainMenu.fxml"));
            Parent mainMenu = fxmlLoader.load();

            MainMenuController mainMenuController = fxmlLoader.getController();
            mainMenuController.setPerson(person);

            boolean flag = false;

            if (person instanceof Admin) {
                mainMenuController.disableButtonsForAdmin();
                flag = true;
            }

            if (person instanceof Client && !flag) {
                mainMenuController.disableButtonsForClient();
            }

            if (person instanceof Employee && !flag) {
                mainMenuController.disableButtonsForEmployee();
            }

            Stage stage = (Stage) Depositbt.getScene().getWindow();
            Scene scene = new Scene(mainMenu, 1300, 700);
            scene.getStylesheets().add(HelloApplication.class.getResource("style.css").toExternalForm());
            stage.setScene(scene);
            stage.setTitle("Main Menu");
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void handleDepositButtonAction(ActionEvent actionEvent)
    {
        float amount = Float.parseFloat(AmountTextField.getText());

        if (DebitCardCheckBox.isSelected()) {
            ((Client) person).getDebitCard().Deposit(amount, (Client) person);
        }

        else if (SavingsAccountCheckBox.isSelected())
        {
            ((Client) person).getSavingsAccount().Deposit(amount, (Client) person);
        }

        else {
            showAlert("Please select an account type.");
        }
    }

    @FXML
    public void handleWithdrawButtonAction(ActionEvent actionEvent) {
        float amount = Float.parseFloat(AmountTextField.getText());
        if (DebitCardCheckBox.isSelected()) {
            ((Client) person).getDebitCard().Withdraw(amount, (Client) person);
        } else if (SavingsAccountCheckBox.isSelected()) {
            ((Client) person).getSavingsAccount().Withdraw(amount, (Client) person);
        } else {
            showAlert("Please select an account type.");
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}