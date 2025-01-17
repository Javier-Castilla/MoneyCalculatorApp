package software.ulpgc.moneycalculator.architecture.control.commands;

import software.ulpgc.moneycalculator.architecture.control.Command;
import software.ulpgc.moneycalculator.architecture.io.ExchangeRateLoader;
import software.ulpgc.moneycalculator.architecture.model.Currency;
import software.ulpgc.moneycalculator.architecture.model.ExchangeRate;
import software.ulpgc.moneycalculator.architecture.view.DateDialog;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class LoadCommand implements Command {
    private final DateDialog dateDialog;
    private final Map<Currency, ExchangeRate> exchangeRateMap;
    private final ExchangeRateLoader loader;

    public LoadCommand(DateDialog dateDialog, Map<Currency, ExchangeRate> exchangeRateMap, ExchangeRateLoader loader) {
        this.dateDialog = dateDialog;
        this.exchangeRateMap = exchangeRateMap;
        this.loader = loader;
    }

    @Override
    public void execute() {
        try {
            this.exchangeRateMap.clear();
            this.exchangeRateMap.putAll(loader.load(dateDialog.get()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
