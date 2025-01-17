package software.ulpgc.moneycalculator.apps.windows.view.customization.components;

import software.ulpgc.moneycalculator.apps.windows.view.customization.Colors;

import javax.swing.*;

public class CustomSwingScrollPane extends JScrollPane {
    public CustomSwingScrollPane() {
        super();
        JScrollBar verticalScrollBar = getVerticalScrollBar();
        verticalScrollBar.setBackground(Colors.HoverBlack.value());
        verticalScrollBar.setUI(new CustomSwingScrollBarUI());
    }
}
