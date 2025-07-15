package buttonController;

import javax.swing.*;

public class UserInputVerifier extends InputVerifier {


    @Override
    public boolean verify(JComponent input) {
        String text = ((JTextField) input).getText();
        try {
            Integer.parseInt(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public boolean shouldYieldFocus(JComponent input) {
        return true;
    }
}
