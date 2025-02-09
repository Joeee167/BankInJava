package com.example.bankproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenuController {

    public Button ShowClientsButton;
    public Button AddClientButton;
    public Button FindClientButton;
    public Button UpdateClientButton;
    public Button DeleteClientButton;
    public Button TransactionButton;
    public Button ShowEmployeesButton;
    public Button HireEmployeeButton;
    public Button FireEmployeeButton;
    public Button TransferEmployeeButton;
    public Button LogOutButton;

    private Person person;

    public void setPerson(Person person)
    {
        this.person = person;
    }

    public void disableButtonsForAdmin() {

        TransactionButton.setDisable(true);
    }

    public void disableButtonsForClient() {

        ShowClientsButton.setDisable(true);
        AddClientButton.setDisable(true);
        FindClientButton.setDisable(true);
        UpdateClientButton.setDisable(true);
        DeleteClientButton.setDisable(true);
        ShowEmployeesButton.setDisable(true);
        HireEmployeeButton.setDisable(true);
        FireEmployeeButton.setDisable(true);
        TransferEmployeeButton.setDisable(true);
    }

    public void disableButtonsForEmployee()
    {
        ShowClientsButton.setDisable(true);
        ShowEmployeesButton.setDisable(true);
        HireEmployeeButton.setDisable(true);
        FireEmployeeButton.setDisable(true);
        TransferEmployeeButton.setDisable(true);
        TransactionButton.setDisable(true);

    }

    public void handleShowClientsButtonAction()
    {
        if(person instanceof Admin)
        {
            ((Admin) person).ShowClients();
        }
    }

    public void handleAddClientButtonAction(ActionEvent actionEvent)
    {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("AddClientScreen.fxml"));
            Parent AddClientScreen = fxmlLoader.load();

            AddClientController addClientController = fxmlLoader.getController();
            addClientController.setPerson(person);

            Stage stage = (Stage) LogOutButton.getScene().getWindow();
            Scene scene = new Scene(AddClientScreen,1300,700);
            scene.getStylesheets().add(HelloApplication.class.getResource("style.css").toExternalForm());
            stage.setScene(scene);
            stage.setTitle("Add Client");
            stage.show();

        }

        catch (IOException ex)
        {
            LogOutButton.setText("Failed to load add client: " + ex.getMessage());
        }
    }

    public void handleFindClientButtonAction(ActionEvent actionEvent)
    {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("FindClientScreen.fxml"));
            Parent FindClientScreen = fxmlLoader.load();

            FindClientController findClientController = fxmlLoader.getController();
            findClientController.setPerson(person);

            Stage stage = (Stage) LogOutButton.getScene().getWindow();
            Scene scene = new Scene(FindClientScreen,1300,700);
            scene.getStylesheets().add(HelloApplication.class.getResource("style.css").toExternalForm());
            stage.setScene(scene);
            stage.setTitle("Find Client");
            stage.show();

        }

        catch (IOException ex)
        {
            LogOutButton.setText("Failed to load find client: " + ex.getMessage());
        }
    }

    public void handleUpdateClientButtonAction(ActionEvent actionEvent)
    {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("UpdateClientScreen.fxml"));
            Parent UpdateClientScreen = fxmlLoader.load();

            UpdateClientController updateClientController = fxmlLoader.getController();
            updateClientController.setPerson(person);

            Stage stage = (Stage) LogOutButton.getScene().getWindow();
            Scene scene = new Scene(UpdateClientScreen,1300,700);
            scene.getStylesheets().add(HelloApplication.class.getResource("style.css").toExternalForm());
            stage.setScene(scene);
            stage.setTitle("Update Client");
            stage.show();

        }

        catch (IOException ex)
        {
            LogOutButton.setText("Failed to load update client: " + ex.getMessage());
        }
    }

    public void handleDeleteClientButtonAction(ActionEvent actionEvent)
    {

        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("DeleteClientScreen.fxml"));
            Parent DeleteClientScreen = fxmlLoader.load();

            DeleteClientController deleteClientController = fxmlLoader.getController();
            deleteClientController.setPerson(person);

            Stage stage = (Stage) LogOutButton.getScene().getWindow();
            Scene scene = new Scene(DeleteClientScreen,1300,700);
            scene.getStylesheets().add(HelloApplication.class.getResource("style.css").toExternalForm());
            stage.setScene(scene);
            stage.setTitle("Delete Client");
            stage.show();

        }

        catch (IOException ex)
        {
            LogOutButton.setText("Failed to load delete client: " + ex.getMessage());
        }
    }

    public void handleTransactionButtonAction(ActionEvent actionEvent)
    {

        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("TransactionsScreen.fxml"));
            Parent TransactionScreen = fxmlLoader.load();

            TransactionsController transactionsController = fxmlLoader.getController();
            transactionsController.setPerson(person);


            Stage stage = (Stage) LogOutButton.getScene().getWindow();
            Scene scene = new Scene(TransactionScreen,1300,700);
            scene.getStylesheets().add(HelloApplication.class.getResource("style.css").toExternalForm());
            stage.setScene(scene);
            stage.setTitle("Transactions");
            stage.show();

        }

        catch (IOException ex)
        {
            LogOutButton.setText("Failed to load transaction: " + ex.getMessage());
        }
    }


    public void handleShowEmployeesButtonAction(ActionEvent actionEvent)
    {
        if(person instanceof Admin)
        {
            ((Admin) person).ShowEmployees();
        }
    }

    public void handleHireEmployeeButtonAction(ActionEvent actionEvent)
    {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("HireEmployeeScreen.fxml"));
            Parent HireEmployeeScreen = fxmlLoader.load();

            HireEmployeeController hireEmployeeController = fxmlLoader.getController();
            hireEmployeeController.setPerson(person);

            Stage stage = (Stage) LogOutButton.getScene().getWindow();
            Scene scene = new Scene(HireEmployeeScreen,1300,700);
            scene.getStylesheets().add(HelloApplication.class.getResource("style.css").toExternalForm());
            stage.setScene(scene);
            stage.setTitle("Hire Employee");
            stage.show();

        }

        catch (IOException ex)
        {
            LogOutButton.setText("Failed to load delete client: " + ex.getMessage());
        }
    }

    public void handleFireEmployeeButtonAction(ActionEvent actionEvent)
    {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("FireEmployeeScreen.fxml"));
            Parent FireEmployeeScreen = fxmlLoader.load();

            FireEmployeeController fireEmployeeController = fxmlLoader.getController();
            fireEmployeeController.setPerson(person);

            Stage stage = (Stage) LogOutButton.getScene().getWindow();
            Scene scene = new Scene(FireEmployeeScreen,1300,700);
            scene.getStylesheets().add(HelloApplication.class.getResource("style.css").toExternalForm());
            stage.setScene(scene);
            stage.setTitle("Fire Employee");
            stage.show();

        }

        catch (IOException ex)
        {
            LogOutButton.setText("Failed to load Fire employee: " + ex.getMessage());
        }
    }

    public void handleTransferEmployeeButtonAction(ActionEvent actionEvent)
    {

        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("TransferEmployeeScreen.fxml"));
            Parent TransferEmployee = fxmlLoader.load();

            TransferEmployeeController transferEmployeeController = fxmlLoader.getController();
            transferEmployeeController.setPerson(person);

            Stage stage = (Stage) LogOutButton.getScene().getWindow();
            Scene scene = new Scene(TransferEmployee,1300,700);
            scene.getStylesheets().add(HelloApplication.class.getResource("style.css").toExternalForm());
            stage.setScene(scene);
            stage.setTitle("Transfer Employee");
            stage.show();

        }

        catch (IOException ex)
        {
            LogOutButton.setText("Failed to load Fire employee: " + ex.getMessage());
        }
    }

    public void handleLogoutButtonAction(ActionEvent actionEvent)
    {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("LoginScreen.fxml"));
            Parent Login = fxmlLoader.load();



            Stage stage = (Stage) LogOutButton.getScene().getWindow();
            Scene scene = new Scene(Login,1300,700);
            scene.getStylesheets().add(HelloApplication.class.getResource("style.css").toExternalForm());
            stage.setScene(scene);
            stage.setTitle("Login Screen");
            stage.show();

        }

        catch (IOException ex)
        {
            LogOutButton.setText("Failed to load Fire employee: " + ex.getMessage());
        }
    }
}
