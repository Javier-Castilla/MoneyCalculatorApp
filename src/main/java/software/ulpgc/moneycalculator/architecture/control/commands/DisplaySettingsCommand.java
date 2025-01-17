package software.ulpgc.moneycalculator.architecture.control.commands;

import software.ulpgc.moneycalculator.architecture.control.Command;
import software.ulpgc.moneycalculator.architecture.view.ProgramInformationDisplay;

public class DisplaySettingsCommand implements Command {
    private final ProgramInformationDisplay display;

    public DisplaySettingsCommand(ProgramInformationDisplay display) {
        this.display = display;
    }

    @Override
    public void execute() {
        // display.show();
    }
}
