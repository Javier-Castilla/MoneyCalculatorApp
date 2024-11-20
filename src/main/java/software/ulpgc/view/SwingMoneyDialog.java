package software.ulpgc.view;

import software.ulpgc.moneycalculator.control.CalculateCommand;
import software.ulpgc.moneycalculator.control.Command;
import software.ulpgc.moneycalculator.model.Currency;
import software.ulpgc.moneycalculator.model.Money;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Map;

public class SwingMoneyDialog extends JPanel implements MoneyDialog {
    private final JTextField textField;
    private final CurrencyDialog currencyDialog;
    private Command calculateCommand;

    public SwingMoneyDialog(CurrencyDialog currencyDialog) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(textField = (JTextField) createMoneyEntry());
        add((Component) (this.currencyDialog = currencyDialog));
        setMaximumSize(new Dimension(600, 100));
    }

    private Component createMoneyEntry() {
        JTextField textField = new JTextField();
        textField.setColumns(20);
        textField.setMaximumSize(new Dimension(300, 20));
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                SwingUtilities.invokeLater(() -> {
                    calculateCommand.execute();
                });
            }
        });
        return textField;
    }

    @Override
    public Money get() {
        double amount = Double.parseDouble(textField.getText());
        Currency currency = currencyDialog.get();
        return new Money(amount, currency);
    }

    public MoneyDialog addCommand(Command command) {
        this.calculateCommand = command;
        return this;
    }

    @Override
    public MoneyDialog define(Map<String, Currency> currencies) {
        currencyDialog.define(currencies);
        return this;
    }

    public CurrencyDialog getCurrencyDialog() {
        return currencyDialog;
    }
}
