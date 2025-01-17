package software.ulpgc.moneycalculator.architecture.control.commands;

import software.ulpgc.moneycalculator.architecture.control.Command;
import software.ulpgc.moneycalculator.architecture.view.CurrencyDialog;

public class CodeOrderCommand implements Command {
    private final CurrencyDialog fromCurrencyDialog;
    private final CurrencyDialog toCurrencyDialog;

    public CodeOrderCommand(CurrencyDialog fromCurrencyDialog, CurrencyDialog toCurrencyDialog) {
        this.fromCurrencyDialog = fromCurrencyDialog;
        this.toCurrencyDialog = toCurrencyDialog;
    }

    @Override
    public void execute() {
        fromCurrencyDialog.setOrderMode(CurrencyDialog.OrderMode.CodeOrder);
        toCurrencyDialog.setOrderMode(CurrencyDialog.OrderMode.CodeOrder);
    }
}
