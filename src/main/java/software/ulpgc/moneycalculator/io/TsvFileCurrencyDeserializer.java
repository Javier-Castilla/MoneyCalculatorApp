package software.ulpgc.moneycalculator.io;

import software.ulpgc.moneycalculator.model.Currency;

public class TsvFileCurrencyDeserializer implements CurrencyDeserializer {
    @Override
    public Currency deserialize(String line) {
        String[] fields = line.split("\t");
        return new Currency(
                fields[0],
                fields[1],
                fields.length == 3 ? fields[2] : null
        );
    }
}
