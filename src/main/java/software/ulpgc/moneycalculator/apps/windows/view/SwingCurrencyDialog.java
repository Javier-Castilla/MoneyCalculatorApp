package software.ulpgc.moneycalculator.apps.windows.view;

import software.ulpgc.moneycalculator.architecture.model.Currency;
import software.ulpgc.moneycalculator.architecture.view.CurrencyDialog;
import software.ulpgc.moneycalculator.apps.windows.view.customization.Colors;
import software.ulpgc.moneycalculator.apps.windows.view.customization.components.CustomSwingCurrencyRenderer;
import software.ulpgc.moneycalculator.apps.windows.view.customization.components.CustomSwingComboBox;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SwingCurrencyDialog extends JPanel implements CurrencyDialog {
    private CustomSwingComboBox<Currency> selector;
    private OrderMode orderMode;
    private OrderDirection orderDirection;

    public SwingCurrencyDialog() {
        this.setBackground(Colors.AlmostWhite.value());
        this.selector = new CustomSwingComboBox<>();
        this.orderMode = OrderMode.NameOrder;
        this.orderDirection = OrderDirection.DescendingOrder;
    }

    @Override
    public CurrencyDialog define(List<Currency> currencies) {
        add(addSelectorItems(currencies));
        setRenderer(Currency.CurrencyRepresentation.CurrencyName);
        return this;
    }

    public CurrencyDialog redefine(List<Currency> currencies) {
        Currency currentCurrency = get();
        selector.removeAllItems();
        addSelectorItems(currencies);
        selector.setSelectedItem(currentCurrency);
        return this;
    }

    @Override
    public List<Currency> getDefinition() {
        List<Currency> currencies = new ArrayList<>();
        for (int i = 0; i < selector.getItemCount(); i++)
            currencies.add(selector.getItemAt(i));
        return currencies;
    }

    private CustomSwingComboBox<Currency> addSelectorItems(List<Currency> currencies) {
        currencies.forEach(c -> selector.addItem(c));
        return selector;
    }

    public SwingCurrencyDialog setRenderer(Currency.CurrencyRepresentation representation) {
        selector.setRenderer(new CustomSwingCurrencyRenderer(Currency.getMethodsMap().get(representation)));
        return this;
    }

    public JComboBox<Currency> getSelector() {
        return selector;
    }

    @Override
    public Currency get() {
        return selector.getItemAt(selector.getSelectedIndex());
    }

    @Override
    public OrderMode getOrderMode() {
        return orderMode;
    }

    @Override
    public OrderDirection getOrderDirection() {
        return orderDirection;
    }

    @Override
    public CurrencyDialog set(Currency currency) {
        selector.setSelectedItem(currency);
        return this;
    }

    @Override
    public CurrencyDialog setOrderMode(OrderMode orderMode) {
        this.orderMode = orderMode;
        return this;
    }

    @Override
    public CurrencyDialog setOrderDirection(OrderDirection orderDirection) {
        this.orderDirection = orderDirection;
        return this;
    }

    @Override
    public boolean isFocused() {
        return this.selector.hasFocus();
    }
}
