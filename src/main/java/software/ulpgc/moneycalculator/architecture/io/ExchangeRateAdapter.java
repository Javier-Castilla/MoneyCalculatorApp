package software.ulpgc.moneycalculator.architecture.io;

import software.ulpgc.moneycalculator.architecture.model.Currency;
import software.ulpgc.moneycalculator.architecture.model.ExchangeRate;

import java.util.Map;

public interface ExchangeRateAdapter {
    Map<Currency, ExchangeRate> adaptToMap(Object object);
    ExchangeRate adapt(ExchangeRate fromExchangeRate, ExchangeRate toExchangeRate);
}
