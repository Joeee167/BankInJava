package com.example.bankproject;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public abstract class Person {

    protected String Username;
    protected String Password;

    public Person(String username, String password) {
        this.Username = username;
        this.Password = password;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        this.Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        this.Password = password;
    }


    public static Person login(String userName, String password) throws IOException
    {
        Person person = null;

        try
        {
            Connection connection = DatabaseConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM admins WHERE Username = '" + userName + "' AND Password = '" + password + "'");

            if (resultSet.next())
            {
                person = new Admin(userName, password);
                return person;
            }

            resultSet = statement.executeQuery("SELECT * FROM employees WHERE Username = '" + userName + "' AND Password = '" + password + "'");

            if (resultSet.next())
            {
                person = new Employee(
                        resultSet.getString("Username"),
                        resultSet.getString("Password"),
                        Float.parseFloat(resultSet.getString("Salary")),
                        resultSet.getString("Branch")

                );
                return person;
            }

            resultSet = statement.executeQuery("SELECT * FROM clients WHERE AccountNum = '" + userName + "' AND PIN = '" + password + "'");

            if (resultSet.next())
            {
                person = new Client(
                        resultSet.getString("AccountNum"),
                        resultSet.getString("PIN"),
                        resultSet.getString("ClientName"),
                        resultSet.getString("PhoneNum"),
                        resultSet.getFloat("AccBalance"),
                        resultSet.getFloat("Interest")
                );
                return person;
            }
        }

        catch (SQLException e) {
            throw new RuntimeException(e);
        }



        return person;
    }

    @Override
    public String toString() {
        return "Username: " + Username;
    }
}
