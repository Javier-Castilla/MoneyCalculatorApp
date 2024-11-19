package software.ulpgc.moneycalculator.model;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public record Currency(String code, String name, String symbol) {
    public enum CurrencyRepresentation {
        CurrencyCode, CurrencyName, CurrencyString
    }

    public static Map<CurrencyRepresentation, Function<Currency, String>> getMethodsMap() {
        Map<CurrencyRepresentation, Function<Currency, String>> methodsMap = new HashMap<>();
        methodsMap.put(CurrencyRepresentation.CurrencyName, Currency::name);
        methodsMap.put(CurrencyRepresentation.CurrencyCode, Currency::code);
        methodsMap.put(CurrencyRepresentation.CurrencyString, Currency::toString);
        return methodsMap;
    }
}
