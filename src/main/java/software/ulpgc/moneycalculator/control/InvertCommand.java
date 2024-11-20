package software.ulpgc.moneycalculator.control;

import software.ulpgc.moneycalculator.model.Currency;
import software.ulpgc.moneycalculator.view.CurrencyDialog;
import software.ulpgc.moneycalculator.view.MoneyDialog;

public class InvertCommand implements Command {
    private final MoneyDialog moneyDialog;
    private final CurrencyDialog currencyDialog;

    public InvertCommand(MoneyDialog moneyDialog, CurrencyDialog currencyDialog) {
        this.moneyDialog = moneyDialog;
        this.currencyDialog = currencyDialog;
    }

    @Override
    public void execute() {
        Currency fromCurrency = moneyDialog.getCurrencyDialog().get();
        Currency toCurrency = currencyDialog.get();
        moneyDialog.set(toCurrency);
        currencyDialog.set(fromCurrency);
    }
}
