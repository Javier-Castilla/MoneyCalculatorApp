package software.ulpgc.moneycalculator.architecture.io;

import software.ulpgc.moneycalculator.architecture.model.ExchangeRate;

public interface ExchangeRateDeserializer {
    Object deserialize(String line);
}
