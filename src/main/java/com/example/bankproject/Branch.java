package com.example.bankproject;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Branch {

    private String ID;
    private String Location;
    private static ArrayList<Branch> Branches = new ArrayList<>();
    private ArrayList<Employee> Employees = new ArrayList<>();


    public Branch(String id, String location) {
        this.ID = id;
        this.Location = location;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        this.Location = location;
    }

    public static void getBranchesFromDatabase()
    {
        try
        {
            Connection connection = DatabaseConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM branch");

            while (resultSet.next())
            {
                String ID = resultSet.getString("ID");
                String Location = resultSet.getString("Location");
                Branches.add(new Branch(ID, Location));
            }
        }

        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    public static void addBranch(Branch branch) {
        Branches.add(branch);
    }

    public static ArrayList<Branch> getBranches() {
        return Branches;
    }

    public void addEmployee(Employee employee) {
        Employees.add(employee);
    }

    public static Branch getBranch(String ID) {
        for (Branch branch : Branches) {
            if (branch.getID().equals(ID)) {
                return branch;
            }
        }
        return null;
    }

    public static Branch getBranch(String ID,String Location) {
        for (Branch branch : Branches) {
            if (branch.getID().equals(ID) && branch.getLocation().equals(Location)) {
                return branch;
            }
        }
        return null;
    }

    public ArrayList<Employee> getEmployees() {
        return Employees;
    }

    public void removeEmployee(Employee employee)
    {
        Employees.remove(employee);
    }



    @Override
    public String toString() {
        return "Branch ID: " + ID + ", Location: " + Location;
    }


}
