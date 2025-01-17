package software.ulpgc.moneycalculator.apps.windows.io.erioapi;

import com.google.gson.*;
import software.ulpgc.moneycalculator.apps.windows.io.pojo.CurrencyGetResponse;
import software.ulpgc.moneycalculator.architecture.io.CurrencyLoader;
import software.ulpgc.moneycalculator.architecture.model.Currency;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Map;
import java.util.stream.Collectors;

public class ERIOCurrencyLoader implements CurrencyLoader {
    @Override
    public Map<String, Currency> load() throws IOException {
        return currenciesMap(loadJson());
    }

    private Map<String, Currency> currenciesMap(String json) {
        return new Gson().fromJson(json, CurrencyGetResponse.class).symbols()
                .entrySet().stream()
                .collect(Collectors.toMap(
                        e -> e.getKey(),
                        e -> new Currency(e.getKey(), e.getValue(),  null)
                ));
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
