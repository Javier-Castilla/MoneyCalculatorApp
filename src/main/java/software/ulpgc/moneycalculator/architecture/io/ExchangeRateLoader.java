package software.ulpgc.moneycalculator.architecture.io;

import software.ulpgc.moneycalculator.architecture.model.Currency;
import software.ulpgc.moneycalculator.architecture.model.ExchangeRate;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.Map;

public interface ExchangeRateLoader {
    Map<Currency, ExchangeRate> load() throws IOException;
    Map<Currency, ExchangeRate> load(Map<Currency, ExchangeRate> exchangeRateMap) throws IOException;
    Map<Currency, ExchangeRate> load(LocalDate date, Currency... currencies) throws IOException;
    Map<Currency, ExchangeRate> load(Map<Currency, ExchangeRate> exchangeRateMap, LocalDate date, Currency... currencies) throws IOException;
}
