package software.ulpgc.moneycalculator.apps.windows.io;

import software.ulpgc.moneycalculator.apps.windows.io.erioapi.ERIOAPI;
import software.ulpgc.moneycalculator.architecture.io.ExchangeRateReader;
import software.ulpgc.moneycalculator.architecture.model.Currency;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDate;

public class ERIOExchangeRateReader implements ExchangeRateReader {
    @Override
    public String read() {
        return read(ERIOAPI.CURRENT_EXCHANGE_RATES + ERIOAPI.KEY);
    }

    @Override
    public String readOfDate(LocalDate date, Currency base, Currency... currencies) {
        if (currencies.length == 0) return read(ERIOAPI.exchangeRatesOfDate(date)+ ERIOAPI.KEY);
        else return read(ERIOAPI.exchangeRatesOfDate(date) + ERIOAPI.exchangeRatesFrom(base, currencies)+ ERIOAPI.KEY);
    }

    private String read(String urlRequest) {
        try (InputStream is = new URL(urlRequest).openStream()) {
            return new String(is.readAllBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
