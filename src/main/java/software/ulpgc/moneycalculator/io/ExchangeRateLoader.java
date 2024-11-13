package software.ulpgc.moneycalculator.io;

import software.ulpgc.moneycalculator.model.ExchangeRate;

import java.net.MalformedURLException;
import java.util.List;

public interface ExchangeRateLoader {
    List<ExchangeRate> load() throws MalformedURLException;
}
