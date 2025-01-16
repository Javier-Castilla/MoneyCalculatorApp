package software.ulpgc.moneycalculator.apps.windows.view.customization.components;

import software.ulpgc.moneycalculator.apps.windows.view.customization.Colors;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;

public class CustomSwingComboBox<E> extends JComboBox<E> {
    public CustomSwingComboBox() {
        setBorder(BorderFactory.createEmptyBorder());
        setBackground(Colors.AlmostWhite.value());
    }
}
