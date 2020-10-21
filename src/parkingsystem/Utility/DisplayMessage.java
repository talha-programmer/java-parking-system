package parkingsystem.Utility;

import javax.swing.*;

public class DisplayMessage extends JFrame {
    public static void displayError(String message){
        JOptionPane.showMessageDialog(null, message, "Error Occurred!", JOptionPane.ERROR_MESSAGE);
    }

    public static void displayInfo(String message){
        JOptionPane.showMessageDialog(null, message, "Information!", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void displayWarning(String message){
        JOptionPane.showMessageDialog(null, message, "Warning!", JOptionPane.WARNING_MESSAGE);
    }
}
