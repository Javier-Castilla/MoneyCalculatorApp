package software.ulpgc.view;

import software.ulpgc.moneycalculator.model.Money;

import javax.swing.*;

public class SwingMoneyDisplay extends JPanel implements MoneyDisplay {
    private final JLabel label;

    public SwingMoneyDisplay() {
        add(this.label = createLabel());
    }

    private JLabel createLabel() {
        return new JLabel("Money Result: ");
    }

    @Override
    public MoneyDisplay show(Money money) {
        updateMoneyLabel(money);
        return this;
    }

    private void updateMoneyLabel(Money money) {
        label.setText("Money result: " + money.amount() + " " + money.currency().code());
    }
}
