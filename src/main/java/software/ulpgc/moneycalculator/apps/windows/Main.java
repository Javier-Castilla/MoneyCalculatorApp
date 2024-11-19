package software.ulpgc.moneycalculator.apps.windows;

import software.ulpgc.moneycalculator.control.Command;
import software.ulpgc.moneycalculator.control.ToggleCurrencyDialogOrderCommand;
import software.ulpgc.moneycalculator.io.TsvFileCurrencyDeserializer;
import software.ulpgc.moneycalculator.io.TsvFileCurrencyLoader;
import software.ulpgc.moneycalculator.model.Currency;
import software.ulpgc.view.MainFrame;
import software.ulpgc.view.SwingCurrencyDialog;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception {
        Map<String, Currency> currencies = new TsvFileCurrencyLoader(
                new File("data/currencies.tsv"),
                new TsvFileCurrencyDeserializer()
        ).load();
        System.out.println(currencies);
        // List<ExchangeRate> exchanges = new ERIOExchangeRateLoader(currencies).load();
        // System.out.println(exchanges);
        MainFrame frame = new MainFrame();
        ((SwingCurrencyDialog) frame.getFromCurrencyDialog().define(currencies)).setRenderer(Currency.CurrencyRepresentation.CurrencyCode);
        ((SwingCurrencyDialog) frame.getToCurrencyDialog().define(currencies)).setRenderer(Currency.CurrencyRepresentation.CurrencyCode);
        Map<String, Command> commands = new HashMap<>();
        commands.put("toggle_order", new ToggleCurrencyDialogOrderCommand(frame.getFromCurrencyDialog(), frame.getToCurrencyDialog(), currencies));
        frame.defineCommands(commands);
        frame.setVisible(true);
    }
}
