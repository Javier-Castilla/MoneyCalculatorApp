package software.ulpgc.moneycalculator.architecture.view;

import software.ulpgc.moneycalculator.architecture.model.Currency;

import java.util.List;

public interface CurrencyDialog {
    void define(List<Currency> currencies);

    void redefine(List<Currency> currencies);

    List<Currency> getDefinition();

    Currency get();

    OrderMode getOrderMode();

    OrderDirection getOrderDirection();

    Currency.CurrencyRepresentation getCurrencyRepresentation();

    void set(Currency currency);

    void setOrderMode(OrderMode orderMode);

    void setOrderDirection(OrderDirection orderDirection);

    void setCurrencyRepresentation(Currency.CurrencyRepresentation currencyRepresentation);

    enum OrderMode {
        NameOrder, CodeOrder
    }

    enum OrderDirection {
        AscendingOrder, DescendingOrder
    }
}
