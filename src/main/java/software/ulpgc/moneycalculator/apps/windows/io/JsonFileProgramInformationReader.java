package software.ulpgc.moneycalculator.apps.windows.io;

import software.ulpgc.moneycalculator.architecture.io.ProgramInformationReader;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.File;

public class JsonFileProgramInformationReader implements ProgramInformationReader {
    private final File file;

    public JsonFileProgramInformationReader(File file) {
        this.file = file;
    }

    @Override
    public String read() {
        try (FileInputStream is = new FileInputStream(file)) {
            return new String(is.readAllBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
