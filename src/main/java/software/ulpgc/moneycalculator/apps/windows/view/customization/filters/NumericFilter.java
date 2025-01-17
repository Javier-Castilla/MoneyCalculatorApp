package software.ulpgc.moneycalculator.apps.windows.view.customization.filters;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class NumericFilter extends DocumentFilter {
    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
        if (isValidInput(fb, string)) {
            super.insertString(fb, offset, string, attr);
        }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        if (isValidInput(fb, text)) {
            super.replace(fb, offset, length, text, attrs);
        }
    }

    private boolean isValidInput(FilterBypass fb, String input) throws BadLocationException {
        String currentText = fb.getDocument().getText(0, fb.getDocument().getLength());

        if (input.equals(".")) {
            return !currentText.contains(".") && currentText.length() > 0;
        }

        return input.matches("(^0\\.00$|\\d+.\\d+|\\d|)");
    }
}
