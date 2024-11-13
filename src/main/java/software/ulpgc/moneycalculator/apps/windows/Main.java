package software.ulpgc.moneycalculator.apps.windows;

import software.ulpgc.moneycalculator.io.ERIOCurrencyLoader;
import software.ulpgc.moneycalculator.io.ERIOExchangeRateLoader;
import software.ulpgc.moneycalculator.io.ExchangeRateLoader;
import software.ulpgc.moneycalculator.model.Currency;
import software.ulpgc.moneycalculator.model.ExchangeRate;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception {
        Map<String, Currency> currencies = new ERIOCurrencyLoader().load();
        System.out.println(currencies);
        List<ExchangeRate> exchanges = new ERIOExchangeRateLoader(currencies).load();
        System.out.println(exchanges);
    }
}
