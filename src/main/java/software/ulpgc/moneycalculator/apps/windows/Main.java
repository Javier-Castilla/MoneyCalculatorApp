package software.ulpgc.moneycalculator.apps.windows;

import software.ulpgc.moneycalculator.control.CalculateCommand;
import software.ulpgc.moneycalculator.control.Command;
import software.ulpgc.moneycalculator.control.ToggleCurrencyDialogOrderCommand;
import software.ulpgc.moneycalculator.control.ToggleCurrencyDialogRendererCommand;
import software.ulpgc.moneycalculator.io.ERIOExchangeRateLoader;
import software.ulpgc.moneycalculator.io.TsvFileCurrencyDeserializer;
import software.ulpgc.moneycalculator.io.TsvFileCurrencyLoader;
import software.ulpgc.moneycalculator.model.Currency;
import software.ulpgc.moneycalculator.model.ExchangeRate;
import software.ulpgc.view.MainFrame;
import software.ulpgc.view.SwingCurrencyDialog;
import software.ulpgc.view.SwingMoneyDialog;

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
        Map<Currency, ExchangeRate> exchanges = new ERIOExchangeRateLoader(currencies).load();
        // System.out.println(exchanges);
        MainFrame frame = new MainFrame();
        frame.getCurrencyDialog().define(currencies);
        frame.getMoneyDialog().define(currencies);
        Map<String, Command> commands = new HashMap<>();
        commands.put("toggle_order", new ToggleCurrencyDialogOrderCommand(frame.getCurrencyDialog(), currencies));
        commands.put("calculate",  new CalculateCommand(frame.getMoneyDialog(), frame.getCurrencyDialog(), frame.getMoneyDisplay(), exchanges));
        commands.put("toggle_representation", new ToggleCurrencyDialogRendererCommand(frame.getCurrencyDialog()));
        frame.defineCommands(commands);
        ((SwingMoneyDialog) frame.getMoneyDialog()).addCommand(commands.get("calculate"));
        frame.setVisible(true);
    }
}
