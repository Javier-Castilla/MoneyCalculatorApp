package software.ulpgc.view;

import software.ulpgc.moneycalculator.model.Currency;

import javax.swing.*;
import java.util.Map;
import java.util.function.Function;

public class SwingCurrencyDialog extends JPanel implements CurrencyDialog {
    private JComboBox<Currency> selector;

    @Override
    public CurrencyDialog define(Map<String, Currency> currencies) {
        add(this.selector = createCurrencySelector(currencies));
        return this;
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
        return addSelectorItems(currencyCombo, currencies);
    }

    @Override
    public Currency get() {
        return selector.getItemAt(selector.getSelectedIndex());
    }
}
