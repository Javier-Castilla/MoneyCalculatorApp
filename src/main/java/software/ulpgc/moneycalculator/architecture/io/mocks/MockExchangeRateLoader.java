package software.ulpgc.moneycalculator.architecture.io.mocks;

import software.ulpgc.moneycalculator.architecture.io.ExchangeRateLoader;
import software.ulpgc.moneycalculator.architecture.model.Currency;
import software.ulpgc.moneycalculator.architecture.model.ExchangeRate;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class MockExchangeRateLoader implements ExchangeRateLoader {
    private final Map<String, Currency> currencyMap;

    public MockExchangeRateLoader(Map<String, Currency> currencyMap) {
        this.currencyMap = currencyMap;
    }

    @Override
    public Map<Currency, ExchangeRate> load() {
        return currencyMap.values().stream()
                .collect(Collectors.toMap(
                        c -> c,
                        c -> new ExchangeRate(LocalDate.now(), 1, currencyMap.get("EUR"), c)
                ));
    }

    @Override
    public Map<Currency, ExchangeRate> load(Map<Currency, ExchangeRate> exchangeRateMap) throws IOException {
        return Map.of();
    }

    @Override
    public Map<Currency, ExchangeRate> load(LocalDate date, Currency... currencies) throws IOException {
        return Arrays.stream(currencies)
                .collect(Collectors.toMap(
                        e -> e,
                        e -> new ExchangeRate(date, 1.5, currencyMap.get("EUR"), e)
                ));
    }

    @Override
    public Map<Currency, ExchangeRate> load(Map<Currency, ExchangeRate> exchangeRateMap, LocalDate date, Currency... currencies) throws IOException {
        return Map.of();
    }
}
