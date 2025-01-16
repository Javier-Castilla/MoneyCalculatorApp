package software.ulpgc.moneycalculator.architecture.control;

import software.ulpgc.moneycalculator.architecture.model.Currency;
import software.ulpgc.moneycalculator.architecture.view.CurrencyDialog;
import software.ulpgc.moneycalculator.apps.windows.view.SwingCurrencyDialog;

public class ToggleRendererCommand implements Command {
    private final CurrencyDialog fromCurrencyDialog;
    private final CurrencyDialog toCurrencyDialog;
    private int representationId;

    public ToggleRendererCommand(CurrencyDialog fromCurrencyDialog, CurrencyDialog toCurrencyDialog) {
        this.fromCurrencyDialog = fromCurrencyDialog;
        this.toCurrencyDialog = toCurrencyDialog;
        this.representationId = 0;
    }

    @Override
    public void execute() {
        representationId = (representationId + 1) % 2;
        changeRenderer(getRepresentation());
    }

    private Currency.CurrencyRepresentation getRepresentation() {
        return switch (representationId) {
            case 0 -> Currency.CurrencyRepresentation.CurrencyName;
            case 1 -> Currency.CurrencyRepresentation.CurrencyCode;
            default -> throw new IllegalStateException("Unexpected value: " + representationId);
        };
    }

    private void changeRenderer(Currency.CurrencyRepresentation representation) {
        ((SwingCurrencyDialog) fromCurrencyDialog).setRenderer(representation);
        ((SwingCurrencyDialog) toCurrencyDialog).setRenderer(representation);
    }
}
