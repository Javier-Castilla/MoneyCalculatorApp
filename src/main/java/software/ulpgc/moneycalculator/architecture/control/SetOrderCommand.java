package software.ulpgc.moneycalculator.architecture.control;

import software.ulpgc.moneycalculator.architecture.view.CurrencyDialog;

import software.ulpgc.moneycalculator.architecture.model.Currency;

import java.util.*;

public class SetOrderCommand implements Command {
    private final CurrencyDialog fromCurrencyDialog;
    private final CurrencyDialog toCurrencyDialog;

    public SetOrderCommand(CurrencyDialog fromCurrencyDialog, CurrencyDialog toCurrencyDialog) {
        this.fromCurrencyDialog = fromCurrencyDialog;
        this.toCurrencyDialog = toCurrencyDialog;
    }

    private Map<CurrencyDialog.OrderMode, CurrencyDialog.OrderMode> buildNegationMap() {
        Map<CurrencyDialog.OrderMode, CurrencyDialog.OrderMode> negationMap = new HashMap<>();
        negationMap.put(CurrencyDialog.OrderMode.NameOrder, CurrencyDialog.OrderMode.CodeOrder);
        negationMap.put(CurrencyDialog.OrderMode.CodeOrder, CurrencyDialog.OrderMode.NameOrder);
        return negationMap;
    }

    @Override
    public void execute() {
        List<Currency> newDefinition = getOrderedDefinition(fromCurrencyDialog.getDefinition());
        fromCurrencyDialog.redefine(newDefinition);
        toCurrencyDialog.redefine(newDefinition);
    }

    private List<Currency> getOrderedDefinition(List<Currency> definition) {
        switch (fromCurrencyDialog.getOrderDirection()) {
            case AscendingOrder ->
                    definition.sort(
                            Comparator.comparing(
                                    fromCurrencyDialog.getOrderMode() == CurrencyDialog.OrderMode.NameOrder ?
                                        Currency::name :
                                        Currency::code
                            ).reversed()
                    );
            case DescendingOrder ->
                    definition.sort(
                            Comparator.comparing(
                                    fromCurrencyDialog.getOrderMode() == CurrencyDialog.OrderMode.NameOrder ?
                                            Currency::name :
                                            Currency::code
                            )
                    );
        }
        return definition;
    }
}
