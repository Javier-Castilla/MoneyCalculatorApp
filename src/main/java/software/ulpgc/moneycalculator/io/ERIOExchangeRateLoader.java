package software.ulpgc.moneycalculator.io;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import software.ulpgc.moneycalculator.model.Currency;
import software.ulpgc.moneycalculator.model.ExchangeRate;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class ERIOExchangeRateLoader implements ExchangeRateLoader {
    private final Map<String, Currency> currencies;

    public ERIOExchangeRateLoader(Map<String, Currency> currencies) {
        this.currencies = currencies;
    }

    @Override
    public Map<Currency, ExchangeRate> load() throws MalformedURLException {
        return exchangeRateMap(loadJson());
    }

    private Map<Currency, ExchangeRate> exchangeRateMap(String json) {
        Map<Currency, ExchangeRate> exchangeRates = new HashMap<>();
        String baseCurrency = new Gson().fromJson(json, JsonObject.class).get("base").getAsString();
        Map<String, JsonElement> exchanges = new Gson()
                .fromJson(json, JsonObject.class)
                .getAsJsonObject("rates")
                .asMap();
        for (String code : exchanges.keySet()) {
            exchangeRates.put(currencies.get(code), new ExchangeRate(LocalDate.now(), exchanges.get(code).getAsDouble(), currencies.get(baseCurrency), currencies.get(code)));
        }
        return exchangeRates;
    }

    private String loadJson() throws MalformedURLException {
        URL url = new URL(ERIOAPI.EXCHANGE_RATES + ERIOAPI.KEY);
        try (InputStream is = url.openStream()) {
            return new String(is.readAllBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
