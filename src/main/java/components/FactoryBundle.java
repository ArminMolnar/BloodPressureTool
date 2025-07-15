package components;

public class FactoryBundle {
    private final ButtonFactory buttonFactory;
    private final LabelFactory labelFactory;
    private final PasswordFieldFactory passwordFieldFactory;
    private final TextFieldFactory textFieldFactory;

    public FactoryBundle(ButtonFactory buttonFactory, LabelFactory labelFactory, PasswordFieldFactory passwordFieldFactory, TextFieldFactory textFieldFactory) {
        this.buttonFactory = buttonFactory;
        this.labelFactory = labelFactory;
        this.passwordFieldFactory = passwordFieldFactory;
        this.textFieldFactory = textFieldFactory;
    }

    public ButtonFactory getButtonFactory() {
        return buttonFactory;
    }

    public LabelFactory getLabelFactory() {
        return labelFactory;
    }

    public PasswordFieldFactory getPasswordFieldFactory() {
        return passwordFieldFactory;
    }

    public TextFieldFactory getTextFieldFactory() {
        return textFieldFactory;
    }

}
