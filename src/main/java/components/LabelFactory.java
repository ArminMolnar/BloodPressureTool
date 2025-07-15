package components;

import javax.swing.*;
import java.awt.*;

public class LabelFactory {

    private final JLabel nameLabel;
    private final JLabel passwordLabel;
    private final JLabel averageLabel;
    private final JLabel systolicLabel;
    private final JLabel diastolicLabel;
    private final JLabel pulseLabel;
    private final JLabel avgResultLabel;
    private static LabelFactory instance;

    private LabelFactory() {
        this.nameLabel = createLabel("Name:", 135, 75, 200, 30, new Font("Times New Roman", Font.BOLD, 20));
        this.passwordLabel = createLabel("Password:", 135, 75, 200, 30, new Font("Times New Roman", Font.BOLD, 20));
        this.averageLabel = createLabel("Average:", 200, 320, 600, 30, new Font("Times New Roman", Font.BOLD, 14));
        this.avgResultLabel = createLabel("", 20, 340, 600, 30, new Font("Times New Roman", Font.BOLD, 14));
        this.systolicLabel = createLabel("Systolic", 85, 75, 100, 30, new Font("Times New Roman", Font.BOLD, 14));
        this.diastolicLabel = createLabel("Diastolic", 160, 75, 100, 30, new Font("Times New Roman", Font.BOLD, 14));
        this.pulseLabel = createLabel("Pulse", 250, 75, 100, 30, new Font("Times New Roman", Font.BOLD, 14));
    }

    private static JLabel createLabel(String text, int x, int y, int width, int height, Font font) {

        JLabel label = new JLabel(text);
        label.setText(text);
        label.setBounds(x, y, width, height);
        label.setFont(font);
        label.setForeground(Color.WHITE);
        return label;
    }

    public JLabel getNameLabel() {
        return nameLabel;
    }

    public JLabel getAverageLabel() {
        return averageLabel;
    }

    public JLabel getSystolicLabel() {
        return systolicLabel;
    }

    public JLabel getDiastolicLabel() {
        return diastolicLabel;
    }

    public JLabel getPulseLabel() {
        return pulseLabel;
    }

    public JLabel getPasswordLabel() {
        return passwordLabel;
    }

    public JLabel getAvgResultLabel() {
        return avgResultLabel;
    }

    public static LabelFactory getInstance() {
        if (instance == null) {
            return new LabelFactory();
        }
        return instance;
    }

}
