package software.ulpgc.moneycalculator.view;

import software.ulpgc.moneycalculator.control.Command;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Map;

public class MainFrame extends JFrame {
    private final CurrencyDialog currencyDialog;
    private final MoneyDialog moneyDialog;
    private final MoneyDisplay moneyDisplay;
    private Map<String, Command> commands;

    public MainFrame() throws HeadlessException {
        this.setTitle("MoneyCalculator");
        this.setLayout(new BorderLayout());
        this.setSize(600, 300);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(BorderLayout.NORTH, createTitlePanel());
        this.add(
                createExchangePanel(
                        this.moneyDialog = createMoneyDialog(),
                        this.currencyDialog = createCurrencyDialog(),
                        this.moneyDisplay = createMoneyDisplay()
                )
        );
        this.add(BorderLayout.SOUTH, toolbar());
    }

    private Component createTitlePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JLabel title = new JLabel("MoneyCalculator");
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setAlignmentX(CENTER_ALIGNMENT);
        panel.add(title);
        JLabel name = new JLabel("Powered by Javier Castilla");
        name.setFont(new Font("Arial", Font.BOLD, 15));
        name.setAlignmentX(CENTER_ALIGNMENT);
        panel.add(name);
        return panel;
    }

    private Component createExchangePanel(MoneyDialog moneyDialog, CurrencyDialog currencyDialog, MoneyDisplay moneyDisplay) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add((Component) moneyDialog);
        panel.add((Component) currencyDialog);
        panel.add((Component) moneyDisplay);
        return panel;
    }

    private MoneyDialog createMoneyDialog() {
        SwingMoneyDialog moneyDialog = new SwingMoneyDialog(createCurrencyDialog());
        moneyDialog.getTextField().addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                SwingUtilities.invokeLater(() -> commands.get("calculate"));
            }
        });
        return moneyDialog;
    }

    private CurrencyDialog createCurrencyDialog() {
        return new SwingCurrencyDialog();
    }

    private MoneyDisplay createMoneyDisplay() {
        return new SwingMoneyDisplay();
    }

    public MoneyDialog getMoneyDialog() {
        return moneyDialog;
    }

    public CurrencyDialog getCurrencyDialog() {
        return currencyDialog;
    }

    public MoneyDisplay getMoneyDisplay() {
        return moneyDisplay;
    }

    public MainFrame defineCommands(Map<String, Command> commands) {
        this.commands = commands;
        ((SwingMoneyDialog) moneyDialog).getTextField().addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                SwingUtilities.invokeLater(() -> {
                    commands.get("calculate").execute();
                });
            }
        });
        return this;
    }

    private Component toolbar() {
        JPanel panel = new JPanel(new GridLayout(1, 4, 0, 0));
        panel.add(createCalculateButton());
        panel.add(createInvertButton());
        panel.add(createToggleCurrencyRepresentationButton());
        panel.add(createToggleCurrencyOrderButton());
        return panel;
    }

    private Component createInvertButton() {
        CustomizedButton button = new CustomizedButton("Invert");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                commands.get("invert").execute();
                commands.get("calculate").execute();
            }
        });
        return button;
    }

    private Component createToggleCurrencyRepresentationButton() {
        CustomizedButton button = new CustomizedButton("Toggle Repr.");
        button.addActionListener(e -> commands.get("toggle_representation").execute());
        return button;
    }

    private Component createToggleCurrencyOrderButton() {
        CustomizedButton button = new CustomizedButton("Toggle Order");
        button.addActionListener(e -> commands.get("toggle_order").execute());
        return button;
    }

    private Component createCalculateButton() {
        CustomizedButton button = new CustomizedButton("Calculate");
        button.addActionListener(e -> commands.get("calculate").execute());
        return button;
    }
}
