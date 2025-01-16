package software.ulpgc.moneycalculator.architecture.view;

import software.ulpgc.moneycalculator.architecture.model.Currency;

import java.util.List;

public interface CurrencyDialog {
    CurrencyDialog define(List<Currency> currencies);
    CurrencyDialog redefine(List<Currency> currencies);
    List<Currency> getDefinition();
    Currency get();
    OrderMode getOrderMode();
    OrderDirection getOrderDirection();
    CurrencyDialog set(Currency currency);
    CurrencyDialog setOrderMode(OrderMode orderMode);
    CurrencyDialog setOrderDirection(OrderDirection orderDirection);
    boolean isFocused();

    enum OrderMode {
        NameOrder, CodeOrder
    }

    enum OrderDirection {
        AscendingOrder, DescendingOrder
    }
}
