package software.ulpgc.moneycalculator.apps.windows.view;

import software.ulpgc.moneycalculator.apps.windows.io.pojo.ProgramInformation;
import software.ulpgc.moneycalculator.apps.windows.view.customization.components.CustomSwingOptionButton;
import software.ulpgc.moneycalculator.architecture.control.CommandFactory;
import software.ulpgc.moneycalculator.architecture.model.Currency;
import software.ulpgc.moneycalculator.architecture.view.DateDialog;
import software.ulpgc.moneycalculator.architecture.view.ExchangeRateDisplay;
import software.ulpgc.moneycalculator.architecture.view.MoneyDialog;
import software.ulpgc.moneycalculator.apps.windows.view.customization.Colors;
import software.ulpgc.moneycalculator.apps.windows.view.customization.components.CustomSwingButton;
import software.ulpgc.moneycalculator.architecture.view.ProgramInformationDisplay;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.Map;

public class MainFrame extends JFrame {
    private final MoneyDialog fromMoneyDialog;
    private final MoneyDialog toMoneyDialog;
    private final ExchangeRateDisplay exchangeRateDisplay;
    private final DateDialog dateDialog;
    private final ProgramInformationDisplay programInformationDisplay;
    private final CommandFactory commandFactory;

    public MainFrame(CommandFactory commandFactory) throws HeadlessException {
        this.commandFactory = commandFactory;
        this.dateDialog = new SwingDateDialog(commandFactory);
        setUpFrame();
        this.add(BorderLayout.NORTH, createDecoratedPanel());
        this.add(BorderLayout.CENTER, createExchangePanel(
                        this.exchangeRateDisplay  = createExchangeRateDisplay(),
                        this.fromMoneyDialog = createMoneyDialog(),
                        this.toMoneyDialog = createMoneyDialog()));
        this.add(BorderLayout.SOUTH, (Component) (this.programInformationDisplay = new SwingProgramInformationDisplay()));
    }

    private void setUpFrame() {
        this.setUndecorated(true);
        this.setTitle("MoneyCalculator");
        this.setLayout(new BorderLayout());
        this.setSize(800, 400);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setBackground(Colors.AlmostBlack.value());
    }

    private SwingExchangeRateDisplay createExchangeRateDisplay() {
        return new SwingExchangeRateDisplay();
    }

    private JPanel createDecoratedPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 0, 0, 0));
        panel.setBackground(Colors.AlmostBlack.value());
        panel.add(createCloseButton());
        panel.add(createTitleLabel());
        panel.add(createMenuPanel());
        addDragListenerTo(panel);
        return panel;
    }

    private JPanel createMenuPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(Colors.AlmostBlack.value());
        panel.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        panel.add(createExchangeDateOptions());
        panel.add(createOrderOptions());
        panel.add(otherOptions());
        return panel;
    }

    private CustomSwingOptionButton createExchangeDateOptions() {
        CustomSwingOptionButton button = new CustomSwingOptionButton("Date");
        button.addOptions(createDateOptions());
        return button;
    }

    private JMenuItem[] createDateOptions() {
        JMenuItem setCurrentDate = new JMenuItem("Set current date");
        JMenuItem setCustomDate = new JMenuItem("Set custom date");
        setCurrentDate.addActionListener(e -> {
            commandFactory.get("current_date").build().execute();
            commandFactory.get("display_exchangeRate").build().execute();
            commandFactory.get("calculate").build().execute();
        });
        setCustomDate.addActionListener(e -> commandFactory.get("custom_date").build().execute());
        return new JMenuItem[]{setCurrentDate, setCustomDate};
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
        JMenuItem invert = new JMenuItem("Invert");
        toggleRepresentation.addActionListener(e -> commandFactory.get("toggle_representation").build().execute());
        invert.addActionListener(e -> commandFactory.get("invert").build().execute());
        return new JMenuItem[]{toggleRepresentation, invert};
    }

    private CustomSwingOptionButton createOrderOptions() {
        CustomSwingOptionButton button = new CustomSwingOptionButton("Order");
        button.addOptions(createOrderItems());
        return button;
    }

    private JMenuItem[] createOrderItems() {
        JMenuItem orderByName = new JMenuItem("Order by name");
        JMenuItem orderByCode = new JMenuItem("Order by code");
        JMenuItem toggleOrder = new JMenuItem("Toggle order");
        orderByName.addActionListener(e -> {
            commandFactory.get("name_order").build().execute();
            commandFactory.get("set_order").build().execute();
        });
        orderByCode.addActionListener(e -> {
            commandFactory.get("code_order").build().execute();
            commandFactory.get("set_order").build().execute();
        });
        toggleOrder.addActionListener(e -> commandFactory.get("toggle_order").build().execute());
        return new JMenuItem[]{orderByName, orderByCode, toggleOrder};
    }

    private static JPanel createCloseButton() {
        JPanel panel = new JPanel();
        panel.setBackground(Colors.AlmostBlack.value());
        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        CustomSwingButton button = new CustomSwingButton("X");
        button.setHoverColor(Color.RED);
        button.addActionListener(e -> System.exit(0));
        panel.add(button);
        return panel;
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

    private static JLabel createTitleLabel() {
        JLabel title = new JLabel("MoneyCalculator");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setAlignmentX(CENTER_ALIGNMENT);
        title.setForeground(Colors.AlmostWhite.value());
        return title;
    }

    private JPanel createExchangePanel(ExchangeRateDisplay exchangeRateDisplay, MoneyDialog fromMoneyDialog, MoneyDialog toMoneyDialog) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Colors.AlmostWhite.value());
        panel.add(Box.createVerticalStrut(50));
        panel.add((Component) exchangeRateDisplay);
        panel.add((Component) fromMoneyDialog);
        panel.add(createSeparatorLabel());
        panel.add((Component) toMoneyDialog);
        panel.add(Box.createVerticalStrut(50));
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
                SwingUtilities.invokeLater(() -> commandFactory.get("calculate").build().execute());
            }
        });
        return moneyDialog;
    }

    private SwingCurrencyDialog createCurrencyDialog() {
        SwingCurrencyDialog currencyDialog = new SwingCurrencyDialog();
        currencyDialog.getSelector().addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED && isVisible()) {
                commandFactory.get("display_exchangeRate").build().execute();
                commandFactory.get("calculate").build().execute();
            }
        });
        return currencyDialog;
    }

    public MainFrame defineCurrencies(List<Currency> currencies) {
        fromMoneyDialog.define(currencies);
        toMoneyDialog.define(currencies);
        return this;
    }

    public MainFrame defineCommands(Map<String, CommandFactory.Builder> builders) {
        builders.forEach(this::put);
        return this;
    }

    public MainFrame defineProgramInformation(ProgramInformation programInformation) {
        programInformationDisplay.showInformation(programInformation);
        return this;
    }

    public void put(String name, CommandFactory.Builder builder) {
        commandFactory.register(name, builder);
    }

    public MoneyDialog getFromMoneyDialog() {
        return fromMoneyDialog;
    }

    public MoneyDialog getToMoneyDialog() {
        return toMoneyDialog;
    }

    public DateDialog getDateDialog() {
        return dateDialog;
    }

    public ExchangeRateDisplay getExchangeRateDisplay() {
        return exchangeRateDisplay;
    }

    @Override
    public void setVisible(boolean b) {
        super.setVisible(b);
        commandFactory.get("display_exchangeRate").build().execute();
    }
}
