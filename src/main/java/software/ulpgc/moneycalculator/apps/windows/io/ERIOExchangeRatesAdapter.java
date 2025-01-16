package software.ulpgc.moneycalculator.apps.windows.io;

import software.ulpgc.moneycalculator.apps.windows.io.pojo.ExchangeRatesGetResponse;
import software.ulpgc.moneycalculator.architecture.io.ExchangeRateAdapter;
import software.ulpgc.moneycalculator.architecture.model.Currency;
import software.ulpgc.moneycalculator.architecture.model.ExchangeRate;

import java.time.LocalDate;
import java.util.Map;
import java.util.stream.Collectors;

public class ERIOExchangeRatesAdapter implements ExchangeRateAdapter {
    private final Map<String, Currency> currencyMap;

    public ERIOExchangeRatesAdapter(Map<String, Currency> currencyMap) {
        this.currencyMap = currencyMap;
    }

    @Override
    public Map<Currency, ExchangeRate> adaptToMap(Object object) {
        return adaptToMap((ExchangeRatesGetResponse) object);
    }

    @Override
    public ExchangeRate adapt(Object object) {
        return null;
    }

    private Map<Currency, ExchangeRate> adaptToMap(ExchangeRatesGetResponse response) {
        System.out.println(response.rates());
        return currencyMap.values().stream()
                .filter(c -> response.rates().containsKey(c.code()))
                .collect(Collectors.toMap(
                        c -> c,
                        c -> new ExchangeRate(LocalDate.now(), response.rates().get(c.code()), currencyMap.get(response.base()), c)
                ));
    }

    @Override
    public ExchangeRate adapt(ExchangeRate fromExchangeRate, ExchangeRate toExchangeRate) {
        return new ExchangeRate(
                fromExchangeRate.date(),
                calculateRate(fromExchangeRate, toExchangeRate),
                fromExchangeRate.to_currency(),
                toExchangeRate.to_currency()
        );
    }

    private static double calculateRate(ExchangeRate fromExchangeRate, ExchangeRate toExchangeRate) {
        Double fromAmount = fromExchangeRate.rate();
        Double toAmount = toExchangeRate.rate();
        return fromAmount / toAmount;
    }
}
