package buttonController;

import components.*;

import javax.swing.*;

public class ButtonManager {

    private final JFrame frame;
    private final FactoryBundle factory;

    public ButtonManager(JFrame frame, FactoryBundle factory) {
        this.frame = frame;
        this.factory = factory;
    }

    public void addButtons() {
        frame.add(factory.getButtonFactory().getAddRecordButton());
        frame.add(factory.getButtonFactory().getDisplayRecordButton());
        frame.add(factory.getButtonFactory().getDisplayAverageButton());

        frame.add(factory.getTextFieldFactory().getSystolicField());
        frame.add(factory.getTextFieldFactory().getDiastolicField());
        frame.add(factory.getTextFieldFactory().getPulseField());

        frame.add(factory.getLabelFactory().getSystolicLabel());
        frame.add(factory.getLabelFactory().getDiastolicLabel());
        frame.add(factory.getLabelFactory().getPulseLabel());
        frame.add(factory.getButtonFactory().getReturnButton());
    }

    public void addAverageData() {
        frame.add(factory.getLabelFactory().getAverageLabel());
        frame.add(factory.getLabelFactory().getAvgResultLabel());
    }

    public void clearTextField() {
        factory.getPasswordFieldFactory().getPasswordField().setText("");
        factory.getTextFieldFactory().getSystolicField().setText("");
        factory.getTextFieldFactory().getDiastolicField().setText("");
        factory.getTextFieldFactory().getPulseField().setText("");
    }

    public void refreshFrame() {
        frame.revalidate();
        frame.repaint();
    }

    public void setupUserSelection() {
        frame.getContentPane().removeAll();
        frame.add(factory.getTextFieldFactory().getNameTextField());
        frame.add(factory.getButtonFactory().getOkButton());
        frame.add(factory.getLabelFactory().getNameLabel());
        refreshFrame();
    }

}
