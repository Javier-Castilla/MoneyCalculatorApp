package software.ulpgc.view;

import software.ulpgc.moneycalculator.model.Currency;
import software.ulpgc.moneycalculator.model.Money;

import java.util.Map;

public interface MoneyDialog {
    Money get();

    MoneyDialog define(Map<String, Currency> currencies);
}
