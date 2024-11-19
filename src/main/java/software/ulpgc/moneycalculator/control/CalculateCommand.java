package software.ulpgc.moneycalculator.control;

import software.ulpgc.view.CurrencyDialog;

public class CalculateCommand implements Command {
    private final CurrencyDialog currencyDialog;

    public CalculateCommand(CurrencyDialog currencyDialog) {
        this.currencyDialog = currencyDialog;
    }

    @Override
    public void execute() {
        System.out.println(currencyDialog.get());
    }
}
