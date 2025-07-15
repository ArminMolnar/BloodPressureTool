package service;

import components.LabelFactory;
import components.TextFieldFactory;
import connection.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Calculator {

    private final LabelFactory labelFactory;

    public Calculator(LabelFactory labelFactory) {
        this.labelFactory = labelFactory;
    }

    public void calculateAverage(TextFieldFactory textFieldFactory) throws SQLException {

        String query = "SELECT calculate_average(?) as result";
        String nameInput = textFieldFactory.getNameTextField().getText();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseConnection.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, nameInput);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {

                String averagesText = resultSet.getString("result");
                labelFactory.getAvgResultLabel().setText(averagesText);
            }
        } finally {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
    }

}
