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

public class HireEmployeeController {

    private Person person;

    public void setPerson(Person person)
    {
        this.person = person;
    }

    public TextField EmpNumTextField;
    public PasswordField EmpPasswordField;
    public TextField EmpSalaryTextField;
    public TextField BranchIDTextField;
    public Button Submit;
    public Button BackToMain;

    public void handleSubmitButtonAction(ActionEvent actionEvent)
    {
        String EmpNum = EmpNumTextField.getText();
        String Password = EmpPasswordField.getText();
        float Salary  = Float.parseFloat(EmpSalaryTextField.getText());
        String BranchID = BranchIDTextField.getText();

        Employee employee = new Employee(EmpNum, Password, Salary, BranchID);


        if(person instanceof Admin)
        {
            ((Admin) person).HireEmployee(employee,BranchID);
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

            Stage stage = (Stage) EmpNumTextField.getScene().getWindow();
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
        EmpNumTextField.clear();
        EmpPasswordField.clear();
        EmpSalaryTextField.clear();
        BranchIDTextField.clear();
    }
}
