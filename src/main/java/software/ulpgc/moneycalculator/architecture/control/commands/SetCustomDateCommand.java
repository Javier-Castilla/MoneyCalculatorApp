package software.ulpgc.moneycalculator.architecture.control.commands;

import software.ulpgc.moneycalculator.architecture.control.Command;
import software.ulpgc.moneycalculator.architecture.io.ExchangeRateLoader;
import software.ulpgc.moneycalculator.architecture.model.Currency;
import software.ulpgc.moneycalculator.architecture.model.ExchangeRate;
import software.ulpgc.moneycalculator.architecture.view.DateDialog;

import java.util.Map;

public class SetCustomDateCommand implements Command {
    private final DateDialog dateDialog;
    private final ExchangeRateLoader loader;
    private final Map<Currency, ExchangeRate> exchangeRateMap;

    public SetCustomDateCommand(DateDialog dateDialog, ExchangeRateLoader loader, Map<Currency, ExchangeRate> exchangeRateMap) {
        this.dateDialog = dateDialog;
        this.loader = loader;
        this.exchangeRateMap = exchangeRateMap;
    }

    @Override
    public void execute() {
        dateDialog.setDateMode(DateDialog.DateMode.Custom);
        dateDialog.request();
    }
}
