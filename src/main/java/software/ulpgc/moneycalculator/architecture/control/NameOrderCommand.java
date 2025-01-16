package software.ulpgc.moneycalculator.architecture.control;

import software.ulpgc.moneycalculator.architecture.view.CurrencyDialog;

public class NameOrderCommand implements Command {
    private final CurrencyDialog fromCurrencyDialog;
    private final CurrencyDialog toCurrencyDialog;

    public NameOrderCommand(CurrencyDialog fromCurrencyDialog, CurrencyDialog toCurrencyDialog) {
        this.fromCurrencyDialog = fromCurrencyDialog;
        this.toCurrencyDialog = toCurrencyDialog;
    }

    @Override
    public void execute() {
        fromCurrencyDialog.setOrderMode(CurrencyDialog.OrderMode.NameOrder);
        toCurrencyDialog.setOrderMode(CurrencyDialog.OrderMode.NameOrder);
    }
}
