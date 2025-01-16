package software.ulpgc.moneycalculator.architecture.control;

import software.ulpgc.moneycalculator.architecture.io.ExchangeRateLoader;
import software.ulpgc.moneycalculator.architecture.model.Currency;
import software.ulpgc.moneycalculator.architecture.model.ExchangeRate;
import software.ulpgc.moneycalculator.architecture.view.DateDialog;

import java.io.IOException;
import java.util.Map;

public class SetCurrentDateCommand implements Command {
    private final DateDialog dateDialog;
    private final ExchangeRateLoader exchangeRateLoader;
    private final Map<Currency, ExchangeRate> exchangeRateMap;

    public SetCurrentDateCommand(DateDialog dateDialog, ExchangeRateLoader exchangeRateLoader, Map<Currency, ExchangeRate> exchangeRateMap, Map<String, Currency> currencyMap) {
        this.dateDialog = dateDialog;
        this.exchangeRateLoader = exchangeRateLoader;
        this.exchangeRateMap = exchangeRateMap;
    }

    @Override
    public void execute() {
        try {
            exchangeRateMap.putAll(exchangeRateLoader.load());
            dateDialog.setDateMode(DateDialog.DateMode.Current);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
