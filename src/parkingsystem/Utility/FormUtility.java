package parkingsystem.Utility;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;

import java.util.HashMap;

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

    /**
     * Check if the fields are empty and generate error message for all
     * fields that are empty.
     * */
    public static String errorMessageForRequiredFields(HashMap<String, String> requiredFields){
        String errorMessage = "";
        for(String fieldName: requiredFields.keySet()){
            if(requiredFields.get(fieldName).isBlank()){
                errorMessage += "- "+fieldName + " is required!\n";
            }
        }
        if(!errorMessage.isBlank()){
            errorMessage = "Please solve the following errors:\n" +errorMessage;
        }
        return errorMessage;
    }
}
