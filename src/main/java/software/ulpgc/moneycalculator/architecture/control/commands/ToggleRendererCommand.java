package software.ulpgc.moneycalculator.architecture.control.commands;

import software.ulpgc.moneycalculator.architecture.control.Command;
import software.ulpgc.moneycalculator.architecture.model.Currency;
import software.ulpgc.moneycalculator.architecture.view.CurrencyDialog;
import software.ulpgc.moneycalculator.apps.windows.view.SwingCurrencyDialog;

public class ToggleRendererCommand implements Command {
    private final CurrencyDialog fromCurrencyDialog;
    private final CurrencyDialog toCurrencyDialog;

    public ToggleRendererCommand(CurrencyDialog fromCurrencyDialog, CurrencyDialog toCurrencyDialog) {
        this.fromCurrencyDialog = fromCurrencyDialog;
        this.toCurrencyDialog = toCurrencyDialog;
    }

    @Override
    public void execute() {
        changeRenderer(getRepresentation());
    }

    private Currency.CurrencyRepresentation getRepresentation() {
        return switch (fromCurrencyDialog.getCurrencyRepresentation()) {
            case CurrencyName -> {
                fromCurrencyDialog.setCurrencyRepresentation(Currency.CurrencyRepresentation.CurrencyCode);
                toCurrencyDialog.setCurrencyRepresentation(Currency.CurrencyRepresentation.CurrencyCode);
                yield Currency.CurrencyRepresentation.CurrencyCode;
            }
            case CurrencyCode -> {
                fromCurrencyDialog.setCurrencyRepresentation(Currency.CurrencyRepresentation.CurrencyName);
                toCurrencyDialog.setCurrencyRepresentation(Currency.CurrencyRepresentation.CurrencyName);
                yield Currency.CurrencyRepresentation.CurrencyName;
            }
            default -> throw new IllegalStateException("Unexpected value: " + fromCurrencyDialog.getCurrencyRepresentation());
        };
    }

    private void changeRenderer(Currency.CurrencyRepresentation representation) {
        ((SwingCurrencyDialog) fromCurrencyDialog).setRenderer(representation);
        ((SwingCurrencyDialog) toCurrencyDialog).setRenderer(representation);
    }
}
