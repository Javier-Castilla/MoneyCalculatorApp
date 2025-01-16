package software.ulpgc.moneycalculator.apps.windows.view;

import software.ulpgc.moneycalculator.apps.windows.view.customization.Colors;
import software.ulpgc.moneycalculator.apps.windows.view.customization.components.CustomSwingButton;
import software.ulpgc.moneycalculator.architecture.control.Command;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class SettingsFrame extends JFrame {
    private Map<String, Command> commands;

    public SettingsFrame() throws HeadlessException {
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setSize(400, 400);
        add(BorderLayout.NORTH, createDateDialog());
        add(createButtons());
    }

    private JPanel createButtons() {
        JPanel panel = new JPanel();
        panel.add(createInvertButton());
        panel.add(createToggleRepresentationButton());
        panel.setBackground(Colors.AlmostBlack.value());
        return panel;
    }

    private CustomSwingButton createToggleRepresentationButton() {
        CustomSwingButton button = new CustomSwingButton("Toggle Representation");
        button.addActionListener(e -> commands.get("toggle_representation").execute());
        return button;
    }

    private CustomSwingButton createInvertButton() {
        CustomSwingButton button = new CustomSwingButton("Invert");
        button.addActionListener(e -> commands.get("invert").execute());
        return button;
    }

    private JPanel createDateDialog() {
        JPanel panel = new JPanel();
        panel.add(new SwingDateDialog());
        panel.setBackground(Colors.AlmostBlack.value());
        return panel;
    }

    private SettingsFrame defineCommands(Map<String, Command> commands) {
        this.commands = commands;
        return this;
    }
}
