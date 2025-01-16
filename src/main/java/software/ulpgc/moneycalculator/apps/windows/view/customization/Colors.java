package software.ulpgc.moneycalculator.apps.windows.view.customization;

import java.awt.*;

public enum Colors {
    AlmostBlack(new Color(51, 51, 51)),
    HoverBlack(new Color(61, 61, 61)),
    PressedBlack(new Color(41, 41, 41)),
    LightGray(new Color(204, 204, 204)),
    AlmostWhite(new Color(236, 208, 227)),
    HoverWhite(new Color(230, 230, 230)),
    PressedWhite(new Color(255, 255, 255));

    private final Color color;

    Colors(Color color) {
        this.color = color;
    }

    public Color value() {
        return color;
    }
}