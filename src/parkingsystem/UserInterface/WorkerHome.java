/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parkingsystem.UserInterface;

import parkingsystem.BusinessLogic.*;
import parkingsystem.Enums.VehicleTypes;
import parkingsystem.Utility.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author talha
 */
public class WorkerHome extends javax.swing.JFrame {
    private static User loggedInUser = User.getLoggedInUser();
    private Vehicle vehicle = null;
    private ParkingLot selectedPL = null;
    private int selectedVehicleId = -1;
    private ParkingLotUtil plUtil = null;
    private PLAllocationUtil plAllocationUtil = null;
    private VehicleUtil vehicleUtil = null;
    private ParkedVehicleUtil parkedVehicleUtil = null;
    private HashMap<String, Integer> vehicleRegNumbers = null;      // Key: vehicle reg number, value: vehicle id
    private ArrayList<String> vehicleTypeNames = null;
    private HashMap<Integer, Integer> totalParkedVehicles = null;   // Key: vehicle type, value: total parked vehicles of that vehicle type
    private Inventory inventory = null;
    private int parkedVehicleId;

    /**
     * Creates new form WorkerHome
     */
    public WorkerHome() {
        vehicle = new Vehicle();
        plUtil = new ParkingLotUtil();
        plAllocationUtil = new PLAllocationUtil();
        vehicleUtil = new VehicleUtil();
        parkedVehicleUtil = new ParkedVehicleUtil();
        vehicleRegNumbers = new HashMap();
        vehicleTypeNames = VehicleTypes.getAllNames();

        initComponents();
        
        pnHome.setVisible(true);
        pnParkVehicle.setVisible(false);
        pnSalesReport.setVisible(false);
        pnUpdateProfile.setVisible(false);


        // Get names of all parking lots which currently logged in user can manage
        // and add the names in combo box
        ArrayList<String> allocatedParkingLots = plAllocationUtil.getPLNamesWithUsername(loggedInUser.getUsername());
        for(String plName: allocatedParkingLots){
            cbSelectedParkingLot.addItem(plName);
        }

        // Add all vehicle types in vehicle type combo box
        for(String vehicleType: vehicleTypeNames){
            cbVehicleType.addItem(vehicleType);
        }

        // Create anonymous text field connected with combo box of reg numbers
        // to get matching registration numbers from database
        final JTextField tfRegNumber = (JTextField)     cbRegNumber.getEditor().getEditorComponent();
        tfRegNumber.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent ke) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        String text = tfRegNumber.getText();
                        if(!text.isBlank()) {
                            regNumberFilter(text);
                        }
                    }
                });
            }
        });

        // Create anonymous text field connected with combo box of Vehicle Name
        // to get matching Vehicle Names from database
        final JTextField tfVehicleName = (JTextField)     cbVehicleName.getEditor().getEditorComponent();
        tfVehicleName.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent ke) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        String text = tfVehicleName.getText();
                        if(!text.isBlank()) {
                            vehicleNameFilter(text);
                        }
                    }
                });
            }
        });

        // Create anonymous text field connected with combo box of Token Number
        // to get matching Parked Vehicle's id from database
        final JTextField tfTokenNumber = (JTextField)     cbTokenNumberVExit.getEditor().getEditorComponent();
        tfTokenNumber.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent ke) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        String text = tfTokenNumber.getText();
                        if(!text.isBlank()) {
                            tokenNumberFilter(text);
                        }
                    }
                });
            }
        });

        // Add details of currently logged in user in update profile panel and title username
        String username = loggedInUser.getUsername();
        String fullName = loggedInUser.getFullName();
        String email = loggedInUser.getEmail();
        lbCurrentUser.setText("User: " + username);
        lbUsernameUP.setText(username);
        tfFullNameUP.setText(fullName);
        tfEmailUP.setText(email);
        

    }



    /**
     * Get matched reg numbers of vehicles from database and add them in combo box
     * */
    private void regNumberFilter(String text){
            vehicleRegNumbers = vehicle.getRegNumbersAndIds(text);
            cbRegNumber.removeAllItems();
            for (String regNumber : vehicleRegNumbers.keySet()) {
                cbRegNumber.addItem(regNumber);
            }
            if(vehicleRegNumbers.size()>0) {
                cbRegNumber.showPopup();
            }
            cbRegNumber.setSelectedItem(text.toUpperCase());      // Force Reg Number to be in uppercase
    }

    /**
     * Get matched names of vehicles from database and add them in combo box
     * */
    private void vehicleNameFilter(String text){
        ArrayList<String> vehicleNames = vehicle.getMatchedVehicleNames(text);
        cbVehicleName.removeAllItems();
        for (String vehicleName : vehicleNames) {
            cbVehicleName.addItem(vehicleName);
        }
        if(vehicleNames.size()>0) {
            cbVehicleName.showPopup();
        }
        cbVehicleName.setSelectedItem(text);
    }

    /**
     * Get matched ids of parked vehicle
     * */
    private void tokenNumberFilter(String text) {
        ParkedVehicle parkedVehicle = new ParkedVehicle();
        ArrayList<Integer> parkedVehicleIds = parkedVehicle.getMatchedIds(text, selectedPL.getId());
        cbTokenNumberVExit.removeAllItems();
        for (int id : parkedVehicleIds) {
            cbTokenNumberVExit.addItem(id + "");
        }
        if(parkedVehicleIds.size()>0) {
            cbTokenNumberVExit.showPopup();
        }
        cbTokenNumberVExit.setSelectedItem(text);
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnMain = new javax.swing.JPanel();
        pnHome = new javax.swing.JPanel();
        pnParkingFee = new javax.swing.JPanel();
        lbHeavyVehicleFee = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lbBikeFee = new javax.swing.JLabel();
        lbRickshawFee = new javax.swing.JLabel();
        lbCarFee = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        pnParkedVehicles = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        lbTotalHeavyVehicle = new javax.swing.JLabel();
        lbTotalCar = new javax.swing.JLabel();
        lbTotalRickshaw = new javax.swing.JLabel();
        lbTotalBike = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lbHeavyVehicleCapacity = new javax.swing.JLabel();
        lbCarCapacity = new javax.swing.JLabel();
        lbRickshawCapacity = new javax.swing.JLabel();
        lbBikeCapacity = new javax.swing.JLabel();
        pnParkingLot = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        lbPLlocation = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lbPLName = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        pnMenu = new javax.swing.JPanel();
        lbParkVehicle = new javax.swing.JLabel();
        lbHome = new javax.swing.JLabel();
        lbSalesReport = new javax.swing.JLabel();
        lbUpdateProfile = new javax.swing.JLabel();
        pnTitle = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        lbCurrentUser = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        cbSelectedParkingLot = new javax.swing.JComboBox<>();
        pnParkVehicle = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        pnVehicleEntrance = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        cbVehicleType = new javax.swing.JComboBox<>();
        jLabel20 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        tfOwnerName = new javax.swing.JTextField();
        btnParkVehicle = new javax.swing.JButton();
        cbVehicleName = new javax.swing.JComboBox<>();
        cbRegNumber = new javax.swing.JComboBox<>();
        jLabel21 = new javax.swing.JLabel();
        pnAllParkedVehicles = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbAllParkedVehicles = new javax.swing.JTable();
        pnVehicleExit = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        lbVehicleNameVExit = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        lbVehicleTypeVExit = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        lbCustomerNameVExit = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        lbParkingFeeVExit = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        lbTimeEntranceVExit = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        lbTimeExitVExit = new javax.swing.JLabel();
        cbTokenNumberVExit = new javax.swing.JComboBox<>();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        lbTotalFeeVExit = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        ftfPayedAmountVExit = new javax.swing.JFormattedTextField();
        jLabel36 = new javax.swing.JLabel();
        lbChangeAmountVExit = new javax.swing.JLabel();
        btnVehicleExit = new javax.swing.JButton();
        lbRegNumberVExit = new javax.swing.JLabel();
        pnSalesReport = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        dcDateFrom = new com.toedter.calendar.JDateChooser();
        jLabel38 = new javax.swing.JLabel();
        dcDateTo = new com.toedter.calendar.JDateChooser();
        btnDisplayReport = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbSalesReport = new javax.swing.JTable();
        jLabel39 = new javax.swing.JLabel();
        lbTotalCash = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        lbTotalTransactions = new javax.swing.JLabel();
        pnUpdateProfile = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel41 = new javax.swing.JLabel();
        lbUsernameUP = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        tfFullNameUP = new javax.swing.JTextField();
        tfEmailUP = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        btnUpdateProfile = new javax.swing.JButton();
        jLabel47 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        pfCurrentPasswordUP = new javax.swing.JPasswordField();
        jLabel46 = new javax.swing.JLabel();
        pfNewPassword = new javax.swing.JPasswordField();
        btnChangePassword = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnMain.setBackground(new java.awt.Color(255, 255, 255));
        pnMain.setPreferredSize(new java.awt.Dimension(890, 580));
        pnMain.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnHome.setBackground(new java.awt.Color(255, 255, 255));
        pnHome.setVerifyInputWhenFocusTarget(false);

        pnParkingFee.setBackground(new java.awt.Color(255, 255, 255));
        pnParkingFee.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        pnParkingFee.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbHeavyVehicleFee.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        lbHeavyVehicleFee.setText("lbHeavyVehicleFee");
        pnParkingFee.add(lbHeavyVehicleFee, new org.netbeans.lib.awtextra.AbsoluteConstraints(163, 179, -1, -1));

        jLabel14.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        jLabel14.setText("Heavy Vehicle Fee");
        pnParkingFee.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 179, -1, -1));

        jLabel13.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        jLabel13.setText("Car Fee");
        pnParkingFee.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 141, -1, -1));

        jLabel12.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        jLabel12.setText("Rickshaw Fee");
        pnParkingFee.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 103, -1, -1));

        jLabel11.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        jLabel11.setText("Bike Fee");
        pnParkingFee.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 65, -1, -1));

        lbBikeFee.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        lbBikeFee.setText("lbBikeFee");
        pnParkingFee.add(lbBikeFee, new org.netbeans.lib.awtextra.AbsoluteConstraints(163, 65, -1, -1));

        lbRickshawFee.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        lbRickshawFee.setText("lbRickshawFee");
        pnParkingFee.add(lbRickshawFee, new org.netbeans.lib.awtextra.AbsoluteConstraints(163, 103, -1, -1));

        lbCarFee.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        lbCarFee.setText("lbCarFee");
        pnParkingFee.add(lbCarFee, new org.netbeans.lib.awtextra.AbsoluteConstraints(163, 141, -1, -1));

        jLabel10.setFont(new java.awt.Font("Noto Sans", 0, 18)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Parking Fee Details (Per Hour)");
        pnParkingFee.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 18, 297, -1));

        pnParkedVehicles.setBackground(new java.awt.Color(255, 255, 255));
        pnParkedVehicles.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        pnParkedVehicles.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel15.setFont(new java.awt.Font("Noto Sans", 0, 18)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Currently Parked Vehicles");
        pnParkedVehicles.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 13, 300, -1));

        jLabel16.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        jLabel16.setText("Bike");
        pnParkedVehicles.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 60, -1, -1));

        jLabel17.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        jLabel17.setText("Rickshaw");
        pnParkedVehicles.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 98, -1, -1));

        jLabel19.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        jLabel19.setText("Car");
        pnParkedVehicles.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 136, -1, -1));

        jLabel18.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        jLabel18.setText("Heavy Vehicle");
        pnParkedVehicles.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 174, -1, -1));

        lbTotalHeavyVehicle.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        lbTotalHeavyVehicle.setText("lbHeavyVehicleFee");
        pnParkedVehicles.add(lbTotalHeavyVehicle, new org.netbeans.lib.awtextra.AbsoluteConstraints(154, 174, -1, -1));

        lbTotalCar.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        lbTotalCar.setText("lbCarFee");
        pnParkedVehicles.add(lbTotalCar, new org.netbeans.lib.awtextra.AbsoluteConstraints(154, 136, -1, -1));

        lbTotalRickshaw.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        lbTotalRickshaw.setText("lbTotalRickshaw");
        pnParkedVehicles.add(lbTotalRickshaw, new org.netbeans.lib.awtextra.AbsoluteConstraints(154, 98, -1, -1));

        lbTotalBike.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        lbTotalBike.setText("lbTotalBike");
        pnParkedVehicles.add(lbTotalBike, new org.netbeans.lib.awtextra.AbsoluteConstraints(154, 60, -1, -1));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Noto Sans", 0, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Vehicle Capacities");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(3, 7, 374, -1));

        jLabel6.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        jLabel6.setText("Bike Capacity");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 51, -1, -1));

        jLabel7.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        jLabel7.setText("Rickshaw Capacity");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 83, -1, -1));

        jLabel8.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        jLabel8.setText("Car Capacity");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 115, -1, -1));

        jLabel9.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        jLabel9.setText("Heavy Vehicle Capacity");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 147, -1, -1));

        lbHeavyVehicleCapacity.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        lbHeavyVehicleCapacity.setText("lbHeavyVehicleCapacity");
        jPanel1.add(lbHeavyVehicleCapacity, new org.netbeans.lib.awtextra.AbsoluteConstraints(208, 147, -1, -1));

        lbCarCapacity.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        lbCarCapacity.setText("lbCarCapacity");
        jPanel1.add(lbCarCapacity, new org.netbeans.lib.awtextra.AbsoluteConstraints(208, 115, -1, -1));

        lbRickshawCapacity.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        lbRickshawCapacity.setText("lbRickshawCapacity");
        jPanel1.add(lbRickshawCapacity, new org.netbeans.lib.awtextra.AbsoluteConstraints(208, 83, -1, -1));

        lbBikeCapacity.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        lbBikeCapacity.setText("lbBikeCapacity");
        jPanel1.add(lbBikeCapacity, new org.netbeans.lib.awtextra.AbsoluteConstraints(208, 51, -1, -1));

        pnParkingLot.setBackground(new java.awt.Color(255, 255, 255));
        pnParkingLot.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        pnParkingLot.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        jLabel4.setText("Location");
        pnParkingLot.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 119, -1, -1));

        lbPLlocation.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        lbPLlocation.setText("lbPLlocation");
        pnParkingLot.add(lbPLlocation, new org.netbeans.lib.awtextra.AbsoluteConstraints(111, 119, -1, -1));

        jLabel2.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        jLabel2.setText("Name");
        pnParkingLot.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 71, -1, -1));

        lbPLName.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        lbPLName.setText("lbPLName");
        pnParkingLot.add(lbPLName, new org.netbeans.lib.awtextra.AbsoluteConstraints(111, 71, -1, -1));

        jLabel48.setFont(new java.awt.Font("Noto Sans", 0, 18)); // NOI18N
        jLabel48.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel48.setText("Parking Lot");
        pnParkingLot.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 15, 248, -1));

        javax.swing.GroupLayout pnHomeLayout = new javax.swing.GroupLayout(pnHome);
        pnHome.setLayout(pnHomeLayout);
        pnHomeLayout.setHorizontalGroup(
            pnHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnHomeLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnHomeLayout.createSequentialGroup()
                        .addComponent(pnParkingFee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pnParkedVehicles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnHomeLayout.createSequentialGroup()
                        .addComponent(pnParkingLot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        pnHomeLayout.setVerticalGroup(
            pnHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnHomeLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                    .addComponent(pnParkingLot, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(pnHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnParkingFee, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnParkedVehicles, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pnMain.add(pnHome, new org.netbeans.lib.awtextra.AbsoluteConstraints(187, 124, 700, 450));

        pnMenu.setBackground(new java.awt.Color(53, 59, 72));
        pnMenu.setPreferredSize(new java.awt.Dimension(181, 580));

        lbParkVehicle.setFont(new java.awt.Font("Noto Sans", 1, 16)); // NOI18N
        lbParkVehicle.setForeground(new java.awt.Color(255, 255, 255));
        lbParkVehicle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbParkVehicle.setText("Park Vehicle");
        lbParkVehicle.setFocusable(false);
        lbParkVehicle.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lbParkVehicle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbParkVehicleMouseClicked(evt);
            }
        });

        lbHome.setFont(new java.awt.Font("Noto Sans", 1, 16)); // NOI18N
        lbHome.setForeground(new java.awt.Color(255, 255, 255));
        lbHome.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbHome.setText("Home");
        lbHome.setFocusable(false);
        lbHome.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lbHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbHomeMouseClicked(evt);
            }
        });

        lbSalesReport.setFont(new java.awt.Font("Noto Sans", 1, 16)); // NOI18N
        lbSalesReport.setForeground(new java.awt.Color(255, 255, 255));
        lbSalesReport.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbSalesReport.setText("Sales Report");
        lbSalesReport.setFocusable(false);
        lbSalesReport.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lbSalesReport.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbSalesReportMouseClicked(evt);
            }
        });

        lbUpdateProfile.setFont(new java.awt.Font("Noto Sans", 1, 16)); // NOI18N
        lbUpdateProfile.setForeground(new java.awt.Color(255, 255, 255));
        lbUpdateProfile.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbUpdateProfile.setText("Update Profile");
        lbUpdateProfile.setFocusable(false);
        lbUpdateProfile.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lbUpdateProfile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbUpdateProfileMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnMenuLayout = new javax.swing.GroupLayout(pnMenu);
        pnMenu.setLayout(pnMenuLayout);
        pnMenuLayout.setHorizontalGroup(
            pnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnMenuLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(pnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbUpdateProfile)
                    .addComponent(lbParkVehicle)
                    .addComponent(lbHome)
                    .addComponent(lbSalesReport))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        pnMenuLayout.setVerticalGroup(
            pnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnMenuLayout.createSequentialGroup()
                .addGap(138, 138, 138)
                .addComponent(lbHome, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbParkVehicle, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbSalesReport, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbUpdateProfile, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(256, Short.MAX_VALUE))
        );

        pnMain.add(pnMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pnTitle.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(53, 59, 72));

        jLabel5.setFont(new java.awt.Font("Noto Sans", 1, 36)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Parking System");
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        lbCurrentUser.setFont(new java.awt.Font("Noto Sans", 1, 16)); // NOI18N
        lbCurrentUser.setForeground(new java.awt.Color(255, 255, 255));
        lbCurrentUser.setText("User: ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(135, 135, 135)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 146, Short.MAX_VALUE)
                .addComponent(lbCurrentUser, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(lbCurrentUser, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jLabel1.setFont(new java.awt.Font("Noto Sans", 1, 16)); // NOI18N
        jLabel1.setText("Parking Lot");

        cbSelectedParkingLot.setMaximumRowCount(100);
        cbSelectedParkingLot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbSelectedParkingLotActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnTitleLayout = new javax.swing.GroupLayout(pnTitle);
        pnTitle.setLayout(pnTitleLayout);
        pnTitleLayout.setHorizontalGroup(
            pnTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(pnTitleLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addGap(28, 28, 28)
                .addComponent(cbSelectedParkingLot, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnTitleLayout.setVerticalGroup(
            pnTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnTitleLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cbSelectedParkingLot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2))
        );

        pnMain.add(pnTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, 710, 120));

        pnParkVehicle.setBackground(new java.awt.Color(255, 255, 255));

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));

        pnVehicleEntrance.setBackground(new java.awt.Color(255, 255, 255));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel23.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        jLabel23.setText("Owner Name");

        jLabel20.setFont(new java.awt.Font("Noto Sans", 0, 18)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Vehicle Entrance");

        jLabel24.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        jLabel24.setText("Vehicle Type");

        jLabel22.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        jLabel22.setText("Vehicle Name");

        btnParkVehicle.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        btnParkVehicle.setText("Go");
        btnParkVehicle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnParkVehicleActionPerformed(evt);
            }
        });

        cbVehicleName.setEditable(true);

        cbRegNumber.setEditable(true);
        cbRegNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbRegNumberActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        jLabel21.setText("Registration Number");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel21)
                            .addComponent(jLabel23)
                            .addComponent(jLabel22))
                        .addGap(40, 40, 40)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbVehicleName, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbRegNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbVehicleType, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfOwnerName, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel24))
                .addContainerGap(20, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnParkVehicle)
                .addGap(150, 150, 150))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel20)
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(cbRegNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(cbVehicleName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(cbVehicleType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfOwnerName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(btnParkVehicle)
                .addGap(19, 19, 19))
        );

        javax.swing.GroupLayout pnVehicleEntranceLayout = new javax.swing.GroupLayout(pnVehicleEntrance);
        pnVehicleEntrance.setLayout(pnVehicleEntranceLayout);
        pnVehicleEntranceLayout.setHorizontalGroup(
            pnVehicleEntranceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnVehicleEntranceLayout.createSequentialGroup()
                .addContainerGap(154, Short.MAX_VALUE)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(159, 159, 159))
        );
        pnVehicleEntranceLayout.setVerticalGroup(
            pnVehicleEntranceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnVehicleEntranceLayout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(93, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Vehicle Entrance", pnVehicleEntrance);

        pnAllParkedVehicles.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

        tbAllParkedVehicles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbAllParkedVehicles.setGridColor(new java.awt.Color(255, 255, 255));
        jScrollPane1.setViewportView(tbAllParkedVehicles);

        javax.swing.GroupLayout pnAllParkedVehiclesLayout = new javax.swing.GroupLayout(pnAllParkedVehicles);
        pnAllParkedVehicles.setLayout(pnAllParkedVehiclesLayout);
        pnAllParkedVehiclesLayout.setHorizontalGroup(
            pnAllParkedVehiclesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnAllParkedVehiclesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 676, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnAllParkedVehiclesLayout.setVerticalGroup(
            pnAllParkedVehiclesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnAllParkedVehiclesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        jTabbedPane1.addTab("All Parked Vehicles", pnAllParkedVehicles);

        pnVehicleExit.setBackground(new java.awt.Color(255, 255, 255));

        jLabel25.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        jLabel25.setText("Token Number");

        jLabel26.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        jLabel26.setText("Registration Number");

        jLabel27.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        jLabel27.setText("Vehicle Name");

        lbVehicleNameVExit.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        lbVehicleNameVExit.setText("Nil");

        jLabel28.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        jLabel28.setText("Vehicle Type");

        lbVehicleTypeVExit.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        lbVehicleTypeVExit.setText("Nil");

        jLabel29.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        jLabel29.setText("Customer Name");

        lbCustomerNameVExit.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        lbCustomerNameVExit.setText("Nil");

        jLabel30.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        jLabel30.setText("Parking Fee");

        lbParkingFeeVExit.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        lbParkingFeeVExit.setText("Nil");

        jLabel31.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        jLabel31.setText("Time Entrance");

        lbTimeEntranceVExit.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        lbTimeEntranceVExit.setText("Nil");

        jLabel32.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        jLabel32.setText("Time Exit");

        lbTimeExitVExit.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        lbTimeExitVExit.setText("Nil");

        cbTokenNumberVExit.setEditable(true);
        cbTokenNumberVExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTokenNumberVExitActionPerformed(evt);
            }
        });

        jLabel33.setFont(new java.awt.Font("Noto Sans", 0, 18)); // NOI18N
        jLabel33.setText("Payment ");

        jLabel34.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        jLabel34.setText("Total Fee");

        lbTotalFeeVExit.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        lbTotalFeeVExit.setText("Nil");

        jLabel35.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        jLabel35.setText("Payed Amount");

        ftfPayedAmountVExit.setFormatterFactory(NumberFormatting.floatFormatterFactory());
        ftfPayedAmountVExit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ftfPayedAmountVExitKeyReleased(evt);
            }
        });

        jLabel36.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        jLabel36.setText("Change Amount");

        lbChangeAmountVExit.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        lbChangeAmountVExit.setText("Nil");

        btnVehicleExit.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        btnVehicleExit.setText("Go");
        btnVehicleExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVehicleExitActionPerformed(evt);
            }
        });

        lbRegNumberVExit.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        lbRegNumberVExit.setText("Nil");

        javax.swing.GroupLayout pnVehicleExitLayout = new javax.swing.GroupLayout(pnVehicleExit);
        pnVehicleExit.setLayout(pnVehicleExitLayout);
        pnVehicleExitLayout.setHorizontalGroup(
            pnVehicleExitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnVehicleExitLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(pnVehicleExitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel29)
                    .addComponent(jLabel25)
                    .addComponent(jLabel28)
                    .addComponent(jLabel31))
                .addGap(35, 35, 35)
                .addGroup(pnVehicleExitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbCustomerNameVExit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbVehicleTypeVExit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbTimeEntranceVExit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbTokenNumberVExit, 0, 150, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addGroup(pnVehicleExitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel26)
                    .addComponent(jLabel27)
                    .addComponent(jLabel30)
                    .addComponent(jLabel32))
                .addGap(28, 28, 28)
                .addGroup(pnVehicleExitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnVehicleExitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lbParkingFeeVExit, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                        .addComponent(lbVehicleNameVExit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbTimeExitVExit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(lbRegNumberVExit, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11))
            .addGroup(pnVehicleExitLayout.createSequentialGroup()
                .addGroup(pnVehicleExitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnVehicleExitLayout.createSequentialGroup()
                        .addGap(167, 167, 167)
                        .addGroup(pnVehicleExitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel34)
                            .addComponent(jLabel35)
                            .addComponent(jLabel36))
                        .addGap(44, 44, 44)
                        .addGroup(pnVehicleExitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbTotalFeeVExit, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ftfPayedAmountVExit, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                            .addComponent(lbChangeAmountVExit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(pnVehicleExitLayout.createSequentialGroup()
                        .addGap(240, 240, 240)
                        .addComponent(jLabel33))
                    .addGroup(pnVehicleExitLayout.createSequentialGroup()
                        .addGap(248, 248, 248)
                        .addComponent(btnVehicleExit)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnVehicleExitLayout.setVerticalGroup(
            pnVehicleExitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnVehicleExitLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(pnVehicleExitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(jLabel25)
                    .addComponent(cbTokenNumberVExit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbRegNumberVExit))
                .addGap(18, 18, 18)
                .addGroup(pnVehicleExitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(lbCustomerNameVExit)
                    .addComponent(jLabel27)
                    .addComponent(lbVehicleNameVExit))
                .addGap(18, 18, 18)
                .addGroup(pnVehicleExitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(lbVehicleTypeVExit)
                    .addComponent(jLabel30)
                    .addComponent(lbParkingFeeVExit))
                .addGap(18, 18, 18)
                .addGroup(pnVehicleExitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(lbTimeEntranceVExit)
                    .addComponent(jLabel32)
                    .addComponent(lbTimeExitVExit))
                .addGap(38, 38, 38)
                .addComponent(jLabel33)
                .addGap(18, 18, 18)
                .addGroup(pnVehicleExitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(lbTotalFeeVExit))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnVehicleExitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(ftfPayedAmountVExit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnVehicleExitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(lbChangeAmountVExit))
                .addGap(18, 18, 18)
                .addComponent(btnVehicleExit)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Vehicle Exit", pnVehicleExit);

        javax.swing.GroupLayout pnParkVehicleLayout = new javax.swing.GroupLayout(pnParkVehicle);
        pnParkVehicle.setLayout(pnParkVehicleLayout);
        pnParkVehicleLayout.setHorizontalGroup(
            pnParkVehicleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnParkVehicleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        pnParkVehicleLayout.setVerticalGroup(
            pnParkVehicleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnParkVehicleLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 451, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnMain.add(pnParkVehicle, new org.netbeans.lib.awtextra.AbsoluteConstraints(187, 124, 700, 450));

        pnSalesReport.setBackground(new java.awt.Color(255, 255, 255));
        pnSalesReport.setPreferredSize(new java.awt.Dimension(708, 450));

        jLabel37.setText("Date From");

        dcDateFrom.setDateFormatString("dd-MM-yyyy");

        jLabel38.setText("Date To");

        dcDateTo.setDateFormatString("dd-MM-yyyy");

        btnDisplayReport.setText("Display Report");
        btnDisplayReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDisplayReportActionPerformed(evt);
            }
        });

        jScrollPane3.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane3.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        tbSalesReport.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tbSalesReport);

        jLabel39.setFont(new java.awt.Font("Noto Sans", 1, 16)); // NOI18N
        jLabel39.setText("Total Cash");

        lbTotalCash.setFont(new java.awt.Font("Noto Sans", 0, 16)); // NOI18N
        lbTotalCash.setText("Nil");

        jLabel40.setFont(new java.awt.Font("Noto Sans", 1, 16)); // NOI18N
        jLabel40.setText("Total Transactions");

        lbTotalTransactions.setFont(new java.awt.Font("Noto Sans", 0, 16)); // NOI18N
        lbTotalTransactions.setText("Nil");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 684, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel40))
                .addGap(39, 39, 39)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbTotalTransactions, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbTotalCash, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(81, 81, 81))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(lbTotalCash))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40)
                    .addComponent(lbTotalTransactions))
                .addGap(0, 175, Short.MAX_VALUE))
        );

        jScrollPane3.setViewportView(jPanel3);

        javax.swing.GroupLayout pnSalesReportLayout = new javax.swing.GroupLayout(pnSalesReport);
        pnSalesReport.setLayout(pnSalesReportLayout);
        pnSalesReportLayout.setHorizontalGroup(
            pnSalesReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnSalesReportLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel37)
                .addGap(18, 18, 18)
                .addComponent(dcDateFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(dcDateTo, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addComponent(btnDisplayReport)
                .addGap(24, 24, 24))
            .addGroup(pnSalesReportLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnSalesReportLayout.setVerticalGroup(
            pnSalesReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnSalesReportLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(pnSalesReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnDisplayReport)
                    .addGroup(pnSalesReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(dcDateTo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(dcDateFrom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 492, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pnMain.add(pnSalesReport, new org.netbeans.lib.awtextra.AbsoluteConstraints(187, 124, 700, 450));

        pnUpdateProfile.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel41.setText("Username");

        lbUsernameUP.setText("Nil");

        jLabel42.setText("Full Name");

        jLabel43.setText("Email");

        btnUpdateProfile.setText("Save");
        btnUpdateProfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateProfileActionPerformed(evt);
            }
        });

        jLabel47.setText("Update Profile");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnUpdateProfile)
                .addGap(40, 40, 40))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel42)
                            .addComponent(jLabel43))
                        .addGap(36, 36, 36)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tfFullNameUP)
                            .addComponent(lbUsernameUP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tfEmailUP, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(94, 94, 94)
                        .addComponent(jLabel47)))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel47)
                .addGap(33, 33, 33)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41)
                    .addComponent(lbUsernameUP))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel42)
                    .addComponent(tfFullNameUP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel43)
                    .addComponent(tfEmailUP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addComponent(btnUpdateProfile)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel44.setText("Change Password");

        jLabel45.setText("Current Password");

        jLabel46.setText("New Password");

        btnChangePassword.setText("Save");
        btnChangePassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChangePasswordActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel46)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pfNewPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel45)
                        .addGap(32, 32, 32)
                        .addComponent(pfCurrentPasswordUP, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(38, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel44)
                        .addGap(108, 108, 108))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(btnChangePassword)
                        .addGap(50, 50, 50))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel44)
                .addGap(40, 40, 40)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel45)
                    .addComponent(pfCurrentPasswordUP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel46)
                    .addComponent(pfNewPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addComponent(btnChangePassword)
                .addContainerGap(61, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnUpdateProfileLayout = new javax.swing.GroupLayout(pnUpdateProfile);
        pnUpdateProfile.setLayout(pnUpdateProfileLayout);
        pnUpdateProfileLayout.setHorizontalGroup(
            pnUpdateProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnUpdateProfileLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );
        pnUpdateProfileLayout.setVerticalGroup(
            pnUpdateProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnUpdateProfileLayout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(pnUpdateProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(113, Short.MAX_VALUE))
        );

        pnMain.add(pnUpdateProfile, new org.netbeans.lib.awtextra.AbsoluteConstraints(187, 124, 700, 450));

        getContentPane().add(pnMain, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cbSelectedParkingLotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSelectedParkingLotActionPerformed
        String selectedPLName = cbSelectedParkingLot.getSelectedItem().toString();
        selectedPL = plUtil.getParkingLotFromName(selectedPLName);

        String plName = selectedPL.getName();
        String plLocation = selectedPL.getLocation();
        String bikeCapacity = Integer.toString(selectedPL.getSingleVehicleCapacity(VehicleTypes.BIKE.getValue()));
        String rickshawCapacity = Integer.toString(selectedPL.getSingleVehicleCapacity(VehicleTypes.RICKSHAW.getValue()));
        String carCapacity = Integer.toString(selectedPL.getSingleVehicleCapacity(VehicleTypes.CAR.getValue()));
        String heavyVehicleCapacity = Integer.toString(selectedPL.getSingleVehicleCapacity(VehicleTypes.HEAVY_VEHICLE.getValue()));

        lbPLName.setText(plName);
        lbPLlocation.setText(plLocation);
        lbBikeCapacity.setText(bikeCapacity);
        lbRickshawCapacity.setText(rickshawCapacity);
        lbCarCapacity.setText(carCapacity);
        lbHeavyVehicleCapacity.setText(heavyVehicleCapacity);

        String bikeFee = "Rs " + selectedPL.getSingleParkingFee(VehicleTypes.BIKE.getValue());
        String rickshawFee = "Rs " + selectedPL.getSingleParkingFee(VehicleTypes.RICKSHAW.getValue());
        String carFee = "Rs " + selectedPL.getSingleParkingFee(VehicleTypes.CAR.getValue());
        String heavyVehicleFee = "Rs " + selectedPL.getSingleParkingFee(VehicleTypes.HEAVY_VEHICLE.getValue());

        lbBikeFee.setText(bikeFee);
        lbRickshawFee.setText(rickshawFee);
        lbCarFee.setText(carFee);
        lbHeavyVehicleFee.setText(heavyVehicleFee);

        updateFrame();

    }//GEN-LAST:event_cbSelectedParkingLotActionPerformed


    private void lbHomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbHomeMouseClicked
        pnHome.setVisible(true);
        pnParkVehicle.setVisible(false);
        pnSalesReport.setVisible(false);
        pnUpdateProfile.setVisible(false);
    }//GEN-LAST:event_lbHomeMouseClicked

    private void lbParkVehicleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbParkVehicleMouseClicked
        pnParkVehicle.setVisible(true);
        pnHome.setVisible(false);
        pnSalesReport.setVisible(false);
        pnUpdateProfile.setVisible(false);
    }//GEN-LAST:event_lbParkVehicleMouseClicked

    private void lbSalesReportMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbSalesReportMouseClicked
        pnSalesReport.setVisible(true);
        pnHome.setVisible(false);
        pnParkVehicle.setVisible(false);
        pnUpdateProfile.setVisible(false);
    }//GEN-LAST:event_lbSalesReportMouseClicked

    private void lbUpdateProfileMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbUpdateProfileMouseClicked
        pnUpdateProfile.setVisible(true);
        pnSalesReport.setVisible(false);
        pnHome.setVisible(false);
        pnParkVehicle.setVisible(false);
    }//GEN-LAST:event_lbUpdateProfileMouseClicked

    private void cbRegNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbRegNumberActionPerformed
        selectedVehicleId = -1;
        if(cbRegNumber.getSelectedItem() != null) {
            String selectedRegNumber = cbRegNumber.getSelectedItem().toString();
            if (vehicleRegNumbers.containsKey(selectedRegNumber)) {
                int vehicleId = vehicleRegNumbers.get(selectedRegNumber);
                selectedVehicleId = vehicleId;
                Vehicle vehicle = vehicleUtil.getVehicleWithId(vehicleId);
                cbVehicleName.setSelectedItem(vehicle.getVehicleName());
                tfOwnerName.setText(vehicle.getOwnerName());
                int vehicleType = vehicle.getVehicleType();
                cbVehicleType.setSelectedItem(VehicleTypes.getNameFromValue(vehicleType));
            }else{
                tfOwnerName.setText("");
                cbVehicleName.setSelectedItem("");
                cbVehicleType.setSelectedIndex(0);
            }
        }

    }//GEN-LAST:event_cbRegNumberActionPerformed

    private void btnParkVehicleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnParkVehicleActionPerformed
        int parkingLotId = selectedPL.getId();

        Vehicle vehicle = new Vehicle();
        if(selectedVehicleId > 0){
            vehicle.setId(selectedVehicleId);
        }
        String vehicleName = "";
        String regNumber = "";

        Object objVehicleName = cbVehicleName.getSelectedItem();
        Object objRegNumber = cbRegNumber.getSelectedItem();
        if(objRegNumber!= null && objVehicleName !=null) {
            vehicleName = objVehicleName.toString();
            regNumber = objRegNumber.toString();
        }
        String vehicleTypeName = cbVehicleType.getSelectedItem().toString();
        int vehicleTypeValue = VehicleTypes.getValueFromName(vehicleTypeName);
        String ownerName = tfOwnerName.getText();

        // Generate and display error message if any of the required fields is missing
        HashMap<String, String> requiredFields = new HashMap<>();
        requiredFields.put("Registration Number", regNumber);
        requiredFields.put("Vehicle Name", vehicleName);
        String errorMessage = FormUtility.errorMessageForRequiredFields(requiredFields);
        if(!errorMessage.isBlank()){
            DisplayMessage.displayError(errorMessage);
        }
        else {
            // Park vehicle by saving it in database if the capacity of current vehicle type is not full
            ParkingLotCapacity plCapacity = new ParkingLotCapacity();
            HashMap<Integer, Integer> allVehicleCapacity = plCapacity.getVehicleCapacity(parkingLotId);
            int parkedVehicles = totalParkedVehicles.get(vehicleTypeValue);     // Total parked vehicles of current vehicle type
            int maxCapacity = allVehicleCapacity.get(vehicleTypeValue);         // Max capacity of current vehicle type in current parking lot
            if (parkedVehicles == maxCapacity) {
                DisplayMessage.displayError("Parking space for '" + vehicleTypeName + "' is already full! \n" +
                        "You cannot park more vehicles of '" + vehicleTypeName + "' category!");
            } else {
                vehicle.setVehicleName(vehicleName);
                vehicle.setRegNumber(regNumber);
                vehicle.setOwnerName(ownerName);
                vehicle.setVehicleType(vehicleTypeValue);

                // Get new id if new vehicle added, otherwise same id will be returned
                int vehicleId = vehicle.saveVehicle();

                ParkedVehicle parkedVehicle = new ParkedVehicle();
                parkedVehicle.setParkingLotId(parkingLotId);
                parkedVehicle.setVehicleId(vehicleId);
                Timestamp currentTime = new Timestamp(System.currentTimeMillis());
                parkedVehicle.setParkTime(currentTime);
                int parkedVehicleId = parkedVehicle.saveParkedVehicle();
                if (parkedVehicleId > 0) {
                    ParkingReceiptFrame receiptFrame = new ParkingReceiptFrame(parkedVehicle);
                    receiptFrame.setVisible(true);
                    updateFrame();
                    FormUtility.clearFields(pnParkVehicle);
                } else {
                    DisplayMessage.displayError("Operation Failed");
                }
            }
        }

    }//GEN-LAST:event_btnParkVehicleActionPerformed

    private void cbTokenNumberVExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTokenNumberVExitActionPerformed
        String entranceTimeStr = "Nil", exitTimeStr = "Nil", vehicleTypeName = "Nil", regNumber = "Nil",
                vehicleName = "Nil", ownerName = "Nil", parkingFeeStr = "Nil", totalFeeStr = "Nil";
        Object cbTokenObject = cbTokenNumberVExit.getSelectedItem();
        inventory = null;
        parkedVehicleId = -1;
        if(cbTokenObject != null){
            String pvIdStr = cbTokenObject.toString();

            if(pvIdStr.matches("[0-9]+")) {      // If valid id provided
                parkedVehicleId = Integer.parseInt(pvIdStr);
                int parkingLotId = selectedPL.getId();
                ParkedVehicle parkedVehicle = parkedVehicleUtil.getParkedVehicleWithPLId(parkingLotId, parkedVehicleId);


                if (parkedVehicle != null) {

                    int vehicleId = parkedVehicle.getVehicleId();
                    Timestamp entranceTime = parkedVehicle.getParkTime();
                    Timestamp exitTime = new Timestamp(System.currentTimeMillis());

                    entranceTimeStr = TimeFormatting.getDateTimeString(entranceTime);
                    exitTimeStr = TimeFormatting.getDateTimeString(exitTime);

                    Vehicle vehicle = vehicleUtil.getVehicleWithId(vehicleId);
                    int vehicleTypeValue = vehicle.getVehicleType();
                    vehicleTypeName = VehicleTypes.getNameFromValue(vehicleTypeValue);
                    regNumber = vehicle.getRegNumber();
                    vehicleName = vehicle.getVehicleName();
                    ownerName = vehicle.getOwnerName();

                    float parkingFee = selectedPL.getSingleParkingFee(vehicleTypeValue);
                    float parkHours = TimeFormatting.getHoursDifference(entranceTime, exitTime);
                    float totalFee = parkingFee * parkHours;

                    parkingFeeStr = "Rs " + parkingFee + " (Per Hour)";
                    totalFeeStr = "Rs " + totalFee;

                    inventory = new Inventory();
                    inventory.setParkingLotId(parkingLotId);
                    inventory.setTimeEntrance(entranceTime);
                    inventory.setTimeExit(exitTime);
                    inventory.setVehicleId(vehicleId);
                    inventory.setTotalFee(totalFee);

                }
            }

        }
        // Now displaying all values in labels, fields and combo boxes
        // Display Nil if the provided id is invalid
        lbRegNumberVExit.setText(regNumber);
        lbCustomerNameVExit.setText(ownerName);
        lbVehicleNameVExit.setText(vehicleName);
        lbParkingFeeVExit.setText(parkingFeeStr);
        lbTimeEntranceVExit.setText(entranceTimeStr);
        lbTimeExitVExit.setText(exitTimeStr);
        lbTotalFeeVExit.setText(totalFeeStr);
        lbVehicleTypeVExit.setText(vehicleTypeName);
    }//GEN-LAST:event_cbTokenNumberVExitActionPerformed

    private void ftfPayedAmountVExitKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ftfPayedAmountVExitKeyReleased
        // Remove commas from string, if any
        String payedAmountStr = ftfPayedAmountVExit.getText().replace("," , "");

        float payedAmount = Float.parseFloat(payedAmountStr);
        if(inventory != null){
            float totalFee = inventory.getTotalFee();
            if(payedAmount > totalFee){
                lbChangeAmountVExit.setText("Rs " + (payedAmount - totalFee));
            }
        }
    }//GEN-LAST:event_ftfPayedAmountVExitKeyReleased

    private void btnVehicleExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVehicleExitActionPerformed
        if(inventory == null){
            DisplayMessage.displayError("Please provide correct token number or vehicle registration number");
        }else{
            String payedAmountStr = ftfPayedAmountVExit.getText().replace("," , "");
            float payedAmount = Float.parseFloat(payedAmountStr);
            if(payedAmount < inventory.getTotalFee()){
                DisplayMessage.displayError("Payed amount is less than total fee\n" +
                        "Please enter amount greater than or equal to total fee!");
            }else{
                if(inventory.saveInventory()){
                    ParkedVehicle pv = new ParkedVehicle();
                    if(pv.deleteParkedVehicle(parkedVehicleId)){
                        PaymentReceiptFrame receiptFrame = new PaymentReceiptFrame(inventory, payedAmount);
                        receiptFrame.setVisible(true);
                        updateFrame();
                    }else{
                        DisplayMessage.displayError("Failed");
                    }
                }else{
                    DisplayMessage.displayError("Operation Failed!");
                }
            }
        }
    }//GEN-LAST:event_btnVehicleExitActionPerformed

    private void btnDisplayReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDisplayReportActionPerformed
        Date dateFrom = dcDateFrom.getDate();
        Date dateTo = dcDateTo.getDate();


        HashMap<String, String> requiredFields = new HashMap<>();
        requiredFields.put("Date From", dateFrom.toString());
        requiredFields.put("Date To", dateTo.toString());

        String errorMessage = FormUtility.errorMessageForRequiredFields(requiredFields);
        if(!errorMessage.isBlank()){
            DisplayMessage.displayError(errorMessage);
        }else {
            Timestamp dateFromTS = TimeFormatting.getTimestampFromDate(dateFrom);
            Timestamp dateToTS = TimeFormatting.getTimestampFromDate(dateTo);
            if (dateFromTS == null || dateToTS == null) {
                DisplayMessage.displayError("Invalid dates provided! \nEnter again!");
            } else if (dateToTS.getTime() < dateFromTS.getTime()) {
                DisplayMessage.displayError("'Date To' must be greater then or equal to 'Date From' ");
            } else{
                Inventory inventory = new Inventory();

                // Increase the date to timestamp till the end of day
                dateToTS.setTime(dateToTS.getTime()+(86340*1000));

                ArrayList<Inventory> inventoryList = inventory.getInventoryWithDates(selectedPL.getId(), dateFromTS, dateToTS);
                Object[][] data = new Object[inventoryList.size()][];
                Object[] columns = {"Sr No.", "Registration Number", "Vehicle Type", "Time Entrance", "Time Exit", "Total Fee"};
                int counter = 0;
                float totalCash = 0;
                for(Inventory inv:inventoryList){
                    int vehicleId = inv.getVehicleId();
                    Vehicle vehicle = vehicleUtil.getVehicleWithId(vehicleId);
                    int vehicleTypeValue = vehicle.getVehicleType();
                    String vehicleTypeName = VehicleTypes.getNameFromValue(vehicleTypeValue);
                    String regNumber = vehicle.getRegNumber();
                    Timestamp timeEntrance = inv.getTimeEntrance();
                    Timestamp timeExit = inv.getTimeExit();
                    String timeEntranceStr = TimeFormatting.getDateTimeString(timeEntrance);
                    String timeExitStr = TimeFormatting.getDateTimeString(timeExit);
                    String totalFee = "Rs " + inv.getTotalFee();
                    totalCash += inv.getTotalFee();

                    data[counter] = new Object[]{counter+1, regNumber, vehicleTypeName, timeEntranceStr, timeExitStr, totalFee};
                    counter++;
                }
                
                tbSalesReport.setModel(new DefaultTableModel(data, columns));
                lbTotalCash.setText("Rs "+ totalCash);
                lbTotalTransactions.setText(counter+"");
                
            }
        }


        
    }//GEN-LAST:event_btnDisplayReportActionPerformed

    private void btnUpdateProfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateProfileActionPerformed
        String username = loggedInUser.getUsername();
        String fullName = tfFullNameUP.getText();
        String email = tfEmailUP.getText();
        User user = loggedInUser;
        HashMap<String, String> requiredFields = new HashMap<>();
        requiredFields.put("Full Name", fullName);
        requiredFields.put("Email", email);
        String errorMessage = FormUtility.errorMessageForRequiredFields(requiredFields);
        if(!errorMessage.isBlank()){
            DisplayMessage.displayError(errorMessage);
        }else {
            if (user.updateProfile(username, fullName, email)) {
                DisplayMessage.displayInfo("Profile updated successfully!");
                loggedInUser = user;            // Now full name and email are update in 'user' object
                tfFullNameUP.setText(loggedInUser.getFullName());
                tfEmailUP.setText(loggedInUser.getEmail());
            }
        }

    }//GEN-LAST:event_btnUpdateProfileActionPerformed

    private void btnChangePasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChangePasswordActionPerformed
        String currentPassword = String.valueOf(pfCurrentPasswordUP.getPassword());
        String newPassword = String.valueOf(pfNewPassword.getPassword());

        HashMap<String, String> requiredFields = new HashMap<>();
        requiredFields.put("Current Password", currentPassword);
        requiredFields.put("Email", newPassword);
        String errorMessage = FormUtility.errorMessageForRequiredFields(requiredFields);
        if(!errorMessage.isBlank()){
            DisplayMessage.displayError(errorMessage);
        }else {
            if (newPassword.length() < 5) {
                DisplayMessage.displayError("New password must be of at least 5 characters!");
            } else {
                if (loggedInUser.updatePassword(currentPassword, newPassword)) {
                    DisplayMessage.displayInfo("Password changed successfully!");
                } else {
                    DisplayMessage.displayError("You have provided wrong password, Please try again!");
                }
            }
        }

    }//GEN-LAST:event_btnChangePasswordActionPerformed



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
            java.util.logging.Logger.getLogger(WorkerHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(WorkerHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(WorkerHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(WorkerHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new WorkerHome().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChangePassword;
    private javax.swing.JButton btnDisplayReport;
    private javax.swing.JButton btnParkVehicle;
    private javax.swing.JButton btnUpdateProfile;
    private javax.swing.JButton btnVehicleExit;
    private javax.swing.JComboBox<String> cbRegNumber;
    private javax.swing.JComboBox<String> cbSelectedParkingLot;
    private javax.swing.JComboBox<String> cbTokenNumberVExit;
    private javax.swing.JComboBox<String> cbVehicleName;
    private javax.swing.JComboBox<String> cbVehicleType;
    private com.toedter.calendar.JDateChooser dcDateFrom;
    private com.toedter.calendar.JDateChooser dcDateTo;
    private javax.swing.JFormattedTextField ftfPayedAmountVExit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lbBikeCapacity;
    private javax.swing.JLabel lbBikeFee;
    private javax.swing.JLabel lbCarCapacity;
    private javax.swing.JLabel lbCarFee;
    private javax.swing.JLabel lbChangeAmountVExit;
    private javax.swing.JLabel lbCurrentUser;
    private javax.swing.JLabel lbCustomerNameVExit;
    private javax.swing.JLabel lbHeavyVehicleCapacity;
    private javax.swing.JLabel lbHeavyVehicleFee;
    private javax.swing.JLabel lbHome;
    private javax.swing.JLabel lbPLName;
    private javax.swing.JLabel lbPLlocation;
    private javax.swing.JLabel lbParkVehicle;
    private javax.swing.JLabel lbParkingFeeVExit;
    private javax.swing.JLabel lbRegNumberVExit;
    private javax.swing.JLabel lbRickshawCapacity;
    private javax.swing.JLabel lbRickshawFee;
    private javax.swing.JLabel lbSalesReport;
    private javax.swing.JLabel lbTimeEntranceVExit;
    private javax.swing.JLabel lbTimeExitVExit;
    private javax.swing.JLabel lbTotalBike;
    private javax.swing.JLabel lbTotalCar;
    private javax.swing.JLabel lbTotalCash;
    private javax.swing.JLabel lbTotalFeeVExit;
    private javax.swing.JLabel lbTotalHeavyVehicle;
    private javax.swing.JLabel lbTotalRickshaw;
    private javax.swing.JLabel lbTotalTransactions;
    private javax.swing.JLabel lbUpdateProfile;
    private javax.swing.JLabel lbUsernameUP;
    private javax.swing.JLabel lbVehicleNameVExit;
    private javax.swing.JLabel lbVehicleTypeVExit;
    private javax.swing.JPasswordField pfCurrentPasswordUP;
    private javax.swing.JPasswordField pfNewPassword;
    private javax.swing.JPanel pnAllParkedVehicles;
    private javax.swing.JPanel pnHome;
    private javax.swing.JPanel pnMain;
    private javax.swing.JPanel pnMenu;
    private javax.swing.JPanel pnParkVehicle;
    private javax.swing.JPanel pnParkedVehicles;
    private javax.swing.JPanel pnParkingFee;
    private javax.swing.JPanel pnParkingLot;
    private javax.swing.JPanel pnSalesReport;
    private javax.swing.JPanel pnTitle;
    private javax.swing.JPanel pnUpdateProfile;
    private javax.swing.JPanel pnVehicleEntrance;
    private javax.swing.JPanel pnVehicleExit;
    private javax.swing.JTable tbAllParkedVehicles;
    private javax.swing.JTable tbSalesReport;
    private javax.swing.JTextField tfEmailUP;
    private javax.swing.JTextField tfFullNameUP;
    private javax.swing.JTextField tfOwnerName;
    // End of variables declaration//GEN-END:variables

    private void updateFrame() {
        // Create new objects so that the parked vehicles' data and vehicle data
        // is updated every time the frame updates its values
        vehicleUtil = new VehicleUtil();
        parkedVehicleUtil = new ParkedVehicleUtil();

        int plId = selectedPL.getId();
        totalParkedVehicles = parkedVehicleUtil.getTotalParkedVehicles(plId);
        String totalBike = Integer.toString(totalParkedVehicles.get(VehicleTypes.BIKE.getValue()));
        String totalRickshaw = Integer.toString(totalParkedVehicles.get(VehicleTypes.RICKSHAW.getValue()));
        String totalCar = Integer.toString(totalParkedVehicles.get(VehicleTypes.CAR.getValue()));
        String totalHeavyVehicle = Integer.toString(totalParkedVehicles.get(VehicleTypes.HEAVY_VEHICLE.getValue()));

        lbTotalBike.setText(totalBike);
        lbTotalRickshaw.setText(totalRickshaw);
        lbTotalCar.setText(totalCar);
        lbTotalHeavyVehicle.setText(totalHeavyVehicle);

        // Create/Update table of All Parked Vehicles in parked vehicle panel
        ParkedVehicle parkedVehicle = new ParkedVehicle();
        ArrayList<ParkedVehicle> allParkedVehicles = parkedVehicle.getParkedVehicleWithPLId(selectedPL.getId());
        Object[] columns = {"Token Number", "Registration Number", "Vehicle Type", "Entrance Time"};
        Object[][] data = new Object[allParkedVehicles.size()][];

        int counter = 0;
        for(ParkedVehicle pv:allParkedVehicles){
            int token = pv.getId();
            int vehicleId = pv.getVehicleId();
            Timestamp entranceTime = pv.getParkTime();
            String entranceTimeStr = TimeFormatting.getDateTimeString(entranceTime);
            Vehicle vehicle = vehicleUtil.getVehicleWithId(vehicleId);
            int vehicleTypeValue = vehicle.getVehicleType();
            String vehicleTypeName = VehicleTypes.getNameFromValue(vehicleTypeValue);
            String regNumber = vehicle.getRegNumber();

            data[counter++] = new Object[] {token, regNumber, vehicleTypeName, entranceTimeStr};
        }

        tbAllParkedVehicles.setModel(new javax.swing.table.DefaultTableModel(
                data, columns
        ));

    }
}
