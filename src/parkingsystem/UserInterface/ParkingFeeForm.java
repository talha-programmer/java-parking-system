/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parkingsystem.UserInterface;

import parkingsystem.BusinessLogic.ParkingLot;
import parkingsystem.Enums.VehicleTypes;
import parkingsystem.Utility.NumberFormatting;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author talha
 */
public class ParkingFeeForm extends javax.swing.JFrame {
    private ArrayList<ParkingLot> parkingLots = null;
    private HashMap<String, Integer> parkingLotNames = null;     // Used to store parking lot names with ids for combo box
    private int selectedParkingLotId = -1;

    /**
     * Creates new form ParkingFeeForm
     */
    public ParkingFeeForm() {
        parkingLotNames = new HashMap<>();
        initComponents();

        updateFrame();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnAddParkingFee = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        ftfBikeFee = new javax.swing.JFormattedTextField();
        ftfRickshawFee = new javax.swing.JFormattedTextField();
        ftfCarFee = new javax.swing.JFormattedTextField();
        ftfHeavyVehicleFee = new javax.swing.JFormattedTextField();
        btnSave = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        cbParkingLotNamesUpdate = new javax.swing.JComboBox<>();
        pnDeleteParkingFee = new javax.swing.JPanel();
        cbParkingLotNamesDelete = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Parking Lot");

        jLabel2.setText("Bike Fee");

        jLabel3.setText("Rickshaw Fee");

        jLabel4.setText("Car Fee");

        jLabel5.setText("Heavy Vehicle Fee");

        ftfBikeFee.setFormatterFactory(NumberFormatting.intFormatterFactory());

        ftfRickshawFee.setFormatterFactory(NumberFormatting.intFormatterFactory());

        ftfCarFee.setFormatterFactory(NumberFormatting.intFormatterFactory());

        ftfHeavyVehicleFee.setFormatterFactory(NumberFormatting.intFormatterFactory());

        btnSave.setText("Save");

        btnClear.setText("Clear");

        cbParkingLotNamesUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbParkingLotNamesUpdateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnAddParkingFeeLayout = new javax.swing.GroupLayout(pnAddParkingFee);
        pnAddParkingFee.setLayout(pnAddParkingFeeLayout);
        pnAddParkingFeeLayout.setHorizontalGroup(
            pnAddParkingFeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnAddParkingFeeLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(pnAddParkingFeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(pnAddParkingFeeLayout.createSequentialGroup()
                        .addComponent(btnClear)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSave))
                    .addGroup(pnAddParkingFeeLayout.createSequentialGroup()
                        .addGroup(pnAddParkingFeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(29, 29, 29)
                        .addGroup(pnAddParkingFeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ftfHeavyVehicleFee, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ftfCarFee, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ftfRickshawFee, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ftfBikeFee, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbParkingLotNamesUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(95, Short.MAX_VALUE))
        );
        pnAddParkingFeeLayout.setVerticalGroup(
            pnAddParkingFeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnAddParkingFeeLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(pnAddParkingFeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cbParkingLotNamesUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnAddParkingFeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(ftfBikeFee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(pnAddParkingFeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(ftfRickshawFee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(pnAddParkingFeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(ftfCarFee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnAddParkingFeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(ftfHeavyVehicleFee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(pnAddParkingFeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave)
                    .addComponent(btnClear))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        jLabel7.setText("Parking Lot");

        javax.swing.GroupLayout pnDeleteParkingFeeLayout = new javax.swing.GroupLayout(pnDeleteParkingFee);
        pnDeleteParkingFee.setLayout(pnDeleteParkingFeeLayout);
        pnDeleteParkingFeeLayout.setHorizontalGroup(
            pnDeleteParkingFeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnDeleteParkingFeeLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addComponent(cbParkingLotNamesDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
        );
        pnDeleteParkingFeeLayout.setVerticalGroup(
            pnDeleteParkingFeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnDeleteParkingFeeLayout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addGroup(pnDeleteParkingFeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbParkingLotNamesDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addContainerGap(75, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnAddParkingFee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 327, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnDeleteParkingFee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnAddParkingFee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnDeleteParkingFee, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cbParkingLotNamesUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbParkingLotNamesUpdateActionPerformed
        selectedParkingLotId = -1;
        if(cbParkingLotNamesUpdate.getSelectedItem()!= null) {
            String parkingLotName = cbParkingLotNamesUpdate.getSelectedItem().toString();
            if (!parkingLotName.contains("Select")) {
                selectedParkingLotId = parkingLotNames.get(parkingLotName);

            }
        }
    }//GEN-LAST:event_cbParkingLotNamesUpdateActionPerformed


    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ParkingFeeForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ParkingFeeForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ParkingFeeForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ParkingFeeForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ParkingFeeForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox<String> cbParkingLotNamesDelete;
    private javax.swing.JComboBox<String> cbParkingLotNamesUpdate;
    private javax.swing.JFormattedTextField ftfBikeFee;
    private javax.swing.JFormattedTextField ftfCarFee;
    private javax.swing.JFormattedTextField ftfHeavyVehicleFee;
    private javax.swing.JFormattedTextField ftfRickshawFee;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel pnAddParkingFee;
    private javax.swing.JPanel pnDeleteParkingFee;
    // End of variables declaration//GEN-END:variables

    private void updateFrame(){
        parkingLots = new ParkingLot().getAllParkingLot();
        int totalParkingLots = parkingLots.size();
        Object[][] data = new Object[totalParkingLots][];
        Object[] columns = {"Sr.No", "Name", "Location", "Bike Capacity", "Rickshaw Capacity", "Car Capacity", "Heavy Vehicle Capacity"};

        cbParkingLotNamesDelete.removeAllItems();     // Remove all items before adding new to avoid duplicates
        cbParkingLotNamesUpdate.removeAllItems();

        cbParkingLotNamesUpdate.addItem("...Select Item...");
        cbParkingLotNamesUpdate.setSelectedIndex(0);

        // Load details of all Parking Lots in the table
        int count = 0;
        for(ParkingLot parkingLot: parkingLots){
            int id = parkingLot.getId();
            String name = parkingLot.getName();
            String location = parkingLot.getLocation();
            parkingLotNames.put(name, id);

            // Adding ParkingLot names in combo box
            cbParkingLotNamesDelete.addItem(name);
            cbParkingLotNamesUpdate.addItem(name);


            /*Object[] row = {count+1, name, location, bikeCapacity, rickShawCapacity, carCapacity, heavyVehicleCapacity};
            data[count] = row;
            count++;*/
        }
        /*tbParkingLot.setModel(new javax.swing.table.DefaultTableModel(
                data, columns
        ));*/


    }
}