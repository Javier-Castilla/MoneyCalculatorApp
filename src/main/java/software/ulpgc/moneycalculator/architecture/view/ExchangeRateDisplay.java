package software.ulpgc.moneycalculator.architecture.view;

import software.ulpgc.moneycalculator.architecture.model.ExchangeRate;

public interface ExchangeRateDisplay {
    ExchangeRateDisplay show(ExchangeRate exchangeRate);
}
