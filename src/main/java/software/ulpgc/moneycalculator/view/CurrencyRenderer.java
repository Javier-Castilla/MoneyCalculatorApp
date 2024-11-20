package software.ulpgc.moneycalculator.view;

import software.ulpgc.moneycalculator.model.Currency;

import javax.swing.*;
import java.awt.*;
import java.util.function.Function;

public class CurrencyRenderer extends JLabel implements ListCellRenderer<Currency> {
    private final Function<Currency, String> textFunction;

    public CurrencyRenderer(Function<Currency, String> textFunction) {
        this.textFunction = textFunction;
        setOpaque(true);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Currency> list, Currency value, int index, boolean isSelected, boolean cellHasFocus) {
        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }

        setText(textFunction.apply(value));
        return this;
    }
}
