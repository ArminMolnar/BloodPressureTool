package controller;

import components.TableFactory;
import components.TextFieldFactory;
import connection.DatabaseConnection;

import java.sql.*;


public class Controller {

    private static Controller instance;

    private Controller() {
    }


    public void addRecord(String name, String systolicValue, String diastolicValue, String pulseValue, String pulsePressureValue, java.sql.Timestamp timestamp) throws SQLException {

        String query = "INSERT INTO record (name, systolic, diastolic, pulse, pulsePressure, date) VALUES (?, ?, ?, ?, ?, ?)";
        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement = null;

        try {
            connection = DatabaseConnection.getConnection();
            statement = connection.prepareStatement(query);

            statement.setString(1, name);
            statement.setString(2, systolicValue);
            statement.setString(3, diastolicValue);
            statement.setString(4, pulseValue);
            statement.setString(5, pulsePressureValue);
            java.util.Date utilDate = new java.util.Date();
            java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(utilDate.getTime());
            statement.setTimestamp(6, sqlTimestamp);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Record inserted successfully.");
            } else {
                System.out.println("Failed to insert record.");
            }
        } finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
    }

    public void displayRecord(TextFieldFactory textFieldFactory) throws SQLException {

        String query = "SELECT * FROM record WHERE name = ?";
        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String nameInput = textFieldFactory.getNameTextField().getText();
        TableFactory tableFactory = TableFactory.getInstance();

        try {
            connection = DatabaseConnection.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, nameInput);
            resultSet = statement.executeQuery();

            tableFactory.getModel().setRowCount(0);

            while (resultSet.next()) {
                String name = resultSet.getString("Name");
                String systolicValue = resultSet.getString("Systolic");
                String diastolicValue = resultSet.getString("Diastolic");
                String pulseValue = resultSet.getString("Pulse");
                String pulsePressureValue = resultSet.getString("PulsePressure");
                Timestamp timeAdded = resultSet.getTimestamp("Date");
                tableFactory.getModel().addRow(new Object[]{name, systolicValue, diastolicValue, pulseValue, pulsePressureValue, timeAdded});
                tableFactory.setVisible(true);
            }
        } finally {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
    }

    public boolean checkIfPersonExists(TextFieldFactory textFieldFactory) throws SQLException {
        String query = "SELECT CASE WHEN EXISTS (SELECT 1 FROM record WHERE name = ?) THEN 1 ELSE 0 END as record_exists";
        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String name = textFieldFactory.getNameTextField().getText();

        try {
            connection = DatabaseConnection.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, name);
            resultSet = statement.executeQuery();
            {
                if (resultSet.next()) {
                    return resultSet.getInt("record_exists") == 1;
                }
            }
        } finally {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
        return false;
    }

    public static Controller getInstance(){
        if(instance == null){
            instance = new Controller();
        }
        return instance;
    }

}
