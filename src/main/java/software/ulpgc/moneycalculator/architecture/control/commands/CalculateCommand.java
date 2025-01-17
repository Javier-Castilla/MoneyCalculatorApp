package software.ulpgc.moneycalculator.architecture.control.commands;

import software.ulpgc.moneycalculator.architecture.control.Command;
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
    private final Map<Currency, ExchangeRate> exchangeRates;

    public CalculateCommand(MoneyDialog fromMoneyDialog, MoneyDialog toMoneyDialog, Map<Currency, ExchangeRate> exchangeRates) {
        this.fromMoneyDialog = fromMoneyDialog;
        this.toMoneyDialog = toMoneyDialog;
        this.exchangeRates = exchangeRates;
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
        double fromCurrencyRate = exchangeRates.get(getFocusedDialog().get().currency()).rate();
        double toCurrencyRate = exchangeRates.get(getNonFocusedDialog().get().currency()).rate();
        return (amount / fromCurrencyRate) * toCurrencyRate;
    }
}
