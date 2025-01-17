package software.ulpgc.moneycalculator.apps.windows.io;

import software.ulpgc.moneycalculator.architecture.io.CurrencyReader;

import java.io.*;

public class JsonFileCurrencyReader implements CurrencyReader {
    private final File file;

    public JsonFileCurrencyReader(File file) {
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
