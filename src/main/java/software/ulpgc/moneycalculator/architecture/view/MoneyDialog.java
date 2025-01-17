package software.ulpgc.moneycalculator.architecture.view;

import software.ulpgc.moneycalculator.architecture.model.Currency;
import software.ulpgc.moneycalculator.architecture.model.Money;

import java.util.List;

public interface MoneyDialog {
    Money get();

    void define(List<Currency> currencies);

    CurrencyDialog getCurrencyDialog();

    void set(Money money);

    boolean isFocused();
}
