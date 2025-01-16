package software.ulpgc.moneycalculator.apps.windows.view.customization.components;

import software.ulpgc.moneycalculator.apps.windows.view.customization.Colors;
import software.ulpgc.moneycalculator.apps.windows.view.customization.filters.DateFilter;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import java.awt.*;

public class SwingDateEntry extends JTextField {
    public SwingDateEntry() {
        setColumns(10);
        setText("dd-mm-yyyy");
        setBackground(Colors.AlmostBlack.value());
        setForeground(Colors.AlmostWhite.value());
        setCaretColor(Colors.AlmostWhite.value());
        // setBorder(BorderFactory.createLineBorder(Colors.AlmostWhite.value(), 1));
        setBorder(null);
        setMaximumSize(new Dimension(100, 70));
        ((AbstractDocument) getDocument()).setDocumentFilter(new DateFilter());
    }
}
