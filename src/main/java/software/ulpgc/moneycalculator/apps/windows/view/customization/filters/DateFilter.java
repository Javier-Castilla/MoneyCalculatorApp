package software.ulpgc.moneycalculator.apps.windows.view.customization.filters;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class DateFilter extends DocumentFilter {
    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
        if (isValidInput(fb, offset, 0, string)) {
            super.insertString(fb, offset, normalizeString(fb, string), attr);
        }
    }

    private String normalizeString(FilterBypass fb, String string) {
        int length = fb.getDocument().getLength();
        if (length == 1 || length == 4)
            return string + "-";
        else
            return string;
    }


    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        if (isValidInput(fb, offset, length, text)) {
            super.replace(fb, offset, length, normalizeString(fb, text), attrs);
        }
    }

    @Override
    public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
        super.remove(fb, offset, length);
        if (offset == 2 || offset == 5)
            super.remove(fb, offset - 1, length);
    }

    private boolean isValidInput(FilterBypass fb, int offset, int length, String input) throws BadLocationException {
        String currentText = fb.getDocument().getText(0, fb.getDocument().getLength());
        String resultText = currentText.substring(0, offset) + input + currentText.substring(offset + length);
        return resultText.matches("(([0-3]\\b|0[1-9]|[1-2][0-9]|3[01])(-|-([01]\\b|0[1-9]|1[0-2])|-([01]\\b|0[1-9]|1[0-2])-([0-9]{0,4})?)?|dd-mm-yyyy|)");
    }
}
