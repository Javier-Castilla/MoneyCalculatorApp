package software.ulpgc.moneycalculator.control;

import software.ulpgc.moneycalculator.model.Currency;
import software.ulpgc.moneycalculator.model.ExchangeRate;
import software.ulpgc.moneycalculator.model.Money;
import software.ulpgc.view.CurrencyDialog;
import software.ulpgc.view.MoneyDialog;
import software.ulpgc.view.MoneyDisplay;

import java.util.Map;

public class CalculateCommand implements Command {
    private final CurrencyDialog currencyDialog;
    private final MoneyDialog moneyDialog;
    private final MoneyDisplay moneyDisplay;
    private final Map<Currency, ExchangeRate> exchangeRates;

    public CalculateCommand(MoneyDialog moneyDialog, CurrencyDialog currencyDialog, MoneyDisplay moneyDisplay, Map<Currency, ExchangeRate> exchangeRates) {
        this.moneyDialog = moneyDialog;
        this.currencyDialog = currencyDialog;
        this.moneyDisplay = moneyDisplay;
        this.exchangeRates = exchangeRates;
    }

    @Override
    public void execute() {
        try {
            Money money = new Money(calculateExchange(moneyDialog.get().amount()), currencyDialog.get());
            moneyDisplay.show(money);
        } catch (Exception ignored) {
            Money money = new Money(0, new Currency("NA", "NA", "NA"));
            moneyDisplay.show(money);
        }
    }

    private double calculateExchange(double amount) {
        double fromCurrencyRate = exchangeRates.get(moneyDialog.get().currency()).rate();
        double toCurrencyRate = exchangeRates.get(currencyDialog.get()).rate();
        return (amount / fromCurrencyRate) * toCurrencyRate;
    }
}
