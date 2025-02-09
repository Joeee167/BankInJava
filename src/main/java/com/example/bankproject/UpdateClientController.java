package com.example.bankproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class UpdateClientController {

    private Person person;

    public void setPerson(Person person)
    {
        this.person = person;
    }


    public TextField AccountNumTextField;
    public PasswordField PINPasswordField;
    public TextField ClientNameTextField;
    public TextField PhoneNumTextField;
    public TextField AccBalanceTextField;
    public TextField InterestTextField;

    public Button Submit;
    public Button BackToMain;

    public void handleSubmitButtonAction(ActionEvent actionEvent)
    {
        String AccountNum = AccountNumTextField.getText();
        String PIN = PINPasswordField.getText();
        String ClientName = ClientNameTextField.getText();
        String PhoneNum = PhoneNumTextField.getText();
        float AccBalance  = Float.parseFloat(AccBalanceTextField.getText());
        float Interest = Float.parseFloat(InterestTextField.getText());

        Client client = new Client(AccountNum, PIN, ClientName, PhoneNum, AccBalance, Interest);

        if(person instanceof Employee)
        {
            ((Employee) person).UpdateClient(client);
        }

        ClearFields();

    }

    public void handleBackToMainMenuButtonAction(ActionEvent actionEvent)
    {
        try {
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

            if (person instanceof Client && !flag) {
                mainMenuController.disableButtonsForClient();
            }

            if (person instanceof Employee && !flag) {
                mainMenuController.disableButtonsForEmployee();
            }

            Stage stage = (Stage) AccountNumTextField.getScene().getWindow();
            Scene scene = new Scene(mainMenu,1300,700);
            scene.getStylesheets().add(HelloApplication.class.getResource("style.css").toExternalForm());
            stage.setScene(scene);
            stage.setTitle("Main Menu");
            stage.show();
        }

        catch (IOException ex) {
            ex.printStackTrace();

        }
    }

    public void ClearFields()
    {
        AccountNumTextField.clear();
        PINPasswordField.clear();
        ClientNameTextField.clear();
        PhoneNumTextField.clear();
        AccBalanceTextField.clear();
        InterestTextField.clear();
    }


}
