package software.ulpgc.moneycalculator.apps.windows;

import software.ulpgc.moneycalculator.control.*;
import software.ulpgc.moneycalculator.io.ERIOExchangeRateLoader;
import software.ulpgc.moneycalculator.io.TsvFileCurrencyDeserializer;
import software.ulpgc.moneycalculator.io.TsvFileCurrencyLoader;
import software.ulpgc.moneycalculator.mocks.MockExchangeRateLoader;
import software.ulpgc.moneycalculator.model.Currency;
import software.ulpgc.moneycalculator.model.ExchangeRate;
import software.ulpgc.moneycalculator.view.MainFrame;
import software.ulpgc.moneycalculator.view.SwingMoneyDialog;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception {
        Map<String, Currency> currencies = new TsvFileCurrencyLoader(
                new File("data/currencies.tsv"),
                new TsvFileCurrencyDeserializer()
        ).load();
        Map<Currency, ExchangeRate> exchanges = new MockExchangeRateLoader(currencies).load();

        MainFrame frame = new MainFrame();
        frame.getCurrencyDialog().define("To: ", currencies);
        frame.getMoneyDialog().define(currencies);

        Map<String, Command> commands = new HashMap<>();
        commands.put("toggle_order", new ToggleCurrencyDialogOrderCommand(frame.getMoneyDialog().getCurrencyDialog(), frame.getCurrencyDialog(), currencies));
        commands.put("calculate",  new CalculateCommand(frame.getMoneyDialog(), frame.getCurrencyDialog(), frame.getMoneyDisplay(), exchanges));
        commands.put("toggle_representation", new ToggleCurrencyDialogRendererCommand(frame.getMoneyDialog().getCurrencyDialog(), frame.getCurrencyDialog()));
        commands.put("invert", new InvertCommand(frame.getMoneyDialog(), frame.getCurrencyDialog()));
        frame.defineCommands(commands);

        frame.setVisible(true);
    }
}
