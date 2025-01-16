package software.ulpgc.moneycalculator.architecture.io;

import software.ulpgc.moneycalculator.architecture.model.Currency;

import java.util.Map;

public interface CurrencyAdapter {
    Map<String, Currency> adaptToMap(Object response);
}
