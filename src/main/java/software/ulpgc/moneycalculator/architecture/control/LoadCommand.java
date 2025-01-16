package software.ulpgc.moneycalculator.architecture.control;

import software.ulpgc.moneycalculator.apps.windows.io.ERIOExchangeRateLoader;
import software.ulpgc.moneycalculator.architecture.io.ExchangeRateLoader;
import software.ulpgc.moneycalculator.architecture.model.Currency;
import software.ulpgc.moneycalculator.architecture.model.ExchangeRate;
import software.ulpgc.moneycalculator.architecture.view.DateDialog;

import java.io.IOException;
import java.util.Map;

public class LoadCommand implements Command {
    private final DateDialog dateDialog;
    private Map<Currency, ExchangeRate> exchangeRateMap;
    private final Map<String, Currency> currencyMap;
    private final ExchangeRateLoader loader;

    public LoadCommand(DateDialog dateDialog, Map<Currency, ExchangeRate> exchangeRateMap, Map<String, Currency> currencyMap, ExchangeRateLoader loader) {
        this.dateDialog = dateDialog;
        this.exchangeRateMap = exchangeRateMap;
        this.currencyMap = currencyMap;
        this.loader = loader;
    }

    @Override
    public void execute() {
        Map<Currency, ExchangeRate> exchangeRateMap = null;
        try {
            exchangeRateMap = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (Currency currency : exchangeRateMap.keySet())
            this.exchangeRateMap.put(currency, exchangeRateMap.get(currency));
    }
}
