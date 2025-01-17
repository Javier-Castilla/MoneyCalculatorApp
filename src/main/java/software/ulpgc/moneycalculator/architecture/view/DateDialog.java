package software.ulpgc.moneycalculator.architecture.view;

import java.time.LocalDate;

public interface DateDialog {
    LocalDate get();

    DateMode getDateMode();

    void setDateMode(DateMode dateMode);

    void request();

    enum DateMode {
        Current, Custom
    }
}
