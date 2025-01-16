package software.ulpgc.moneycalculator.architecture.control;

import software.ulpgc.moneycalculator.architecture.model.Currency;
import software.ulpgc.moneycalculator.architecture.view.CurrencyDialog;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class ToggleOrderCommand implements Command {

    private final CurrencyDialog fromCurrencyDialog;
    private final CurrencyDialog toCurrencyDialog;
    private final static Map<CurrencyDialog.OrderDirection, CurrencyDialog.OrderDirection> negationMap =
            Map.of(CurrencyDialog.OrderDirection.DescendingOrder, CurrencyDialog.OrderDirection.AscendingOrder, CurrencyDialog.OrderDirection.AscendingOrder, CurrencyDialog.OrderDirection.DescendingOrder);

    public ToggleOrderCommand(CurrencyDialog fromCurrencyDialog, CurrencyDialog toCurrencyDialog) {
        this.fromCurrencyDialog = fromCurrencyDialog;
        this.toCurrencyDialog = toCurrencyDialog;
    }

    @Override
    public void execute() {
        setOrderedDefinitionTo(fromCurrencyDialog)
                .setOrderDirection(negationMap.get(fromCurrencyDialog.getOrderDirection()));
        setOrderedDefinitionTo(toCurrencyDialog)
                .setOrderDirection(negationMap.get(fromCurrencyDialog.getOrderDirection()));
    }

    private CurrencyDialog setOrderedDefinitionTo(CurrencyDialog currencyDialog) {
        currencyDialog.redefine(getNewDefinitionFor(currencyDialog));
        return currencyDialog;
    }

    private List<Currency> getNewDefinitionFor(CurrencyDialog currencyDialog) {
        return sortWithComparator(currencyDialog.getDefinition(), getComparatorFor(currencyDialog));
    }

    private List<Currency> sortWithComparator(List<Currency> definition, Comparator<? super Currency> comparator) {
        definition.sort(comparator);
        return definition;
    }

    private Comparator<? super Currency> getComparatorFor(CurrencyDialog currencyDialog) {
        return switch (currencyDialog.getOrderMode()) {
            case NameOrder -> reverseComparator(Comparator.comparing(Currency::name), isAscendingOrdered(currencyDialog.getOrderDirection()));
            case CodeOrder -> reverseComparator(Comparator.comparing(Currency::code), isAscendingOrdered(currencyDialog.getOrderDirection()));
        };
    }

    private Comparator<? super Currency> reverseComparator(Comparator<Currency> comparing, boolean reverse) {
        return reverse ? comparing.reversed() : comparing;
    }

    private boolean isAscendingOrdered(CurrencyDialog.OrderDirection orderDirection) {
        return orderDirection.equals(CurrencyDialog.OrderDirection.AscendingOrder);
    }
}
