package software.ulpgc.moneycalculator.io;

import software.ulpgc.moneycalculator.model.Currency;

import java.io.IOException;
import java.util.Map;

public interface CurrencyLoader {
    Map<String, Currency> load() throws IOException;
}
