package software.ulpgc.moneycalculator.architecture.view;

import java.time.LocalDate;
import java.util.Date;

public interface DateDialog {
    LocalDate get();
    DateMode getDateMode();
    DateDialog setDateMode(DateMode dateMode);

    DateDialog request();

    enum DateMode {
        Current, Custom
    }
}
