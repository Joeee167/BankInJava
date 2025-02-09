package com.example.bankproject;

import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DebitCard implements Ioperations {

    private float AccountBalance;

    public DebitCard(float AccountBalance) {
        this.AccountBalance = AccountBalance;
    }

    @Override
    public void Deposit(float amount, Client client) {

        try {
            Connection connection = DatabaseConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();

            String updateQuery = "UPDATE clients SET AccBalance = AccBalance + " + amount + " WHERE AccountNum = '" + client.getAccountNumber() + "'";
            statement.executeUpdate(updateQuery);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Deposit");
            alert.setHeaderText(null);
            alert.setContentText("Deposit Successful");
            alert.showAndWait();


        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void Withdraw(float amount, Client person) {
        if (AccountBalance >= amount) {
    try {
        Connection connection = DatabaseConnection.getInstance().getConnection();
        Statement statement = connection.createStatement();

        String updateQuery = "UPDATE clients SET AccBalance = AccBalance - " + amount + " WHERE AccountNum = '" + person.getAccountNumber() + "'";
        statement.executeUpdate(updateQuery);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Withdraw");
        alert.setHeaderText(null);
        alert.setContentText("Withdraw Successful");
        alert.showAndWait();

    }

    catch (SQLException ex)
    {
        ex.printStackTrace();
    }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Withdraw");
            alert.setHeaderText(null);
            alert.setContentText("Insufficient Funds");
            alert.showAndWait();
        }
    }

    public float getAccountBalance() {
        return AccountBalance;
    }

    @Override
    public String toString() {
        return "DebitCard SavingsBalance: " + AccountBalance;
    }
}
