package software.ulpgc.moneycalculator.apps.windows.view;

import software.ulpgc.moneycalculator.architecture.model.Currency;
import software.ulpgc.moneycalculator.architecture.model.Money;
import software.ulpgc.moneycalculator.architecture.view.CurrencyDialog;
import software.ulpgc.moneycalculator.architecture.view.MoneyDialog;
import software.ulpgc.moneycalculator.apps.windows.view.customization.Colors;
import software.ulpgc.moneycalculator.apps.windows.view.customization.components.SwingAmountEntry;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.Locale;

public class SwingMoneyDialog extends JPanel implements MoneyDialog {
    private final SwingAmountEntry amountEntry;
    private final SwingCurrencyDialog currencyDialog;

    public SwingMoneyDialog(SwingCurrencyDialog currencyDialog) {
        setBorder(BorderFactory.createLineBorder(Colors.AlmostBlack.value(), 1));
        setBackground(Colors.AlmostWhite.value());
        setLayout(new GridBagLayout()); // Cambiar a GridBagLayout

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 2, 2, 2); // Espaciado entre componentes
        gbc.fill = GridBagConstraints.BOTH; // Ajustar componentes al espacio disponible

        // Agregar el campo de entrada de dinero (SwingAmountEntry)
        gbc.gridx = 0; // Columna 0
        gbc.weightx = 0.4; // Peso horizontal para ajustar el espacio
        gbc.gridy = 0; // Fila 0
        add(createMoneyEntry(this.amountEntry = new SwingAmountEntry()), gbc);

        // Agregar el separador vertical
        gbc.gridx = 1; // Columna 1
        gbc.weightx = 0; // Sin redimensionar horizontalmente
        gbc.fill = GridBagConstraints.VERTICAL; // Rellenar solo verticalmente
        add(createVerticalSeparator(), gbc);

        // Agregar el selector de divisas (currencyDialog)
        gbc.gridx = 2; // Columna 2
        gbc.weightx = 0.6; // Peso horizontal para ajustar el espacio
        gbc.fill = GridBagConstraints.BOTH; // Rellenar completamente
        add(this.currencyDialog = currencyDialog, gbc);

        // Establecer el tamaño máximo
        setMaximumSize(new Dimension(600, 100));
    }


    private JSeparator createVerticalSeparator() {
        JSeparator jSeparator = new JSeparator(SwingConstants.VERTICAL);
        jSeparator.setForeground(Colors.AlmostBlack.value());
        jSeparator.setBackground(Colors.AlmostBlack.value());
        jSeparator.setMinimumSize(new Dimension(10, 20));
        jSeparator.setPreferredSize(new Dimension(10, 20));
        return jSeparator;
    }

    private JPanel createMoneyEntry(SwingAmountEntry amountEntry) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Colors.AlmostWhite.value());
        panel.add(addTextFieldListeners(amountEntry));
        return panel;
    }

    private SwingAmountEntry addTextFieldListeners(SwingAmountEntry amountEntry) {
        amountEntry.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                if (amountEntry.getText().equals("0.00"))
                    amountEntry.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                if (amountEntry.getText().isEmpty())
                    amountEntry.setText("0.00");
                else
                    set(get());
            }
        });

        amountEntry.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER)
                    requestFocus();
            }
        });
        return amountEntry;
    }

    @Override
    public Money get() {
        double amount = Double.parseDouble(amountEntry.getText());
        Currency currency = currencyDialog.get();
        return new Money(amount, currency);
    }

    @Override
    public void define(List<Currency> currencies) {
        currencyDialog.define(currencies);
    }

    @Override
    public CurrencyDialog getCurrencyDialog() {
        return currencyDialog;
    }

    @Override
    public void set(Money money) {
        amountEntry.setText(String.format(Locale.US, "%.2f", money.amount()));
        currencyDialog.set(money.currency());
        revalidate();
    }

    @Override
    public boolean isFocused() {
        return this.amountEntry.hasFocus();
    }

    public SwingAmountEntry getAmountEntry() {
        return amountEntry;
    }
}
