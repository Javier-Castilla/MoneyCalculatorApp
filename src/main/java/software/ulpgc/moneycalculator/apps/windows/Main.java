package software.ulpgc.moneycalculator.apps.windows;

import software.ulpgc.moneycalculator.apps.windows.io.*;
import software.ulpgc.moneycalculator.apps.windows.view.MainFrame;
import software.ulpgc.moneycalculator.architecture.control.*;
import software.ulpgc.moneycalculator.architecture.io.ExchangeRateLoader;
import software.ulpgc.moneycalculator.apps.windows.io.JsonFileCurrencyLoader;
import software.ulpgc.moneycalculator.apps.windows.io.JsonFileCurrencyReader;
import software.ulpgc.moneycalculator.architecture.model.Currency;
import software.ulpgc.moneycalculator.architecture.model.ExchangeRate;
import software.ulpgc.moneycalculator.mocks.MockExchangeRateLoader;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Main {
    private static MainFrame frame;
    private static Map<String, Currency> currencies;
    private static Map<Currency, ExchangeRate> exchangeRates;
    private static ExchangeRateLoader loader;

    public static void main(String[] args) throws Exception {
        createMainFrame()
                .defineCurrencies(loadCurrencies().values().stream().sorted(Comparator.comparing(Currency::name)).toList())
                .defineCommands(createCommands(currencies, loadExchangeRates()))
                .setVisible(true);
    }

    private static Map<Currency, ExchangeRate> loadExchangeRates() {
        try {
            exchangeRates = createLoader().load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return exchangeRates;
    }

    private static ExchangeRateLoader createLoader() {
        return new MockExchangeRateLoader(currencies);
    }

    private static Map<String, Currency> loadCurrencies() throws IOException {
        currencies = new JsonFileCurrencyLoader(
                new JsonFileCurrencyReader(new File(Main.class.getClassLoader().getResource("currencies.json").getFile())),
                new JsonFileCurrencyDeserializer(),
                new JsonFileCurrencyAdapter()
        ).load();
        return currencies;
    }

    private static Map<String, Command> createCommands(Map<String, Currency> currencies, Map<Currency, ExchangeRate> exchanges) throws IOException {
        Map<String, Command> commands = new HashMap<>();
        commands.put("calculate",  new CalculateCommand(frame.getFromMoneyDialog(), frame.getToMoneyDialog(), frame.getDateDialog(), exchanges, new MockExchangeRateLoader(currencies)));
        commands.put("toggle_representation", new ToggleRendererCommand(frame.getFromMoneyDialog().getCurrencyDialog(), frame.getToMoneyDialog().getCurrencyDialog()));
        commands.put("invert", new InvertCommand(frame.getFromMoneyDialog(), frame.getToMoneyDialog()));
        commands.put("display_exchangeRate", new DisplayExchangeRateCommand(frame.getFromMoneyDialog(), frame.getToMoneyDialog(), frame.getExchangeRateDisplay(), exchanges));
        commands.put("load_rates", new LoadCommand(frame.getDateDialog(), exchanges, currencies, loader));
        commands.put("open_settings", new OpenSettingsCommand());
        commands.get("display_exchangeRate").execute();
        commands.put("toggle_order", new ToggleOrderCommand(frame.getFromMoneyDialog().getCurrencyDialog(), frame.getToMoneyDialog().getCurrencyDialog()));
        commands.put("set_order", new SetOrderCommand(frame.getFromMoneyDialog().getCurrencyDialog(), frame.getToMoneyDialog().getCurrencyDialog()));
        commands.put("name_order", new NameOrderCommand(frame.getFromMoneyDialog().getCurrencyDialog(), frame.getToMoneyDialog().getCurrencyDialog()));
        commands.put("code_order", new CodeOrderCommand(frame.getFromMoneyDialog().getCurrencyDialog(), frame.getToMoneyDialog().getCurrencyDialog()));
        commands.put("custom_date", new SetCustomDateCommand(frame.getDateDialog()));
        return commands;
    }

    private static MainFrame createMainFrame() {
        frame = new MainFrame();
        return frame;
    }
}
