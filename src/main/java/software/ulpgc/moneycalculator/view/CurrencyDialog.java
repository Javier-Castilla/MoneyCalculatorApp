package software.ulpgc.moneycalculator.view;

import software.ulpgc.moneycalculator.model.Currency;

import java.util.Map;

public interface CurrencyDialog {
    CurrencyDialog define(String text, Map<String, Currency> currencies);
    CurrencyDialog redefine(Map<String, Currency> currencies);
    Currency get();

    CurrencyDialog set(Currency currency);
}
