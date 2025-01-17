package software.ulpgc.moneycalculator.apps.windows.io;

import com.google.gson.Gson;
import software.ulpgc.moneycalculator.apps.windows.io.pojo.ProgramInformation;
import software.ulpgc.moneycalculator.architecture.io.ProgramInformationDeserializer;
import software.ulpgc.moneycalculator.architecture.io.ProgramInformationLoader;

public class JsonFileProgramInformationDeserializer implements ProgramInformationDeserializer {
    @Override
    public Object deserialize(String line) {
        return new Gson().fromJson(line, ProgramInformation.class);
    }
}
