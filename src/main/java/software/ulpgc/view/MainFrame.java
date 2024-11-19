package software.ulpgc.view;

import software.ulpgc.moneycalculator.control.Command;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class MainFrame extends JFrame {
    private final CurrencyDialog fromCurrencyDialog;
    private final CurrencyDialog toCurrencyDialog;
    private Map<String, Command> commands;
    public MainFrame() throws HeadlessException {
        this.setTitle("MoneyCalculator");
        this.setSize(800, 600);
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(BorderLayout.NORTH, toolbar());
        this.add(BorderLayout.EAST, (Component) (this.fromCurrencyDialog = createCurrencyDialog()));
        this.add(BorderLayout.WEST, (Component) (this.toCurrencyDialog = createCurrencyDialog()));
    }

    public void defineCommands(Map<String, Command> commands) {
        this.commands = commands;
    }

    public CurrencyDialog getFromCurrencyDialog() {
        return fromCurrencyDialog;
    }


    public CurrencyDialog getToCurrencyDialog() {
        return toCurrencyDialog;
    }

    private CurrencyDialog createCurrencyDialog() {
        return new SwingCurrencyDialog();
    }

    private Component toolbar() {
        JPanel panel = new JPanel();
        panel.add(createCalculateButton());
        panel.add(createToggleCurrencyOrderButton());
        return panel;
    }

    private Component createToggleCurrencyOrderButton() {
        JButton button = new JButton("Toggle Order");
        button.addActionListener(e -> commands.get("toggle_order").execute());
        return button;
    }

    private Component createCalculateButton() {
        JButton button = new JButton("Calculate");
        button.addActionListener(e -> System.out.println("Presionado!"));
        return button;
    }
}
