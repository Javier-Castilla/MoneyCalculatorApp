package software.ulpgc.moneycalculator.apps.windows;

import software.ulpgc.moneycalculator.apps.windows.io.*;
import software.ulpgc.moneycalculator.apps.windows.io.pojo.ProgramInformation;
import software.ulpgc.moneycalculator.apps.windows.view.MainFrame;
import software.ulpgc.moneycalculator.architecture.control.*;
import software.ulpgc.moneycalculator.architecture.control.commands.*;
import software.ulpgc.moneycalculator.architecture.io.ExchangeRateLoader;
import software.ulpgc.moneycalculator.apps.windows.io.JsonFileCurrencyLoader;
import software.ulpgc.moneycalculator.apps.windows.io.JsonFileReader;
import software.ulpgc.moneycalculator.architecture.model.Currency;
import software.ulpgc.moneycalculator.architecture.model.ExchangeRate;

import java.io.IOException;
import java.util.*;

public class Main {
    private static MainFrame frame;
    private static Map<String, Currency> currencies;
    private static Map<Currency, ExchangeRate> exchangeRates;

    public static void main(String[] args) throws Exception {
        createMainFrame()
                .defineCommands(createCommands(new CommandFactory(), loadCurrencies(), loadExchangeRates()))
                .defineCurrencies(currencies.values().stream().sorted(Comparator.comparing(Currency::name)).toList())
                .defineProgramInformation(loadProgramInformation())
                .setVisible(true);
    }

    private static ProgramInformation loadProgramInformation() {
        return new JsonFileProgramInformationLoader(
                new JsonFileReader("programInformation.json"),
                new JsonFileProgramInformationDeserializer()
        ).load();
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
        return new ERIOExchangeRateLoader(
                new ERIOExchangeRateReader(),
                new ERIOExchangeRateDeserializer(),
                new ERIOExchangeRatesAdapter(currencies),
                currencies
        );
    }

    private static Map<String, Currency> loadCurrencies() throws IOException {
        currencies = new JsonFileCurrencyLoader(
                new JsonFileReader("currencies.json"),
                new JsonFileCurrencyDeserializer(),
                new JsonFileCurrencyAdapter()
        ).load();
        return currencies;
    }

    private static Map<String, CommandFactory.Builder> createCommands(CommandFactory commandFactory, Map<String, Currency> currencies, Map<Currency, ExchangeRate> exchanges) throws IOException {
        Map<String, CommandFactory.Builder> commands = new HashMap<>();
        commands.put("calculate", () -> new CalculateCommand(frame.getFromMoneyDialog(), frame.getToMoneyDialog(), exchanges));
        commands.put("toggle_representation", () -> new ToggleRendererCommand(frame.getFromMoneyDialog().getCurrencyDialog(), frame.getToMoneyDialog().getCurrencyDialog()));
        commands.put("invert", () -> new InvertCommand(frame.getFromMoneyDialog(), frame.getToMoneyDialog()));
        commands.put("display_exchangeRate", () -> new DisplayExchangeRateCommand(frame.getFromMoneyDialog(), frame.getToMoneyDialog(), frame.getExchangeRateDisplay(), exchanges));
        commands.put("load_rates", () -> new LoadCommand(frame.getDateDialog(), exchanges, createLoader()));
        commands.put("toggle_order", () -> new ToggleOrderCommand(frame.getFromMoneyDialog().getCurrencyDialog(), frame.getToMoneyDialog().getCurrencyDialog()));
        commands.put("set_order", () -> new SetOrderCommand(frame.getFromMoneyDialog().getCurrencyDialog(), frame.getToMoneyDialog().getCurrencyDialog()));
        commands.put("name_order", () -> new NameOrderCommand(frame.getFromMoneyDialog().getCurrencyDialog(), frame.getToMoneyDialog().getCurrencyDialog()));
        commands.put("code_order", () -> new CodeOrderCommand(frame.getFromMoneyDialog().getCurrencyDialog(), frame.getToMoneyDialog().getCurrencyDialog()));
        commands.put("custom_date", () -> new SetCustomDateCommand(frame.getDateDialog(), createLoader(), exchanges));
        commands.put("current_date", () -> new SetCurrentDateCommand(frame.getDateDialog(), createLoader(), exchanges, currencies));
        return commands;
    }

    private static MainFrame createMainFrame() {
        frame = new MainFrame(new CommandFactory());
        return frame;
    }
}
