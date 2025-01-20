package software.ulpgc.moneycalculator.apps.windows.io;

import software.ulpgc.moneycalculator.architecture.io.CurrencyAdapter;
import software.ulpgc.moneycalculator.architecture.io.CurrencyDeserializer;
import software.ulpgc.moneycalculator.architecture.io.CurrencyLoader;
import software.ulpgc.moneycalculator.architecture.io.FileReader;
import software.ulpgc.moneycalculator.architecture.model.Currency;

import java.io.IOException;
import java.util.Map;

public class JsonFileCurrencyLoader implements CurrencyLoader {
    private final FileReader fileReader;
    private final CurrencyDeserializer deserializer;
    private final CurrencyAdapter adapter;

    public JsonFileCurrencyLoader(FileReader fileReader, CurrencyDeserializer deserializer, CurrencyAdapter adapter) {
        this.fileReader = fileReader;
        this.deserializer = deserializer;
        this.adapter = adapter;
    }

    @Override
    public Map<String, Currency> load() throws IOException {
        return adapter.adaptToMap(deserializer.deserialize(fileReader.read()));
    }
}
