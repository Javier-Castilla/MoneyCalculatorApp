package software.ulpgc.moneycalculator.apps.windows.view;

import software.ulpgc.moneycalculator.apps.windows.view.customization.components.CustomSwingOptionButton;
import software.ulpgc.moneycalculator.apps.windows.view.customization.components.CustomSwingSeparator;
import software.ulpgc.moneycalculator.architecture.control.Command;
import software.ulpgc.moneycalculator.architecture.control.Setting;
import software.ulpgc.moneycalculator.architecture.model.Currency;
import software.ulpgc.moneycalculator.architecture.view.DateDialog;
import software.ulpgc.moneycalculator.architecture.view.ExchangeRateDisplay;
import software.ulpgc.moneycalculator.architecture.view.MoneyDialog;
import software.ulpgc.moneycalculator.apps.windows.view.customization.Colors;
import software.ulpgc.moneycalculator.apps.windows.view.customization.components.CustomSwingButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class MainFrame extends JFrame {
    private final SwingMoneyDialog fromMoneyDialog;
    private final SwingMoneyDialog toMoneyDialog;
    private final SwingExchangeRateDisplay exchangeRateDisplay;
    private Map<String, Command> commands;
    private Map<String, Setting> settings;
    private SwingDateDialog dateDialog;

    public MainFrame() throws HeadlessException {
        this.setUndecorated(true);
        this.setTitle("MoneyCalculator");
        this.setLayout(new BorderLayout());
        this.setSize(800, 400);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.add(BorderLayout.NORTH, createHeaderPanel());
        this.add(
                BorderLayout.CENTER, createExchangePanel(
                        this.exchangeRateDisplay  = createExchangeRateDisplay(),
                        this.fromMoneyDialog = createMoneyDialog(),
                        this.toMoneyDialog = createMoneyDialog()
                )
        );
        this.add(BorderLayout.SOUTH, createCurrentSettingsDisplay());
        this.dateDialog = new SwingDateDialog();
        setBackground(Colors.AlmostBlack.value());
    }

    private SwingSettingsDisplay createSettingsDisplay() {
        return new SwingSettingsDisplay(3);
    }

    private JPanel createFooterPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(Colors.AlmostWhite.value());
        panel.add(createCurrentSettingsDisplay());
        return panel;
    }

    private JPanel createCurrentSettingsDisplay() {
        JPanel panel = new JPanel(new FlowLayout());
        panel.setBackground(Colors.AlmostWhite.value());
        panel.add(new JLabel("Order mode: " + fromMoneyDialog.getCurrencyDialog().getOrderMode().name()));
        panel.add(new JLabel("Order direction: " + fromMoneyDialog.getCurrencyDialog().getOrderDirection().name()));
        panel.add(new JLabel("Exchanges date: " + LocalDate.now()));
        panel.add(new JLabel("Representation: " + Currency.CurrencyRepresentation.CurrencyName.name()));
        for (Component component : panel.getComponents()) {
            component.setFont(new Font("Arial", Font.BOLD, 9));
            ((JLabel) component).setHorizontalAlignment(SwingConstants.CENTER);
        }
        return panel;
    }

    private SwingExchangeRateDisplay createExchangeRateDisplay() {
        return new SwingExchangeRateDisplay();
    }

    private JPanel createHeaderPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Colors.AlmostBlack.value());
        panel.add(createDecoratedPanel());
        panel.add(createTitlePanel());
        return panel;
    }

    private JPanel createDecoratedPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 2, 0, 0));
        panel.setBackground(Colors.AlmostBlack.value());
        panel.add(createCloseButtonPanel());
        panel.add(createOptionsPanel());
        addDragListenerTo(panel);
        return panel;
    }

    private JPanel createOptionsPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        panel.setBackground(Colors.AlmostBlack.value());
        panel.add(createExchangeDateOptions());
        panel.add(createOrderOptions());
        panel.add(otherOptions());
        return panel;
    }

    private CustomSwingOptionButton createExchangeDateOptions() {
        CustomSwingOptionButton button = new CustomSwingOptionButton("Date");
        button.addOptions(
                createDateOptions()
        );
        return button;
    }

    private JMenuItem[] createDateOptions() {
        JMenuItem setCurrentDate = new JMenuItem("Set current date");
        setCurrentDate.addActionListener(e -> commands.get("current_date").execute());
        JMenuItem setCustomDate = new JMenuItem("Set custom date");
        setCustomDate.addActionListener(e -> commands.get("custom_date").execute());
        JMenuItem toggleDate = new JMenuItem("Toggle date");
        toggleDate.addActionListener(e -> commands.get("toggle_date").execute());
        return new JMenuItem[]{setCurrentDate, setCustomDate, toggleDate};
    }

    private JPanel createCloseButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panel.setBackground(Colors.AlmostBlack.value());
        panel.add(createCloseButton());
        return panel;
    }

    private CustomSwingOptionButton otherOptions() {
        CustomSwingOptionButton button = new CustomSwingOptionButton("Others");
        button.addOptions(
                createOtherItems()
        );
        return button;
    }

    private JMenuItem[] createOtherItems() {
        JMenuItem toggleRepresentation = new JMenuItem("Toggle representation");
        toggleRepresentation.addActionListener(e -> commands.get("toggle_representation").execute());
        JMenuItem invert = new JMenuItem("Invert");
        invert.addActionListener(e -> commands.get("invert").execute());
        return new JMenuItem[]{toggleRepresentation, invert};
    }

    private CustomSwingOptionButton createOrderOptions() {
        CustomSwingOptionButton button = new CustomSwingOptionButton("Order");
        button.addOptions(
                createOrderItems()
        );
        return button;
    }

    private JMenuItem[] createOrderItems() {
        JMenuItem orderByName = new JMenuItem("Order by name");
        orderByName.addActionListener(e -> {
            commands.get("name_order").execute();
            commands.get("set_order").execute();
        });
        JMenuItem orderByCode = new JMenuItem("Order by code");
        orderByCode.addActionListener(e -> {
            commands.get("code_order").execute();
            System.out.println(fromMoneyDialog.getCurrencyDialog().getOrderMode());
            commands.get("set_order").execute();
        });
        JMenuItem toggleOrder = new JMenuItem("Toggle order");
        toggleOrder.addActionListener(e -> commands.get("toggle_order").execute());
        return new JMenuItem[]{orderByName, orderByCode, toggleOrder};
    }

    private static CustomSwingButton createCloseButton() {
        CustomSwingButton button = new CustomSwingButton("X");
        button.setHoverColor(Color.RED);
        button.addActionListener(e -> System.exit(0));
        return button;
    }

    private void addDragListenerTo(JPanel panel) {
        final Point[] initialClick = {null};

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                initialClick[0] = e.getPoint();
            }
        });

        panel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (initialClick[0] != null) {
                    int deltaX = e.getXOnScreen() - initialClick[0].x;
                    int deltaY = e.getYOnScreen() - initialClick[0].y;
                    setLocation(deltaX, deltaY);
                }
            }
        });
    }

    private static JPanel createTitlePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Colors.AlmostBlack.value());
        panel.add(Box.createVerticalStrut(20));
        panel.add(createTitleLabel());
        panel.add(createNameLabel());
        panel.add(Box.createVerticalStrut(20));
        return panel;
    }

    private static JLabel createNameLabel() {
        JLabel name = new JLabel("Powered by Javier Castilla");
        name.setFont(new Font("Arial", Font.BOLD, 15));
        name.setAlignmentX(CENTER_ALIGNMENT);
        name.setForeground(Colors.AlmostWhite.value());
        return name;
    }

    private static JLabel createTitleLabel() {
        JLabel title = new JLabel("MoneyCalculator");
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setAlignmentX(CENTER_ALIGNMENT);
        title.setForeground(Colors.AlmostWhite.value());
        return title;
    }

    private JPanel createExchangePanel(SwingExchangeRateDisplay exchangeRateDisplay, SwingMoneyDialog fromMoneyDialog, SwingMoneyDialog toMoneyDialog) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Colors.AlmostWhite.value());
        panel.add(Box.createVerticalStrut(20));
        panel.add(exchangeRateDisplay);
        panel.add(fromMoneyDialog);
        panel.add(createSeparatorLabel());
        panel.add(toMoneyDialog);
        panel.add(Box.createVerticalStrut(20));
        return panel;
    }

    private static JPanel createSeparatorLabel() {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("=");
        label.setForeground(Colors.AlmostBlack.value());
        label.setFont(new Font("Arial", Font.BOLD, 30));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(BorderLayout.CENTER, label);
        panel.setBackground(Colors.AlmostWhite.value());
        return panel;
    }

    private SwingMoneyDialog createMoneyDialog() {
        SwingMoneyDialog moneyDialog = new SwingMoneyDialog(createCurrencyDialog());
        moneyDialog.getAmountEntry().addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                SwingUtilities.invokeLater(() -> commands.get("calculate").execute());
            }
        });
        return moneyDialog;
    }

    private SwingCurrencyDialog createCurrencyDialog() {
        SwingCurrencyDialog currencyDialog = new SwingCurrencyDialog();
        currencyDialog.getSelector().addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED && commands != null) {
                commands.get("display_exchangeRate").execute();
                commands.get("calculate").execute();
            }
        });
        return currencyDialog;
    }

    public MoneyDialog getFromMoneyDialog() {
        return fromMoneyDialog;
    }

    public MoneyDialog getToMoneyDialog() {
        return toMoneyDialog;
    }

    private JPanel createDateDialogPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(Colors.AlmostBlack.value());
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(Box.createVerticalStrut(20));
        panel.add(createDateDialog());
        panel.add(Box.createVerticalStrut(20));
        return panel;
    }

    private SwingDateDialog createDateDialog() {
        SwingDateDialog dateDialog = new SwingDateDialog();
        dateDialog.getButton().addActionListener(e -> commands.get("load_rates").execute());
        return dateDialog;
    }

    private JPanel createOrderOptionsPanel() {
        JPanel panel = new JPanel(new GridLayout(5, 1));
        panel.setBackground(Colors.AlmostBlack.value());
        panel.add(createInvertButton());
        panel.add(createToggleCurrencyRepresentationButton());
        ButtonGroup grp = createButtonGroup();
        panel.add(new CustomSwingSeparator());
        grp.getElements().asIterator().forEachRemaining(panel::add);
        return panel;
    }

    private ButtonGroup createButtonGroup() {
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(createNameOrderButton());
        buttonGroup.add(createCodeOrderButton());
        buttonGroup.setSelected(buttonGroup.getElements().nextElement().getModel(), true);
        return buttonGroup;
    }

    private CustomSwingButton createCodeOrderButton() {
        CustomSwingButton button = new CustomSwingButton("OrderMode by code");
        button.addActionListener(e -> commands.get("order_by_code").execute());
        return button;
    }

    private CustomSwingButton createNameOrderButton() {
        CustomSwingButton button = new CustomSwingButton("OrderMode by name");
        button.addActionListener(e -> commands.get("order_by_name").execute());
        return button;
    }

    private Component createInvertButton() {
        CustomSwingButton button = new CustomSwingButton("Invert");
        button.addActionListener(e -> commands.get("invert").execute());
        return button;
    }

    private Component createToggleCurrencyRepresentationButton() {
        CustomSwingButton button = new CustomSwingButton("Toggle Representation");
        button.addActionListener(e -> commands.get("toggle_representation").execute());
        return button;
    }

    public DateDialog getDateDialog() {
        return dateDialog;
    }

    public MainFrame defineCurrencies(List<Currency> currencies) {
        fromMoneyDialog.define(currencies);
        toMoneyDialog.define(currencies);
        return this;
    }

    public MainFrame defineCommands(Map<String, Command> commands) {
        this.commands = commands;
        return this;
    }

    public MainFrame defineSettings(Map<String, Setting> settings) {
        this.settings = settings;
        return this;
    }

    public MainFrame put(String name, Command command) {
        commands.put(name, command);
        return this;
    }

    public ExchangeRateDisplay getExchangeRateDisplay() {
        return exchangeRateDisplay;
    }
}
