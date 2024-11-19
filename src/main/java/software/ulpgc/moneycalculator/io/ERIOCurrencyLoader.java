package software.ulpgc.moneycalculator.io;

import com.google.gson.*;
import software.ulpgc.moneycalculator.model.Currency;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class ERIOCurrencyLoader implements CurrencyLoader {
    private final static String KEY = "8ebc93a9925d938d5b813e1896a2141d";

    @Override
    public Map<String, Currency> load() throws IOException {
        return currenciesMap(loadJson());
    }

    private Map<String, Currency> currenciesMap(String json) {
        Map<String, Currency> currencies = new HashMap<>();
        Map<String, JsonElement> symbols = new Gson().fromJson(json, JsonObject.class).getAsJsonObject("symbols").asMap();
        for (String symbol : symbols.keySet()) {
            currencies.put(symbol, new Currency(symbol, symbols.get(symbol).getAsString(), null));
        }
        return currencies;
    }

    private String loadJson() throws IOException {
        URL url = new URL(ERIOAPI.SYMBOLS + ERIOAPI.KEY);
        try (InputStream is = url.openStream()) {
            return new String(is.readAllBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
