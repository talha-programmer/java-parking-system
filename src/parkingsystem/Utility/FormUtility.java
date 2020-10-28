package parkingsystem.Utility;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;

public class FormUtility {
    public static void clearFields(JComponent component){
        Component[] components = component.getComponents();
        for (Component singleComponent : components) {
            if (singleComponent instanceof JTextField || singleComponent instanceof JTextArea) {
                JTextComponent specificObject = (JTextComponent) singleComponent;
                specificObject.setText("");
            }
        }
    }
}
