package software.ulpgc.moneycalculator.architecture.io;

import software.ulpgc.moneycalculator.architecture.model.Currency;

import java.util.Map;

public interface CurrencyDeserializer {
    Object deserialize(String line);
}
