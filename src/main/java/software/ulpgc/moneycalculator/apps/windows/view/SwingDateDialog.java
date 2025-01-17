package software.ulpgc.moneycalculator.apps.windows.view;

import software.ulpgc.moneycalculator.apps.windows.view.customization.Colors;
import software.ulpgc.moneycalculator.apps.windows.view.customization.components.CustomSwingButton;
import software.ulpgc.moneycalculator.apps.windows.view.customization.components.SwingDateEntry;
import software.ulpgc.moneycalculator.architecture.control.CommandFactory;
import software.ulpgc.moneycalculator.architecture.view.DateDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.time.LocalDate;


public class SwingDateDialog extends JFrame implements DateDialog {
    private final SwingDateEntry dateEntry;
    private final CustomSwingButton button;
    private final CommandFactory commandFactory;
    private DateMode dateMode;

    public SwingDateDialog(CommandFactory commandFactory) {
        this.commandFactory = commandFactory;
        this.dateMode = DateMode.Current;
        add(createDialogPanel(createDialogComponent(this.dateEntry = withListenerAddition(new SwingDateEntry()), this.button = createLoadButton())));
        setSize(350, 200);
        setBackground(Colors.AlmostBlack.value());
    }

    private JPanel createDialogPanel(JPanel dialogComponent) {
        JPanel panel = new JPanel();
        panel.setBackground(Colors.AlmostBlack.value());
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(Box.createVerticalStrut(50));
        panel.add(dialogComponent);
        panel.add(Box.createVerticalStrut(50));
        return panel;
    }

    private JPanel createDialogComponent(SwingDateEntry dateEntry, CustomSwingButton loadButton) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.add(Box.createHorizontalStrut(5));
        panel.add(createLabel());
        panel.add(Box.createHorizontalStrut(5));
        panel.add(createVerticalSeparator());
        panel.add(dateEntry);
        panel.add(loadButton);
        panel.setBackground(Colors.AlmostBlack.value());
        panel.setBorder(BorderFactory.createLineBorder(Colors.AlmostWhite.value(), 1));
        return reSizePanel(panel);
    }

    private JPanel reSizePanel(JPanel panel) {
        Dimension dimension = new Dimension();
        for (Component component : panel.getComponents()) {
            Dimension componentDimension = component.getPreferredSize();
            dimension.width += (int) componentDimension.getWidth();
            dimension.height = (int) Math.max(dimension.getHeight(), componentDimension.getHeight());
        }
        panel.setPreferredSize(dimension);
        panel.setMaximumSize(dimension);
        return panel;
    }

    private JSeparator createVerticalSeparator() {
        JSeparator separator = new JSeparator(SwingConstants.VERTICAL);
        separator.setForeground(Colors.AlmostWhite.value());
        separator.setBackground(Colors.AlmostWhite.value());
        separator.setMinimumSize(new Dimension(20, 17));
        separator.setPreferredSize(new Dimension(20, 17));
        separator.setMaximumSize(new Dimension(20, 17));
        return separator;
    }

    private static JLabel createLabel() {
        JLabel label = new JLabel("DATE ");
        label.setForeground(Colors.AlmostWhite.value());
        return label;
    }

    private CustomSwingButton createLoadButton() {
        CustomSwingButton button = new CustomSwingButton("Load");
        button.setBackground(Colors.AlmostWhite.value());
        button.setForeground(Colors.AlmostBlack.value());
        button.setNormalColor(Colors.AlmostWhite.value());
        button.setHoverColor(Colors.HoverWhite.value());
        button.setPressedColor(Colors.PressedWhite.value());
        button.addActionListener(e -> {
            commandFactory.get("load_rates").build().execute();
            commandFactory.get("display_exchangeRate").build().execute();
            commandFactory.get("calculate").build().execute();
            setVisible(false);
        });
        return button;
    }

    private SwingDateEntry withListenerAddition(SwingDateEntry swingDateEntry) {
        swingDateEntry.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                if (swingDateEntry.getText().equals("dd-mm-yyyy"))
                    swingDateEntry.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                if (swingDateEntry.getText().isEmpty())
                    swingDateEntry.setText("dd-mm-yyyy");
            }
        });
        return swingDateEntry;
    }

    @Override
    public LocalDate get() {
        String[] splitDate = dateEntry.getText().split("-");
        return LocalDate.of(toInteger(splitDate[2]), toInteger(splitDate[1]), toInteger(splitDate[0]));
    }

    private int toInteger(String string) {
        return Integer.parseInt(string);
    }

    @Override
    public DateMode getDateMode() {
        return this.dateMode;
    }

    @Override
    public void setDateMode(DateMode dateMode) {
        this.dateMode = dateMode;
    }

    @Override
    public void request() {
        setVisible(true);
    }

    public SwingDateEntry getDateEntry() {
        return dateEntry;
    }

    public CustomSwingButton getButton() {
        return button;
    }
}
