package software.ulpgc.moneycalculator.apps.windows.io;

import software.ulpgc.moneycalculator.apps.windows.io.pojo.CurrencyGetResponse;
import software.ulpgc.moneycalculator.architecture.io.CurrencyAdapter;
import software.ulpgc.moneycalculator.architecture.model.Currency;

import java.util.Map;
import java.util.stream.Collectors;

public class JsonFileCurrencyAdapter implements CurrencyAdapter {
    @Override
    public Map<String, Currency> adaptToMap(Object response) {
        return ((CurrencyGetResponse) response).symbols().entrySet().stream()
                .collect(
                        Collectors.toMap(
                                Map.Entry::getKey,
                                e -> new Currency(e.getKey(), e.getValue(), null)
                        )
                );
    }
}
