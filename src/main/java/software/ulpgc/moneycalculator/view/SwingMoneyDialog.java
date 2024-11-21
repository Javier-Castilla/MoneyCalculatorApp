package software.ulpgc.moneycalculator.view;

import software.ulpgc.moneycalculator.control.Command;
import software.ulpgc.moneycalculator.model.Currency;
import software.ulpgc.moneycalculator.model.Money;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Map;

public class SwingMoneyDialog extends JPanel implements MoneyDialog {
    private JTextField textField;
    private final CurrencyDialog currencyDialog;

    public SwingMoneyDialog(CurrencyDialog currencyDialog) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(createMoneyEntry());
        add((Component) (this.currencyDialog = currencyDialog));
        setMaximumSize(new Dimension(600, 100));
    }

    private Component createMoneyEntry() {
        JPanel panel = new JPanel(new FlowLayout());
        JLabel label = new JLabel("Amount: ");
        panel.add(label);
        this.textField = new JTextField();
        panel.add(textField);
        textField.setColumns(20);
        textField.setMaximumSize(new Dimension(300, 20));
        return panel;
    }

    @Override
    public Money get() {
        double amount = Double.parseDouble(textField.getText());
        Currency currency = currencyDialog.get();
        return new Money(amount, currency);
    }

    @Override
    public MoneyDialog define(Map<String, Currency> currencies) {
        currencyDialog.define("From: ", currencies);
        return this;
    }

    @Override
    public CurrencyDialog getCurrencyDialog() {
        return currencyDialog;
    }

    @Override
    public MoneyDialog set(Currency currency) {
        currencyDialog.set(currency);
        return this;
    }

    public JTextField getTextField() {
        return textField;
    }
}
