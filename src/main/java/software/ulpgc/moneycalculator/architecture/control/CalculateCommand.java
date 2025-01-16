package software.ulpgc.moneycalculator.architecture.control;

import software.ulpgc.moneycalculator.architecture.io.ExchangeRateLoader;
import software.ulpgc.moneycalculator.architecture.view.DateDialog;
import software.ulpgc.moneycalculator.architecture.view.MoneyDialog;
import software.ulpgc.moneycalculator.architecture.model.Currency;
import software.ulpgc.moneycalculator.architecture.model.ExchangeRate;
import software.ulpgc.moneycalculator.architecture.model.Money;

import java.io.IOException;
import java.util.Map;

public class CalculateCommand implements Command {
    private final MoneyDialog fromMoneyDialog;
    private final MoneyDialog toMoneyDialog;
    private final DateDialog dateDialog;
    private final Map<Currency, ExchangeRate> exchangeRates;
    private final ExchangeRateLoader exchangeRateLoader;

    public CalculateCommand(MoneyDialog fromMoneyDialog, MoneyDialog toMoneyDialog, DateDialog dateDialog, Map<Currency, ExchangeRate> exchangeRates, ExchangeRateLoader exchangeRateLoader) {
        this.fromMoneyDialog = fromMoneyDialog;
        this.toMoneyDialog = toMoneyDialog;
        this.dateDialog = dateDialog;
        this.exchangeRates = exchangeRates;
        this.exchangeRateLoader = exchangeRateLoader;
    }

    @Override
    public void execute() {
        try {
            getNonFocusedDialog().set(new Money(calculateExchange(getFocusedDialog().get().amount()), getNonFocusedDialog().get().currency()));
        } catch (Exception ignored) {
            getNonFocusedDialog().set(new Money(0.00, getNonFocusedDialog().get().currency()));
        }
    }

    private MoneyDialog getNonFocusedDialog() {
        if (fromMoneyDialog.isFocused()) {
            return toMoneyDialog;
        }  else if (toMoneyDialog.isFocused()) {
            return fromMoneyDialog;
        } else {
            return toMoneyDialog;
        }
    }

    private MoneyDialog getFocusedDialog() {
        if (fromMoneyDialog.isFocused()) {
            return fromMoneyDialog;
        } else if (toMoneyDialog.isFocused()) {
            return toMoneyDialog;
        } else {
            return fromMoneyDialog;
        }
    }

    private void show(MoneyDialog moneyDialog, Money money) {
        moneyDialog.set(money);
    }

    private double calculateExchange(double amount) {
        try {
            double fromCurrencyRate = dateDialog.getDateMode() == DateDialog.DateMode.Current ?
                    exchangeRates.get(fromMoneyDialog.get().currency()).rate() :
                    exchangeRateLoader.load(dateDialog.get(), toMoneyDialog.getCurrencyDialog().get()).rate();
            double toCurrencyRate = dateDialog.getDateMode() == DateDialog.DateMode.Current ?
                    exchangeRates.get(toMoneyDialog.get().currency()).rate() :
                    exchangeRateLoader.load(dateDialog.get(), toMoneyDialog.getCurrencyDialog().get()).rate();
            System.out.println(fromCurrencyRate + " " + toCurrencyRate);
            System.out.println();
            return (amount / fromCurrencyRate) * toCurrencyRate;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
