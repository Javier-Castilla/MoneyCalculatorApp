package software.ulpgc.view;

import software.ulpgc.moneycalculator.model.Currency;

import java.util.Map;
import java.util.function.Function;

public interface CurrencyDialog {
    CurrencyDialog define(Map<String, Currency> currencies);
    CurrencyDialog redefine(Map<String, Currency> currencies);
    Currency get();
}
