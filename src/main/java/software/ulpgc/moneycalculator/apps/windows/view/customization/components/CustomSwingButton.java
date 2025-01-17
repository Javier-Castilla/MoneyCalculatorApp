package software.ulpgc.moneycalculator.apps.windows.view.customization.components;

import software.ulpgc.moneycalculator.apps.windows.view.customization.Colors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CustomSwingButton extends JButton {
    private Color normalColor;
    private Color hoverColor;
    private Color pressedColor;
    public CustomSwingButton(String text) {
        setText(text);
        setBackground(Colors.AlmostBlack.value());
        setForeground(Color.WHITE);
        setBorderPainted(false);
        setFocusPainted(false);
        addHoverEffect();
        this.normalColor = Colors.AlmostBlack.value();
        this.hoverColor = Colors.HoverBlack.value();
        this.pressedColor = Colors.PressedBlack.value();
    }

    public Color getNormalColor() {
        return normalColor;
    }

    public void setNormalColor(Color normalColor) {
        this.normalColor = normalColor;
    }

    public Color getHoverColor() {
        return hoverColor;
    }

    public void setHoverColor(Color hoverColor) {
        this.hoverColor = hoverColor;
    }

    public Color getPressedColor() {
        return pressedColor;
    }

    public void setPressedColor(Color pressedColor) {
        this.pressedColor = pressedColor;
    }

    private void addHoverEffect() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(getHoverColor());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(getNormalColor());
            }

            @Override
            public void mousePressed(MouseEvent e) {
                setBackground(getPressedColor());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                setBackground(getNormalColor());
            }
        });
    }
}
