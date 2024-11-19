package software.ulpgc.moneycalculator.control;

import software.ulpgc.moneycalculator.model.Currency;
import software.ulpgc.view.CurrencyDialog;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class ToggleCurrencyDialogOrderCommand implements Command {
    private final CurrencyDialog fromCurrencyDialog;
    private final CurrencyDialog toCurrencyDialog;
    private final Map<String, Currency> currencies;
    private int orderId = 0;

    public ToggleCurrencyDialogOrderCommand(CurrencyDialog currencyDialog, CurrencyDialog toCurrencyDialog, Map<String, Currency> currencies) {
        this.fromCurrencyDialog = currencyDialog;
        this.toCurrencyDialog = toCurrencyDialog;
        this.currencies = currencies;
    }

    @Override
    public void execute() {
        orderId++;
        fromCurrencyDialog.redefine(sortCurrencies());
        toCurrencyDialog.redefine(sortCurrencies());
    }

    private Map<String, Currency> sortCurrencies() {
        return switch (orderId % 3) {
            case 0 -> currencies;
            case 1 -> new TreeMap<>(Comparator.naturalOrder()){{putAll(currencies);}};
            case 2 -> new TreeMap<>(Comparator.reverseOrder()){{putAll(currencies);}};
            default -> throw new IllegalStateException("Unexpected value: " + orderId % 3);
        };
    }
}
