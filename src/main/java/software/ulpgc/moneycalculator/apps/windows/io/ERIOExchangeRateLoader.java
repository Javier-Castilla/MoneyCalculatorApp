package software.ulpgc.moneycalculator.apps.windows.io;

import software.ulpgc.moneycalculator.architecture.io.ExchangeRateAdapter;
import software.ulpgc.moneycalculator.architecture.io.ExchangeRateDeserializer;
import software.ulpgc.moneycalculator.architecture.io.ExchangeRateLoader;
import software.ulpgc.moneycalculator.architecture.io.ExchangeRateReader;
import software.ulpgc.moneycalculator.architecture.model.Currency;
import software.ulpgc.moneycalculator.architecture.model.ExchangeRate;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;

public class ERIOExchangeRateLoader implements ExchangeRateLoader {
    private final ExchangeRateReader reader;
    private final ExchangeRateDeserializer deserializer;
    private final ExchangeRateAdapter adapter;
    private final Map<String, Currency> currencyMap;

    public ERIOExchangeRateLoader(ExchangeRateReader reader, ExchangeRateDeserializer deserializer, ExchangeRateAdapter adapter, Map<String, Currency> currencyMap) {
        this.reader = reader;
        this.deserializer = deserializer;
        this.adapter = adapter;
        this.currencyMap = currencyMap;
    }

    @Override
    public Map<Currency, ExchangeRate> load() throws IOException {
        return adapter.adaptToMap(deserializer.deserialize(reader.read()));
    }

    @Override
    public Map<Currency, ExchangeRate> load(Map<Currency, ExchangeRate> exchangeRateMap) throws IOException {
        exchangeRateMap.clear();
        exchangeRateMap.putAll(load());
        return exchangeRateMap;
    }

    @Override
    public Map<Currency, ExchangeRate> load(LocalDate date, Currency... currencies) throws IOException {
        return adapter.adaptToMap(deserializer.deserialize(reader.readOfDate(date, currencyMap.get("EUR"), currencies)));
    }

    @Override
    public Map<Currency, ExchangeRate> load(Map<Currency, ExchangeRate> exchangeRateMap, LocalDate date, Currency... currencies) throws IOException {
        exchangeRateMap.clear();
        exchangeRateMap.putAll(load(date, currencies));
        return exchangeRateMap;
    }
}
