package software.ulpgc.moneycalculator.apps.windows.view.customization.components;

import software.ulpgc.moneycalculator.apps.windows.view.customization.Colors;
import software.ulpgc.moneycalculator.apps.windows.view.customization.filters.NumericFilter;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import java.awt.*;

public class SwingAmountEntry extends JTextField {
    public SwingAmountEntry() {
        setColumns(15);
        setText("0.00");
        setBackground(Colors.AlmostWhite.value());
        setForeground(Colors.AlmostBlack.value());
        setBorder(null);
        setHorizontalAlignment(RIGHT);
        //setMaximumSize(new Dimension(240, 20));
        ((AbstractDocument) getDocument()).setDocumentFilter(new NumericFilter());
    }
}
