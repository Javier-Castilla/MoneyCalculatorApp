package software.ulpgc.moneycalculator.apps.windows.view.customization.components;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CustomSwingOptionButton extends CustomSwingButton {
    private final JPopupMenu popUp;

    public CustomSwingOptionButton(String text) {
        super(text);
        this.popUp = new JPopupMenu();
        addActionListener(e -> this.popUp.show(this, 0, getHeight()));
    }

    public CustomSwingOptionButton addOptions(JMenuItem... options) {
        for (JMenuItem item : options) this.popUp.add(item);
        return this;
    }
}
