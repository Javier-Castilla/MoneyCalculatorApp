package software.ulpgc.moneycalculator.apps.windows.io;

import software.ulpgc.moneycalculator.apps.windows.io.pojo.ProgramInformation;
import software.ulpgc.moneycalculator.architecture.io.ProgramInformationDeserializer;
import software.ulpgc.moneycalculator.architecture.io.ProgramInformationLoader;
import software.ulpgc.moneycalculator.architecture.io.ProgramInformationReader;

public class JsonFileProgramInformationLoader implements ProgramInformationLoader {
    private final ProgramInformationReader reader;
    private final ProgramInformationDeserializer deserializer;

    public JsonFileProgramInformationLoader(ProgramInformationReader reader, ProgramInformationDeserializer deserializer) {
        this.reader = reader;
        this.deserializer = deserializer;
    }

    @Override
    public ProgramInformation load() {
        return (ProgramInformation) deserializer.deserialize(reader.read());
    }
}
