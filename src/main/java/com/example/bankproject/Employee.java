package com.example.bankproject;


import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Employee extends Person{

    private float Salary;
    private Branch Branch;

    public Employee(String username, String password, float salary, String branchID) {
        super(username, password);
        this.Salary = salary;
        this.Branch = com.example.bankproject.Branch.getBranch(branchID);
    }

    public Employee(String username, String password) {
        super(username, password);
    }

    public float getSalary() {
        return Salary;
    }

    public void setSalary(float salary) {
        this.Salary = salary;
    }

    public void AddClient(Client client) {
        try
        {
            Connection connection = DatabaseConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM clients WHERE AccountNum = '" + client.getAccountNumber() + "'");

            if (resultSet.next()) {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Add Client failed");
                alert.setHeaderText(null);
                alert.setContentText("Client with Same Account Number exists");

                alert.showAndWait();
                return;
            }

            statement.executeUpdate("INSERT INTO clients VALUES ('" + client.getAccountNumber() + "', '" + client.getPIN() + "', '" + client.getClientName() + "', '" + client.getPhoneNumber() + "', " + client.getDebitCard().getAccountBalance() + ", " + client.getSavingsAccount().getSavingsBalance() + ")");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Add Client");
            alert.setHeaderText(null);
            alert.setContentText("Client Added Successfully");
            alert.showAndWait();
        }

        catch (SQLException ex) {
            ex.printStackTrace();
        }


    }

    public void FindClient(String AccountNum) {
        try {
            Connection connection = DatabaseConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM clients WHERE AccountNum = '" + AccountNum + "'");

            if (!resultSet.next()) {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Find Client failed");
                alert.setHeaderText(null);
                alert.setContentText("Client with Account Number does not exist");

                alert.showAndWait();
                return;
            } else {
                StringBuilder clientInfo = new StringBuilder();
                clientInfo.append("Account Number: ").append(resultSet.getString("AccountNum")).append("\n");
                clientInfo.append("PIN: ").append(resultSet.getString("PIN")).append("\n");
                clientInfo.append("Client Name: ").append(resultSet.getString("ClientName")).append("\n");
                clientInfo.append("Phone Number: ").append(resultSet.getString("PhoneNum")).append("\n");
                clientInfo.append("Account Balance: ").append(resultSet.getFloat("AccBalance")).append("\n");
                clientInfo.append("Savings Balance: ").append(resultSet.getFloat("Interest")).append("\n");

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Client Information");
                alert.setHeaderText(null);
                alert.setContentText(clientInfo.toString());
                alert.showAndWait();
            }


        } catch (SQLException ex) {
            ex.printStackTrace();

        }
    }

    public void DeleteClient(String AccountNum)
    {
        try
        {
            Connection connection = DatabaseConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM clients WHERE AccountNum = '" + AccountNum + "'");

            if (!resultSet.next()) {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Delete Client failed");
                alert.setHeaderText(null);
                alert.setContentText("Client with Account Number does not exist");

                alert.showAndWait();
                return;
            }

            statement.executeUpdate("DELETE FROM clients WHERE AccountNum = '" + AccountNum + "'");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Delete Client");
            alert.setHeaderText(null);
            alert.setContentText("Client Delete Successfully");
            alert.showAndWait();
        }

        catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public void UpdateClient(Client client)
    {
        try
        {
            Connection connection = DatabaseConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM clients WHERE AccountNum = '" + client.getAccountNumber() + "'");

            if (!resultSet.next()) {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Update Client failed");
                alert.setHeaderText(null);
                alert.setContentText("Client with Account Number does not exist");

                alert.showAndWait();
                return;
            }

            statement.executeUpdate("UPDATE clients SET PIN = '" + client.getPIN() + "', ClientName = '" + client.getClientName() + "', PhoneNum = '" + client.getPhoneNumber() + "', AccBalance = " + client.getDebitCard().getAccountBalance() + ", Interest = " + client.getSavingsAccount().getSavingsBalance() + " WHERE AccountNum = '" + client.getAccountNumber() + "'");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Update Client");
            alert.setHeaderText(null);
            alert.setContentText("Client Updated Successfully");
            alert.showAndWait();
        }

        catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public Branch getBranch() {
        return Branch;
    }

    public void setBranch(Branch branch) {
        this.Branch = branch;
    }

    @Override
    public String toString() {
        return "Employee: " + Username + ", Salary: " + Salary;
    }
}
