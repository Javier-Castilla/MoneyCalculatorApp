package software.ulpgc.moneycalculator.apps.windows.io.erioapi;

import software.ulpgc.moneycalculator.architecture.model.Currency;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Arrays;

public class ERIOAPI {
    public final static String KEY = "7e5ea59922afd6f5ca33401bbca5573a";
    public final static String SYMBOLS = "https://api.exchangeratesapi.io/v1/symbols?access_key=";
    public final static String CURRENT_EXCHANGE_RATES = "https://api.exchangeratesapi.io/v1/latest?access_key=";
    public final static String FROM_TO_EXCHANGE_RATES = "&base=%s&symbols=%s";

    public static String exchangeRatesFrom(Currency from, Currency... to) {
        return String.format(
                FROM_TO_EXCHANGE_RATES,
                from.code(),
                normalizeArrayString(getCurrencyCodesArray(to))
        );
    }

    private static String normalizeArrayString(String[] currencyCodesArray) {
        return Arrays.toString(currencyCodesArray)
                .replace(" ", "")
                .replace("[", "")
                .replace("]", "");
    }

    private static String[] getCurrencyCodesArray(Currency[] currencies) {
        return Arrays.stream(currencies)
                .map(Currency::code)
                .toArray(String[]::new);
    }

    public static String exchangeRatesOfDate(LocalDate date) {
        return CURRENT_EXCHANGE_RATES.replace("latest", date.toString());
    }
}
