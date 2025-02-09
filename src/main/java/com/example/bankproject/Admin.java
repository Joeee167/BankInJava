package com.example.bankproject;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Admin extends Employee{


    public Admin(String username, String password) {
        super(username, password);
    }

    public void ShowClients() {
        try {
            Connection connection = DatabaseConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM clients");

            List<ClientInfo> clientList = new ArrayList<>();

            while (resultSet.next()) {
                clientList.add(new ClientInfo(
                        resultSet.getString("AccountNum"),
                        resultSet.getString("PIN"),
                        resultSet.getString("ClientName"),
                        resultSet.getString("PhoneNum"),
                        resultSet.getString("AccBalance"),
                        resultSet.getString("Interest")
                ));
            }

            TableView<ClientInfo> tableView = new TableView<>();

            TableColumn<ClientInfo, String> AccountNumColumn = new TableColumn<>("Account Num");
            AccountNumColumn.setCellValueFactory(cellData -> cellData.getValue().AccountNumProperty());


            TableColumn<ClientInfo, String> PINColumn = new TableColumn<>("PIN");
            PINColumn.setCellValueFactory(cellData -> cellData.getValue().PINProperty());

            TableColumn<ClientInfo, String> ClientNameColumn = new TableColumn<>("Client Name");
            ClientNameColumn.setCellValueFactory(cellData -> cellData.getValue().ClientNameProperty());

            TableColumn<ClientInfo, String> PhoneNumColumn = new TableColumn<>("Phone Num");
            PhoneNumColumn.setCellValueFactory(cellData -> cellData.getValue().PhoneNumProperty());

            TableColumn<ClientInfo, String> AccBalanceColumn = new TableColumn<>("AccBalance");
            AccBalanceColumn.setCellValueFactory(cellData -> cellData.getValue().AccBalanceProperty());

            TableColumn<ClientInfo, String> InterestColumn = new TableColumn<>("Interest");
            InterestColumn.setCellValueFactory(cellData -> cellData.getValue().InterestProperty());

            tableView.getColumns().addAll(AccountNumColumn, PINColumn, ClientNameColumn, PhoneNumColumn, AccBalanceColumn, InterestColumn);

            // Set table data
            tableView.setItems(FXCollections.observableArrayList(clientList));

            // Create and display modal window
            VBox vbox = new VBox(tableView);
            Scene scene = new Scene(vbox);
            Stage dialog = new Stage();
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.setTitle("Clients");
            dialog.setScene(scene);
            dialog.setWidth(800); // Adjust width as needed
            dialog.setHeight(400); // Adjust height as needed
            dialog.showAndWait();
        }

        catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    static class ClientInfo {
        private final String AccountNum;
        private final String PIN;
        private final String ClientName;
        private final String PhoneNum;
        private final String AccBalance;
        private final String Interest;

        public ClientInfo(String AccountNum, String PIN, String ClientName, String PhoneNum, String AccBalance, String Interest) {
            this.AccountNum = AccountNum;
            this.PIN = PIN;
            this.ClientName = ClientName;
            this.PhoneNum = PhoneNum;
            this.AccBalance = AccBalance;
            this.Interest = Interest;
        }

        public StringProperty AccountNumProperty() {
            return new SimpleStringProperty(AccountNum);
        }

        public StringProperty PINProperty() {
            return new SimpleStringProperty(PIN);
        }

        public StringProperty ClientNameProperty() {
            return new SimpleStringProperty(ClientName);
        }

        public StringProperty PhoneNumProperty() {
            return new SimpleStringProperty(PhoneNum);
        }

        public StringProperty AccBalanceProperty() {
            return new SimpleStringProperty(AccBalance);
        }

        public StringProperty InterestProperty() {
            return new SimpleStringProperty(Interest);
        }
    }

    public void ShowEmployees() {
    try {
        Connection connection = DatabaseConnection.getInstance().getConnection();
        Statement statement = connection.createStatement();
        String query = "SELECT e.Username, e.Password, e.Salary, b.Location " +
                       "FROM employees e " +
                       "JOIN branch b ON e.Branch = b.ID";
        ResultSet resultSet = statement.executeQuery(query);

        List<EmployeeInfo> employeeList = new ArrayList<>();

        while (resultSet.next()) {
            employeeList.add(new EmployeeInfo(
                    resultSet.getString("Username"),
                    resultSet.getString("Password"),
                    resultSet.getString("Salary"),
                    resultSet.getString("Location") // Get branch location
            ));
        }

        TableView<EmployeeInfo> tableView = new TableView<>();

        TableColumn<EmployeeInfo, String> UsernameColumn = new TableColumn<>("Username");
        UsernameColumn.setCellValueFactory(cellData -> cellData.getValue().UsernameProperty());

        TableColumn<EmployeeInfo, String> PasswordColumn = new TableColumn<>("Password");
        PasswordColumn.setCellValueFactory(cellData -> cellData.getValue().PasswordProperty());

        TableColumn<EmployeeInfo, String> SalaryColumn = new TableColumn<>("Salary");
        SalaryColumn.setCellValueFactory(cellData -> cellData.getValue().SalaryProperty());

        TableColumn<EmployeeInfo, String> BranchLocationColumn = new TableColumn<>("Branch Location");
        BranchLocationColumn.setCellValueFactory(cellData -> cellData.getValue().BranchLocationProperty());

        tableView.getColumns().addAll(UsernameColumn, PasswordColumn, SalaryColumn, BranchLocationColumn);

        // Set table data
        tableView.setItems(FXCollections.observableArrayList(employeeList));

        // Create and display modal window
        VBox vbox = new VBox(tableView);
        Scene scene = new Scene(vbox);
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("Employees");
        dialog.setScene(scene);
        dialog.setWidth(800); // Adjust width as needed
        dialog.setHeight(400); // Adjust height as needed
        dialog.showAndWait();
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}

    static class EmployeeInfo
    {
        private final String Username;
        private final String Password;
        private final String Salary;
        private final String BranchLocation;

        public EmployeeInfo(String Username, String Password, String Salary, String BranchID)
        {
            this.Username = Username;
            this.Password = Password;
            this.Salary = Salary;
            this.BranchLocation = BranchID;
        }

        public StringProperty UsernameProperty()
        {
            return new SimpleStringProperty(Username);
        }

        public StringProperty PasswordProperty()
        {
            return new SimpleStringProperty(Password);
        }

        public StringProperty SalaryProperty()
        {
            return new SimpleStringProperty(Salary);
        }

        public StringProperty BranchLocationProperty()
        {
            return new SimpleStringProperty(BranchLocation);
        }
    }

    public void Fire(Employee employee)
    {
        try
        {
            Connection connection = DatabaseConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM employees WHERE Username = '" + employee.getUsername() + "'");

            if (!resultSet.next())
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Fire Employee failed");
                alert.setHeaderText(null);
                alert.setContentText("Employee with Username does not exist");
                alert.showAndWait();
                return;
            }

            statement.executeUpdate("DELETE FROM employees WHERE Username = '" + employee.getUsername() + "'");

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Fire Employee");
            alert.setHeaderText(null);
            alert.setContentText("Employee Fired Successfully");
            alert.showAndWait();

            for (Branch branch : Branch.getBranches())
            {
                if (branch.getEmployees().contains(employee))
                {
                    branch.removeEmployee(employee);
                    break;
                }
            }
        }

        catch (SQLException ex) {
            ex.printStackTrace();

        }
    }

    public void HireEmployee(Employee employee, String branch)
    {
        try
        {
            Connection connection = DatabaseConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM branch WHERE ID = '" + branch + "'");
            if (!resultSet.next()) {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Hire Employee failed");
                alert.setHeaderText(null);
                alert.setContentText("Branch with ID does not exist");
                alert.showAndWait();
                return;
            }


            connection = DatabaseConnection.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM employees WHERE Username = '" + employee.getUsername() + "'");

            if (resultSet.next())
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Hire Employee failed");
                alert.setHeaderText(null);
                alert.setContentText("Employee with Username already exists");
                alert.showAndWait();
                return;
            }

            statement.executeUpdate("INSERT INTO employees VALUES ('" + employee.getUsername() + "', '" + employee.getPassword() + "', " + employee.getSalary() + ", '" + branch + "')");

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Hire Employee");
            alert.setHeaderText(null);
            alert.setContentText("Employee Hired Successfully");
            alert.showAndWait();

            com.example.bankproject.Branch.getBranch(branch).addEmployee(employee);
        }

        catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public void TransferEmployee(Employee employee, String branchID) {
        try {
            Connection connection = DatabaseConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();

            // Check if the employee exists
            ResultSet resultSet = statement.executeQuery("SELECT * FROM employees WHERE Username = '" + employee.getUsername() + "'");
            if (!resultSet.next()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Transfer Employee failed");
                alert.setHeaderText(null);
                alert.setContentText("Employee with Username does not exist");
                alert.showAndWait();
                return;
            }

            // Check if the current branch exists
            resultSet = statement.executeQuery("SELECT * FROM branch WHERE ID = '" + employee.getBranch().getID() + "'");
            if (!resultSet.next()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Transfer Employee failed");
                alert.setHeaderText(null);
                alert.setContentText("Current branch does not exist");
                alert.showAndWait();
                return;
            }

            // Check if the new branch exists
            resultSet = statement.executeQuery("SELECT * FROM branch WHERE ID = '" + branchID + "'");
            if (!resultSet.next()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Transfer Employee failed");
                alert.setHeaderText(null);
                alert.setContentText("New branch does not exist");
                alert.showAndWait();
                return;
            }

            // Update the employee's branch
            statement.executeUpdate("UPDATE employees SET Branch = '" + branchID + "' WHERE Username = '" + employee.getUsername() + "'");

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Transfer Employee");
            alert.setHeaderText(null);
            alert.setContentText("Employee Transferred Successfully");
            alert.showAndWait();

            // Update the branch objects
            Branch currentBranch = Branch.getBranch(employee.getBranch().getID());
            Branch newBranch = Branch.getBranch(branchID);

            if (currentBranch != null && newBranch != null) {
                currentBranch.removeEmployee(employee);
                newBranch.addEmployee(employee);
                employee.setBranch(com.example.bankproject.Branch.getBranch(branchID));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Admin: " + Username;
    }
}
