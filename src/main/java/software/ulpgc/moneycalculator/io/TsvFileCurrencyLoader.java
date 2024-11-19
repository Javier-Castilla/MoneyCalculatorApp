package software.ulpgc.moneycalculator.io;

import software.ulpgc.moneycalculator.model.Currency;

import java.io.*;
import java.util.*;

public class TsvFileCurrencyLoader implements CurrencyLoader {
    private final File file;
    private final CurrencyDeserializer deserializer;

    public TsvFileCurrencyLoader(File file, CurrencyDeserializer deserializer) {
        this.file = file;
        this.deserializer = deserializer;
    }

    @Override
    public Map<String, Currency> load() throws IOException {
        return currencyMap();
    }

    private Map<String, Currency> currencyMap() {
        return read();
    }

    private Map<String, Currency> read() {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            return readWith(reader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Map<String, Currency> readWith(BufferedReader reader) throws IOException {
        Map<String, Currency> currencies = new HashMap<>();
        String line;
        while ((line = reader.readLine()) != null) {
            Currency currency = deserializer.deserialize(line);
            currencies.put(currency.code(), currency);
        }
        return currencies;
    }
}
