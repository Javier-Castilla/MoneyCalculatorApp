package software.ulpgc.moneycalculator.apps.windows.view;

import software.ulpgc.moneycalculator.architecture.view.SettingDisplay;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class SwingSettingsDisplay extends JPanel implements SettingDisplay {
    private final int settingsCount;
    private final Map<Integer, JLabel> labels;

    public SwingSettingsDisplay(int settingsCount) {
        this.settingsCount = settingsCount;
        this.labels = buildLabels();
        setLayout(new GridLayout(1, settingsCount, 0, 0));
        addLabels();
    }

    private void addLabels() {
        labels.values().forEach(this::add);
    }

    private Map<Integer, JLabel> buildLabels() {
        Map<Integer, JLabel> settingsStrings = new HashMap<>();
        for (int i = 0; i < settingsCount; i++) {
            settingsStrings.put(i, new JLabel(""));
        }
        return settingsStrings;
    }

    @Override
    public SettingDisplay put(int settingPosition, String settingString) {
        labels.get(settingPosition).setText(settingString);
        return this;
    }

    @Override
    public String get(int settingPosition) {
        return labels.get(settingPosition).getText();
    }
}
