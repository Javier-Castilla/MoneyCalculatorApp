package software.ulpgc.moneycalculator.architecture.control;

import software.ulpgc.moneycalculator.architecture.view.SettingDisplay;

public class DisplaySettingsCommand implements Command {
    private final SettingDisplay display;

    public DisplaySettingsCommand(SettingDisplay display) {
        this.display = display;
    }

    @Override
    public void execute() {
        // display.show();
    }
}
