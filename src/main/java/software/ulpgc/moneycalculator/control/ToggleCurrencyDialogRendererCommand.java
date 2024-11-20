package software.ulpgc.moneycalculator.control;

import software.ulpgc.moneycalculator.model.Currency;
import software.ulpgc.moneycalculator.view.CurrencyDialog;
import software.ulpgc.moneycalculator.view.CurrencyRenderer;
import software.ulpgc.moneycalculator.view.SwingCurrencyDialog;

public class ToggleCurrencyDialogRendererCommand implements Command {
    private final CurrencyDialog fromCurrencyDialog;
    private final CurrencyDialog toCurrencyDialog;
    private int representationId;

    public ToggleCurrencyDialogRendererCommand(CurrencyDialog fromCurrencyDialog, CurrencyDialog toCurrencyDialog) {
        this.fromCurrencyDialog = fromCurrencyDialog;
        this.toCurrencyDialog = toCurrencyDialog;
        this.representationId = 0;
    }

    @Override
    public void execute() {
        representationId = (representationId + 1) % 3;
        changeRenderer(getRepresentation());
    }

    private Currency.CurrencyRepresentation getRepresentation() {
        return switch (representationId) {
            case 0 -> Currency.CurrencyRepresentation.CurrencyString;
            case 1 -> Currency.CurrencyRepresentation.CurrencyName;
            case 2 -> Currency.CurrencyRepresentation.CurrencyCode;
            default -> throw new IllegalStateException("Unexpected value: " + representationId);
        };
    }

    private void changeRenderer(Currency.CurrencyRepresentation representation) {
        ((SwingCurrencyDialog) fromCurrencyDialog).setRenderer(representation);
        ((SwingCurrencyDialog) toCurrencyDialog).setRenderer(representation);
    }
}
