package software.ulpgc.moneycalculator.io;

import software.ulpgc.moneycalculator.model.Currency;
import software.ulpgc.moneycalculator.model.ExchangeRate;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;

public interface ExchangeRateLoader {
    Map<Currency, ExchangeRate> load() throws MalformedURLException;
}
