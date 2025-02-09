package com.example.bankproject;

import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class SavingsAccount implements Ioperations {

    private float SavingsBalance;
    public static float InterestRate = 0.1F;


    public SavingsAccount(float Balance)
    {
        this.SavingsBalance = Balance;
    }

    public float getSavingsBalance()
    {
        return SavingsBalance;
    }


    @Override
    public void Deposit(float amount, Client client)
    {
        try {
            Connection connection = DatabaseConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();

            String updateQuery = "UPDATE clients SET Interest = Interest + " + amount + " WHERE AccountNum = '" + client.getAccountNumber() + "'";
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
        if (SavingsBalance >= 1.1 * amount) {
            try {
                Connection connection = DatabaseConnection.getInstance().getConnection();
                Statement statement = connection.createStatement();

                String updateQuery = "UPDATE clients SET Interest = Interest - " + 1.1 * amount + " WHERE AccountNum = '" + person.getAccountNumber() + "'";
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

    public Float CalcMonth()
    {
        return 0.1f * SavingsBalance;
    }

    @Override
    public String toString() {
        return "SavingsAccount SavingsBalance: " + SavingsBalance + ", Interest Rate: " + InterestRate;
    }
}
