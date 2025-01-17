package software.ulpgc.moneycalculator.apps.windows.view;

import software.ulpgc.moneycalculator.apps.windows.view.customization.Colors;
import software.ulpgc.moneycalculator.architecture.model.ExchangeRate;
import software.ulpgc.moneycalculator.architecture.view.ExchangeRateDisplay;

import javax.swing.*;
import java.awt.*;

public class SwingExchangeRateDisplay extends JPanel implements ExchangeRateDisplay {
    private final JLabel label;

    public SwingExchangeRateDisplay() {
        this.setBackground(Colors.AlmostWhite.value());
        add(this.label = createLabel());
        label.setForeground(Colors.AlmostBlack.value());
        label.setFont(new Font("Arial", Font.BOLD, 14));
    }

    private JLabel createLabel() {
        return new JLabel();
    }

    @Override
    public void show(ExchangeRate exchangeRate) {
        updateMoneyLabel(exchangeRate);
    }

    private void updateMoneyLabel(ExchangeRate exchangeRate) {
        label.setText(String.format("1 %s is equivalent to %.2f %s", exchangeRate.from_currency().code(), exchangeRate.rate(), exchangeRate.to_currency().name()));
    }
}
