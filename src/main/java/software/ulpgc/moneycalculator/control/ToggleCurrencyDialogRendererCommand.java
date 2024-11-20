package software.ulpgc.moneycalculator.control;

import software.ulpgc.moneycalculator.model.Currency;
import software.ulpgc.view.CurrencyDialog;
import software.ulpgc.view.SwingCurrencyDialog;

public class ToggleCurrencyDialogRendererCommand implements Command {
    private final CurrencyDialog currencyDialog;
    private int representationId;

    public ToggleCurrencyDialogRendererCommand(CurrencyDialog currencyDialog) {
        this.currencyDialog = currencyDialog;
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
        ((SwingCurrencyDialog) currencyDialog).setRenderer(representation);
    }
}
