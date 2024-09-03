package ipc1.lab.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class Input extends JPanel {
    JTextField textField;

    public Input(String label) {
        GridLayout gridLayout = new GridLayout(2, 1, 0, 4);
        setLayout(gridLayout);
        setBackground(Pallete.background);

        JLabel inputLabel = new JLabel(label, SwingConstants.LEFT);
        inputLabel.setFont(Fonts.input);
        inputLabel.setForeground(Pallete.text);
        add(inputLabel);

        textField = new JTextField();
        textField.setFont(Fonts.normal);
        textField.setForeground(Pallete.text);
        textField.setBorder(
                BorderFactory.createCompoundBorder(
                        new LineBorder(Pallete.text, 2), new EmptyBorder(0, 16, 0, 16)
                )
        );
        add(textField);
    }

    public Input(String label, String placeholder) {
        GridLayout gridLayout = new GridLayout(2, 1, 0, 4);
        setLayout(gridLayout);
        setBackground(Pallete.background);

        JLabel inputLabel = new JLabel(label, SwingConstants.LEFT);
        inputLabel.setFont(Fonts.input);
        inputLabel.setForeground(Pallete.text);
        add(inputLabel);

        textField = new JTextField(placeholder);
        textField.setFont(Fonts.normal);
        textField.setForeground(Pallete.text);
        textField.setBorder(
                BorderFactory.createCompoundBorder(
                        new LineBorder(Pallete.text, 2), new EmptyBorder(0, 16, 0, 16)
                )
        );
        add(textField);
    }

    public String getText() {
        return this.textField.getText();
    }
}
