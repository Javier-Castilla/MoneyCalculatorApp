package software.ulpgc.moneycalculator.architecture.control;

import software.ulpgc.moneycalculator.architecture.view.MoneyDialog;
import software.ulpgc.moneycalculator.architecture.model.Money;

public class InvertCommand implements Command {
    private final MoneyDialog fromMoneyDialog;
    private final MoneyDialog toMoneyDialog;

    public InvertCommand(MoneyDialog fromMoneyDialog, MoneyDialog toMoneyDialog) {
        this.fromMoneyDialog = fromMoneyDialog;
        this.toMoneyDialog = toMoneyDialog;
    }

    @Override
    public void execute() {
        Money fromMoney = fromMoneyDialog.get();
        Money toMoney = toMoneyDialog.get();
        fromMoneyDialog.set(toMoney);
        toMoneyDialog.set(fromMoney);
    }
}
