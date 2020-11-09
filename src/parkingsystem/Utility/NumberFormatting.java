package parkingsystem.Utility;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

public class NumberFormatting {

    public static JFormattedTextField.AbstractFormatterFactory intFormatterFactory() {

        class IntFormatter extends NumberFormatter{
            public IntFormatter(NumberFormat longFormat) {
                super(longFormat);
            }

            @Override
            public Object stringToValue(String text) throws ParseException {
                if(text.isEmpty()){
                    return null;
                }
                return super.stringToValue(text);
            }
        }


        NumberFormat longFormat = NumberFormat.getIntegerInstance();

        IntFormatter numberFormatter = new IntFormatter(longFormat);
        numberFormatter.setValueClass(Long.class); //optional, ensures you will always get a long value
        numberFormatter.setAllowsInvalid(false); //this is the key!!
        numberFormatter.setMinimum(0l); //Optional

        NumberFormatter finalNumberFormatter = numberFormatter;
        JFormattedTextField.AbstractFormatterFactory formatterFactory = new JFormattedTextField.AbstractFormatterFactory() {
            @Override
            public JFormattedTextField.AbstractFormatter getFormatter(JFormattedTextField tf) {
                return finalNumberFormatter;
            }
        };

        return formatterFactory;
    }

    public static JFormattedTextField.AbstractFormatterFactory floatFormatterFactory() {

        class FloatFormatter extends NumberFormatter{
            public FloatFormatter(NumberFormat longFormat) {
                super(longFormat);
            }

            @Override
            public Object stringToValue(String text) throws ParseException {
                if(text.isEmpty()){
                    return null;
                }
                return super.stringToValue(text);
            }
        }


        NumberFormat format = DecimalFormat.getInstance();
        format.setMinimumFractionDigits(2);
        format.setMaximumFractionDigits(2);

        FloatFormatter numberFormatter = new FloatFormatter(format);
        numberFormatter.setValueClass(Float.class); //optional, ensures you will always get a float value
        numberFormatter.setAllowsInvalid(false); //this is the key!!

        NumberFormatter finalNumberFormatter = numberFormatter;
        JFormattedTextField.AbstractFormatterFactory formatterFactory = new JFormattedTextField.AbstractFormatterFactory() {
            @Override
            public JFormattedTextField.AbstractFormatter getFormatter(JFormattedTextField tf) {
                return finalNumberFormatter;
            }
        };

        return formatterFactory;
    }

}
