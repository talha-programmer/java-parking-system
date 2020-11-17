package parkingsystem.Utility;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;

import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
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


    public static void printPanel(JPanel panel){
        // Create PrinterJob Here
        PrinterJob printerJob = PrinterJob.getPrinterJob();
        // Set Printer Job Name
        printerJob.setJobName("Print Record");
        // Set Printable
        printerJob.setPrintable(new Printable() {
            @Override
            public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
                // Check If No Printable Content
                if(pageIndex > 0){
                    return Printable.NO_SUCH_PAGE;
                }

                // Make 2D Graphics to map content
                Graphics2D graphics2D = (Graphics2D)graphics;
                // Set Graphics Translations
                // A Little Correction here Multiplication was not working so I replaced with addition
                graphics2D.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
                // This is a page scale. Default should be 0.3 I am using 0.5
                graphics2D.scale(0.7, 0.7);

                // Now paint panel as graphics2D
                panel.paint(graphics2D);

                // return if page exists
                return Printable.PAGE_EXISTS;
            }
        });
        // Store printerDialog as boolean
        boolean returningResult = printerJob.printDialog();
        // check if dilog is showing
        if(returningResult){
            // Use try catch exeption for failure
            try{
                // Now call print method inside printerJob to print
                printerJob.print();
            }catch (PrinterException printerException){
                DisplayMessage.displayError("Print Error: " + printerException.getMessage());
            }
        }
    }
}
