package software.ulpgc.moneycalculator.apps.windows.view.customization.components;

import software.ulpgc.moneycalculator.apps.windows.view.customization.Colors;

import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

public class CustomSwingScrollBarUI extends BasicScrollBarUI {
    @Override
    protected void configureScrollBarColors() {
        this.thumbColor = Colors.PressedBlack.value(); // Color del "pulgar" (la barra que se mueve)
        this.trackColor = Color.LIGHT_GRAY; // Color del fondo de la barra
    }
}
