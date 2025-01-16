package software.ulpgc.moneycalculator.architecture.control;

import software.ulpgc.moneycalculator.apps.windows.view.SettingsFrame;

public class OpenSettingsCommand implements Command {
    private SettingsFrame settingsFrame;
    @Override
    public void execute() {
        new SettingsFrame().setVisible(true);
    }
}
