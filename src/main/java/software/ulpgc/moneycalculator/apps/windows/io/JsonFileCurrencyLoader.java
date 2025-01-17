package software.ulpgc.moneycalculator.apps.windows.io;

import software.ulpgc.moneycalculator.architecture.io.CurrencyAdapter;
import software.ulpgc.moneycalculator.architecture.io.CurrencyDeserializer;
import software.ulpgc.moneycalculator.architecture.io.CurrencyLoader;
import software.ulpgc.moneycalculator.architecture.io.CurrencyReader;
import software.ulpgc.moneycalculator.architecture.model.Currency;

import java.io.IOException;
import java.util.Map;

public class JsonFileCurrencyLoader implements CurrencyLoader {
    private final CurrencyReader reader;
    private final CurrencyDeserializer deserializer;
    private final CurrencyAdapter adapter;

    public JsonFileCurrencyLoader(CurrencyReader reader, CurrencyDeserializer deserializer, CurrencyAdapter adapter) {
        this.reader = reader;
        this.deserializer = deserializer;
        this.adapter = adapter;
    }

    @Override
    public Map<String, Currency> load() throws IOException {
        return adapter.adaptToMap(deserializer.deserialize(reader.read()));
    }
}
