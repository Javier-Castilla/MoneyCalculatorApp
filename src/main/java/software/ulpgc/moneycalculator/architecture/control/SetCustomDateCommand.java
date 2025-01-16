package software.ulpgc.moneycalculator.architecture.control;

import software.ulpgc.moneycalculator.architecture.view.DateDialog;

public class SetCustomDateCommand implements Command {
    private final DateDialog dateDialog;

    public SetCustomDateCommand(DateDialog dateDialog) {
        this.dateDialog = dateDialog;
    }

    @Override
    public void execute() {
        dateDialog.setDateMode(DateDialog.DateMode.Custom);
        dateDialog.request();
    }
}
