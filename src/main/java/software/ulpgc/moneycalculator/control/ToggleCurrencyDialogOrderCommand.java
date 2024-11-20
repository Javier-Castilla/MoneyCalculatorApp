package software.ulpgc.moneycalculator.control;

import software.ulpgc.moneycalculator.model.Currency;
import software.ulpgc.view.CurrencyDialog;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class ToggleCurrencyDialogOrderCommand implements Command {
    private final CurrencyDialog currencyDialog;
    private final Map<String, Currency> currencies;
    private int orderId = 0;

    public ToggleCurrencyDialogOrderCommand(CurrencyDialog currencyDialog, Map<String, Currency> currencies) {
        this.currencyDialog = currencyDialog;
        this.currencies = currencies;
    }

    @Override
    public void execute() {
        orderId = (orderId + 1) % 3;
        currencyDialog.redefine(sortCurrencies());
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
