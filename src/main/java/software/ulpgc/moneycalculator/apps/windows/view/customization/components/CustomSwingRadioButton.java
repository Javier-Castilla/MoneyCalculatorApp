package software.ulpgc.moneycalculator.apps.windows.view.customization.components;

import software.ulpgc.moneycalculator.apps.windows.view.customization.Colors;

import javax.swing.*;

public class CustomSwingRadioButton extends JRadioButton {
    public CustomSwingRadioButton(String text) {
        setText(text);
        setBackground(Colors.AlmostBlack.value());
        setForeground(Colors.AlmostWhite.value());
    }
}
