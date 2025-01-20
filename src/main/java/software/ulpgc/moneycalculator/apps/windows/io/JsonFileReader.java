package software.ulpgc.moneycalculator.apps.windows.io;

import software.ulpgc.moneycalculator.architecture.io.FileReader;

import java.io.*;

public class JsonFileReader implements FileReader {
    private final String file;

    public JsonFileReader(String file) {
        this.file = file;
    }

    @Override
    public String read() {
        try (InputStream is = JsonFileReader.class.getClassLoader().getResourceAsStream(this.file)) {
            return new String(is.readAllBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
