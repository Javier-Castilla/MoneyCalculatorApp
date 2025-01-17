package software.ulpgc.moneycalculator.apps.windows.view;

import software.ulpgc.moneycalculator.apps.windows.io.pojo.ProgramInformation;
import software.ulpgc.moneycalculator.apps.windows.view.customization.Colors;
import software.ulpgc.moneycalculator.architecture.view.ProgramInformationDisplay;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class SwingProgramInformationDisplay extends JPanel implements ProgramInformationDisplay {
    public SwingProgramInformationDisplay() {
        this.setBackground(Colors.AlmostWhite.value());
    }

    @Override
    public ProgramInformationDisplay showInformation(ProgramInformation programInformation) {
        removeAll();
        Arrays.stream(programInformation.getClass().getDeclaredMethods())
                .filter(m ->m.getParameterCount() == 0 && m.getReturnType() == String.class && ! m.getName().equals("toString"))
                .toList().forEach(m -> addLabel(m, programInformation));
        revalidate();
        repaint();
        return this;
    }

    private void addLabel(Method m, ProgramInformation programInformation) {
        try {
            add(new JLabel((String) m.invoke(programInformation)));
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
