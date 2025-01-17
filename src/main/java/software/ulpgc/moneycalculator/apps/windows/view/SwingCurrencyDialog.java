package software.ulpgc.moneycalculator.apps.windows.view;

import software.ulpgc.moneycalculator.architecture.model.Currency;
import software.ulpgc.moneycalculator.architecture.view.CurrencyDialog;
import software.ulpgc.moneycalculator.apps.windows.view.customization.Colors;
import software.ulpgc.moneycalculator.apps.windows.view.customization.components.CustomSwingCurrencyRenderer;
import software.ulpgc.moneycalculator.apps.windows.view.customization.components.CustomSwingComboBox;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class SwingCurrencyDialog extends JPanel implements CurrencyDialog {
    private CustomSwingComboBox<Currency> selector;
    private OrderMode orderMode;
    private OrderDirection orderDirection;
    private Currency.CurrencyRepresentation currencyRepresentation;

    public SwingCurrencyDialog() {
        this.setBackground(Colors.AlmostWhite.value());
        this.selector = new CustomSwingComboBox<>();
        this.orderMode = OrderMode.NameOrder;
        this.orderDirection = OrderDirection.DescendingOrder;
        this.currencyRepresentation = Currency.CurrencyRepresentation.CurrencyName;
    }

    @Override
    public void define(List<Currency> currencies) {
        removeAll();
        add(addSelectorItems(currencies));
        setRenderer(Currency.CurrencyRepresentation.CurrencyName);
    }

    public void redefine(List<Currency> currencies) {
        Currency currentCurrency = get();
        selector.removeAllItems();
        addSelectorItems(currencies);
        selector.setSelectedItem(currentCurrency);
    }

    @Override
    public List<Currency> getDefinition() {
        List<Currency> currencies = new ArrayList<>();
        for (int i = 0; i < selector.getItemCount(); i++)
            currencies.add(selector.getItemAt(i));
        return currencies;
    }

    private CustomSwingComboBox<Currency> addSelectorItems(List<Currency> currencies) {
        selector.removeAllItems();
        currencies.forEach(selector::addItem);
        return selector;
    }

    public void setRenderer(Currency.CurrencyRepresentation representation) {
        selector.setRenderer(new CustomSwingCurrencyRenderer(Currency.getMethodsMap().get(representation)));
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
    public Currency.CurrencyRepresentation getCurrencyRepresentation() {
        return currencyRepresentation;
    }

    @Override
    public void set(Currency currency) {
        selector.setSelectedItem(currency);
    }

    @Override
    public void setOrderMode(OrderMode orderMode) {
        this.orderMode = orderMode;
    }

    @Override
    public void setOrderDirection(OrderDirection orderDirection) {
        this.orderDirection = orderDirection;
    }

    @Override
    public void setCurrencyRepresentation(Currency.CurrencyRepresentation currencyRepresentation) {
        this.currencyRepresentation = currencyRepresentation;
    }

}
