package components;

import javax.swing.*;
import java.awt.*;

public class ButtonFactory {

    private final JButton testConnectionButton;
    private final JButton addRecordButton;
    private final JButton displayRecordButton;
    private final JButton displayAverageButton;
    private final JButton OkButton;
    private static ButtonFactory instance;
    private final JButton returnButton;

    private ButtonFactory() {
        this.testConnectionButton = ButtonFactory.createButton("Test connection", 80, 150, 230, 30);
        this.addRecordButton = ButtonFactory.createButton("Add record", 80, 160, 230, 30);
        this.displayRecordButton = ButtonFactory.createButton("Display record", 80, 220, 230, 30);
        this.displayAverageButton = ButtonFactory.createButton("Display average", 80, 280, 230, 30);
        this.OkButton = ButtonFactory.createButton("OK", 80, 150, 230, 30);
        this.returnButton = ButtonFactory.createButton("Return", 240, 40, 120, 30);

    }

    private static JButton createButton(String text, int x, int y, int width, int height) {

        JButton button = new JButton(text);
        button.setBounds(x, y, width, height);
        button.setFont(new Font("Times New Roman", Font.BOLD, 17));
        button.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
        button.setForeground(Color.white);
        button.setBackground(Color.black);
        return button;
    }


    public JButton getTestConnectionButton() {
        return testConnectionButton;
    }

    public JButton getDisplayAverageButton() {
        return displayAverageButton;
    }

    public JButton getDisplayRecordButton() {
        return displayRecordButton;
    }

    public JButton getAddRecordButton() {
        return addRecordButton;
    }

    public JButton getOkButton() {
        return OkButton;
    }
    public JButton getReturnButton() {
        return returnButton;
    }

    public static ButtonFactory getInstance() {
        if (instance == null) {
            instance = new ButtonFactory();
        }
        return instance;
    }

}
