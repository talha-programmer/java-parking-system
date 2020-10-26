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

    /**
     * Show confirmation dialog
     * @return 0: Yes, 1: No
    * */
    public static int displayConfirmDialog(String message){
        return JOptionPane.showConfirmDialog(null, message, "Please Confirm!", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
    }
}
