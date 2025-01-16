package software.ulpgc.moneycalculator.apps.windows.io;

import com.google.gson.Gson;
import software.ulpgc.moneycalculator.apps.windows.io.pojo.CurrencyGetResponse;
import software.ulpgc.moneycalculator.architecture.io.CurrencyDeserializer;

public class JsonFileCurrencyDeserializer implements CurrencyDeserializer {
    @Override
    public Object deserialize(String line) {
        return new Gson().fromJson(line, CurrencyGetResponse.class);
    }
}
