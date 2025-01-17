package software.ulpgc.moneycalculator.apps.windows.io.pojo;

import java.util.Map;

public record ExchangeRatesGetResponse(Boolean success, int timestamp, String base, String date, Map<String, Double> rates) {
}
