package software.ulpgc.moneycalculator.architecture.io;

import software.ulpgc.moneycalculator.architecture.model.Currency;

import java.time.LocalDate;

public interface ExchangeRateReader {
    String read();
    String readOfDate(LocalDate date, Currency base, Currency... currencies);
}
