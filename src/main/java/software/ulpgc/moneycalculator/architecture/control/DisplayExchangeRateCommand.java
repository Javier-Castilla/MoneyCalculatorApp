package software.ulpgc.moneycalculator.architecture.control;

import software.ulpgc.moneycalculator.architecture.model.Currency;
import software.ulpgc.moneycalculator.architecture.model.ExchangeRate;
import software.ulpgc.moneycalculator.architecture.view.ExchangeRateDisplay;
import software.ulpgc.moneycalculator.architecture.view.MoneyDialog;

import java.util.Map;

public class DisplayExchangeRateCommand implements Command {
    private final MoneyDialog fromMoneyDialog;
    private final MoneyDialog toMoneyDialog;
    private final ExchangeRateDisplay display;
    private final Map<Currency, ExchangeRate> exchangeRateMap;

    public DisplayExchangeRateCommand(MoneyDialog fromMoneyDialog, MoneyDialog toMoneyDialog, ExchangeRateDisplay display, Map<Currency, ExchangeRate> exchangeRateMap) {
        this.fromMoneyDialog = fromMoneyDialog;
        this.toMoneyDialog = toMoneyDialog;
        this.display = display;
        this.exchangeRateMap = exchangeRateMap;
    }

    @Override
    public void execute() {
        ExchangeRate exchangeRate = mergeExchangeRates(
                exchangeRateMap.get(fromMoneyDialog.getCurrencyDialog().get()),
                exchangeRateMap.get(toMoneyDialog.getCurrencyDialog().get())
        );
        display.show(exchangeRate);
    }

    private ExchangeRate mergeExchangeRates(ExchangeRate fromExchangeRate, ExchangeRate toExchangeRate) {
        return new ExchangeRate(
                fromExchangeRate.date(),
                calculateRate(fromExchangeRate, toExchangeRate),
                fromExchangeRate.to_currency(),
                toExchangeRate.to_currency()
        );
    }

    private double calculateRate(ExchangeRate fromExchangeRate, ExchangeRate toExchangeRate) {
        Double fromAmount = fromExchangeRate.rate();
        Double toAmount = toExchangeRate.rate();
        return toAmount / fromAmount;
    }
}
