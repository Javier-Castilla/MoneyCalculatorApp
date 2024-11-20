package software.ulpgc.moneycalculator.mocks;

import software.ulpgc.moneycalculator.io.ExchangeRateLoader;
import software.ulpgc.moneycalculator.model.Currency;
import software.ulpgc.moneycalculator.model.ExchangeRate;

import java.net.MalformedURLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class MockExchangeRateLoader implements ExchangeRateLoader {
    private final Map<String, Currency> currencies;

    public MockExchangeRateLoader(Map<String, Currency> currencies) {
        this.currencies = currencies;
    }

    @Override
    public Map<Currency, ExchangeRate> load() throws MalformedURLException {
        Map<Currency, ExchangeRate> result = new HashMap<>();
        for (Currency currency : currencies.values()) {
            result.put(currency, new ExchangeRate(LocalDate.now(), 1, currency, currency));
        }
        return result;
    }
}
