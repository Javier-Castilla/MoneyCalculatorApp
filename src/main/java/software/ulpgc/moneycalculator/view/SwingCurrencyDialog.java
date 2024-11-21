package software.ulpgc.moneycalculator.view;

import software.ulpgc.moneycalculator.model.Currency;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class SwingCurrencyDialog extends JPanel implements CurrencyDialog {
    private JComboBox<Currency> selector;
    private JLabel label;

    @Override
    public CurrencyDialog define(String text, Map<String, Currency> currencies) {
        add(this.label= createCurrencySelectorLabel(text));
        add(this.selector = createCurrencySelector(currencies));
        return this;
    }

    private JLabel createCurrencySelectorLabel(String text) {
        JLabel label = new JLabel(text);
        label.setPreferredSize(new Dimension(35, 20));
        label.setMaximumSize(new Dimension(35, 20));
        return label;
    }

    public CurrencyDialog redefine(Map<String, Currency> currencies) {
        selector.removeAllItems();
        addSelectorItems(selector, currencies);
        return this;
    }

    private JComboBox<Currency> addSelectorItems(JComboBox<Currency> selector, Map<String, Currency> currencies) {
        for (Currency currency : currencies.values())
            selector.addItem(currency);
        return selector;
    }

    public SwingCurrencyDialog setRenderer(Currency.CurrencyRepresentation representation) {
        selector.setRenderer(new CurrencyRenderer(Currency.getMethodsMap().get(representation)));
        return this;
    }

    private JComboBox<Currency> createCurrencySelector(Map<String, Currency> currencies) {
        JComboBox<Currency> currencyCombo = new JComboBox<>();
        currencyCombo.setBackground(Color.WHITE);
        return addSelectorItems(currencyCombo, currencies);
    }

    public JComboBox<Currency> getSelector() {
        return selector;
    }

    public JLabel getLabel() {
        return label;
    }

    @Override
    public Currency get() {
        return selector.getItemAt(selector.getSelectedIndex());
    }

    @Override
    public CurrencyDialog set(Currency currency) {
        selector.setSelectedItem(currency);
        return this;
    }
}
