package buttonController;

import components.*;
import connection.DatabaseConnection;
import controller.Controller;
import service.Calculator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class ButtonEventHandler implements ActionListener {
    JFrame frame;
    private final Controller controller;
    private final Calculator calculator;
    private final ButtonManager buttonManager;
    private final FactoryBundle factory;


    public ButtonEventHandler(JFrame frame, Controller controller, Calculator calculator, FactoryBundle factory) {
        this.frame = frame;
        this.controller = controller;
        this.calculator = calculator;
        this.factory = factory;
        this.buttonManager = new ButtonManager(frame, factory);
    }

    private void handleAction(Object source) throws SQLException {
        if (source == factory.getButtonFactory().getTestConnectionButton()) {
            handleTestConnection();
        } else if (source == factory.getButtonFactory().getOkButton()) {
            handleOkButton();
        } else if (source == factory.getButtonFactory().getAddRecordButton()) {
            handleAddRecord();
        } else if (source == factory.getButtonFactory().getDisplayRecordButton()) {
            handleDisplayRecordButton();
        } else if (source == factory.getButtonFactory().getDisplayAverageButton()) {
            handleAverageRecordButton();
        } else if (source == factory.getButtonFactory().getReturnButton()) {
            handleReturnButton();
        }
    }

    private void handleTestConnection() {
        char[] passwordChar = (factory.getPasswordFieldFactory().getPasswordField().getPassword());
        String passwordString = new String(passwordChar);
        if (DatabaseConnection.testConnection(passwordString)) {
            DatabaseConnection.setPassword(passwordString);
            JOptionPane.showMessageDialog(frame, "Connected to the database");

            buttonManager.setupUserSelection();

        } else {
            JOptionPane.showMessageDialog(frame, "Connection failed. You might entered the wrong password");
        }
    }

    private void handleOkButton() {
        String name = getName();
        if (name != null && !name.isEmpty() && name.matches("^[a-zA-Z]+$")) {
            frame.getContentPane().removeAll();
            buttonManager.addButtons();
            buttonManager.refreshFrame();
        } else {
            JOptionPane.showMessageDialog(frame, "Invalid input. Enter letters only.");
        }

    }

    private void handleAddRecord() throws SQLException {
        setupInputVerifier();
        String name = getName();
        try {
            int systolic = Integer.parseInt(factory.getTextFieldFactory().getSystolicField().getText());
            int diastolic = Integer.parseInt(factory.getTextFieldFactory().getDiastolicField().getText());
            int pulse = Integer.parseInt(factory.getTextFieldFactory().getPulseField().getText());
            int pulsePressure = systolic - diastolic;

            java.util.Date utilDate = new java.util.Date();
            java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(utilDate.getTime());

            controller.addRecord(name, String.valueOf(systolic), String.valueOf(diastolic), String.valueOf(pulse), String.valueOf(pulsePressure), sqlTimestamp);
            JOptionPane.showMessageDialog(frame, "Record added");
            buttonManager.clearTextField();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Invalid input. Enter numbers and only numbers");
        }
    }

    private void handleDisplayRecordButton() throws SQLException {
        if (controller.checkIfPersonExists(factory.getTextFieldFactory())) {
            controller.displayRecord(factory.getTextFieldFactory());
        } else {
            JOptionPane.showMessageDialog(frame, "No data for this person yet." + '\n' + "Enter blood pressure values first");
        }
    }

    private void handleAverageRecordButton() throws SQLException {
        buttonManager.addAverageData();
        calculator.calculateAverage(factory.getTextFieldFactory());
        buttonManager.refreshFrame();

    }

    private void handleReturnButton() {
        buttonManager.setupUserSelection();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            handleAction(e.getSource());
        } catch (SQLException ex) {
            handleSQLException(ex);
        }
    }

    private String getName() {
        return factory.getTextFieldFactory().getNameTextField().getText();
    }

    private void setupInputVerifier() {
        factory.getTextFieldFactory().getSystolicField().setInputVerifier(new UserInputVerifier());
        factory.getTextFieldFactory().getDiastolicField().setInputVerifier(new UserInputVerifier());
        factory.getTextFieldFactory().getPulseField().setInputVerifier(new UserInputVerifier());
    }

    private void handleSQLException(SQLException ex) {
        JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
    }

}
