package com.example.bankproject;

import java.sql.*;

public class DatabaseConnection {

    private static DatabaseConnection instance;
    private Connection connection;
    private String DB_user = "Youssef";
    private String DB_password = "1234";
    private String DB_URL = "jdbc:mysql://localhost:3306/Bank";

    private DatabaseConnection() {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(DB_URL, DB_user, DB_password);
        }

        catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }
    }

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}