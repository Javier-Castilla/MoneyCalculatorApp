package software.ulpgc.moneycalculator.apps.windows.view.customization.components;

import software.ulpgc.moneycalculator.apps.windows.view.customization.Colors;

import javax.swing.*;
import java.awt.*;

public class CustomSwingSeparator extends JSeparator {
    public CustomSwingSeparator() {
        setMinimumSize(new Dimension(250, 10));
        setPreferredSize(new Dimension(250, 10));
        setBackground(Colors.LightGray.value());
        setForeground(Colors.AlmostBlack.value());
    }
}
