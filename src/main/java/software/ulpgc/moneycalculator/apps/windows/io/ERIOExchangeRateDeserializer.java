package software.ulpgc.moneycalculator.apps.windows.io;

import com.google.gson.Gson;
import software.ulpgc.moneycalculator.apps.windows.io.pojo.ExchangeRatesGetResponse;
import software.ulpgc.moneycalculator.architecture.io.ExchangeRateDeserializer;
import software.ulpgc.moneycalculator.architecture.model.ExchangeRate;

public class ERIOExchangeRateDeserializer implements ExchangeRateDeserializer {
    @Override
    public Object deserialize(String line) {
        System.out.println(line);
        return new Gson().fromJson(line, ExchangeRatesGetResponse.class);
    }
}
