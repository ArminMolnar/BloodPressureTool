package components;

import buttonController.ButtonEventHandler;
import controller.Controller;
import service.Calculator;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class FrameFactory {

    private static final ButtonFactory buttonFactory = ButtonFactory.getInstance();
    private static final TextFieldFactory textFieldFactory = TextFieldFactory.getInstance();
    private static final LabelFactory labelFactory = LabelFactory.getInstance();
    private static final Controller controller = Controller.getInstance();
    private static final PasswordFieldFactory passwordFieldFactory = PasswordFieldFactory.getInstance();
    private static final Calculator calculator = new Calculator(labelFactory);


    public void createMainFrame() {

        JFrame mainFrame = new JFrame();
        ImageIcon backgroundImage = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/TokyoGhoul.jpg")));
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setSize(500, 550);

        FactoryBundle factory = new FactoryBundle(buttonFactory, labelFactory, passwordFieldFactory, textFieldFactory);
        ButtonEventHandler buttonEventHandler = new ButtonEventHandler(mainFrame, controller, calculator, factory);

        buttonFactory.getTestConnectionButton().addActionListener(buttonEventHandler);
        buttonFactory.getOkButton().addActionListener(buttonEventHandler);
        buttonFactory.getAddRecordButton().addActionListener(buttonEventHandler);
        buttonFactory.getDisplayRecordButton().addActionListener(buttonEventHandler);
        buttonFactory.getDisplayAverageButton().addActionListener(buttonEventHandler);
        buttonFactory.getReturnButton().addActionListener(buttonEventHandler);

        mainFrame.setTitle("Blood Pressure Log");
        mainFrame.setSize(500, 550);

        mainFrame.getContentPane().setFont(new Font("Times New Roman", Font.BOLD, 20));
        mainFrame.setLayout(null);
        mainFrame.setContentPane(backgroundLabel);
        mainFrame.setVisible(true);
        mainFrame.setResizable(false);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainFrame.add(buttonFactory.getTestConnectionButton());
        mainFrame.add(labelFactory.getPasswordLabel());
        mainFrame.add(passwordFieldFactory.getPasswordField());

    }

}
