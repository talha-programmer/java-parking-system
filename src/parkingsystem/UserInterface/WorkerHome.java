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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Timestamp;
import java.util.ArrayList;
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
        pnMenu = new javax.swing.JPanel();
        lbParkVehicle = new javax.swing.JLabel();
        lbHome = new javax.swing.JLabel();
        pnTitle = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        cbSelectedParkingLot = new javax.swing.JComboBox<>();
        pnParkVehicle = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        tfOwnerName = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        cbVehicleType = new javax.swing.JComboBox<>();
        cbRegNumber = new javax.swing.JComboBox<>();
        cbVehicleName = new javax.swing.JComboBox<>();
        btnParkVehicle = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnMain.setBackground(new java.awt.Color(255, 255, 255));
        pnMain.setPreferredSize(new java.awt.Dimension(890, 580));
        pnMain.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnHome.setBackground(new java.awt.Color(255, 255, 255));
        pnHome.setVerifyInputWhenFocusTarget(false);
        pnHome.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnParkingFee.setBackground(new java.awt.Color(255, 255, 255));
        pnParkingFee.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbHeavyVehicleFee.setText("lbHeavyVehicleFee");
        pnParkingFee.add(lbHeavyVehicleFee, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 171, -1, -1));

        jLabel14.setText("Heavy Vehicle Fee");
        pnParkingFee.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 171, -1, -1));

        jLabel13.setText("Car Fee");
        pnParkingFee.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 134, -1, -1));

        jLabel12.setText("Rickshaw Fee");
        pnParkingFee.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 97, -1, -1));

        jLabel11.setText("Bike Fee");
        pnParkingFee.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 60, -1, -1));

        lbBikeFee.setText("lbBikeFee");
        pnParkingFee.add(lbBikeFee, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 60, -1, -1));

        lbRickshawFee.setText("lbRickshawFee");
        pnParkingFee.add(lbRickshawFee, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 97, -1, -1));

        lbCarFee.setText("lbCarFee");
        pnParkingFee.add(lbCarFee, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 134, -1, -1));

        jLabel10.setText("Parking Fee Details (Per Hour)");
        pnParkingFee.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(38, 23, -1, -1));

        pnHome.add(pnParkingFee, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, -1, -1));

        pnParkedVehicles.setBackground(new java.awt.Color(255, 255, 255));
        pnParkedVehicles.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel15.setText("Currently Parked Vehicles");
        pnParkedVehicles.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, -1, -1));

        jLabel16.setText("Bike");
        pnParkedVehicles.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 43, -1, -1));

        jLabel17.setText("Rickshaw");
        pnParkedVehicles.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 80, -1, -1));

        jLabel19.setText("Car");
        pnParkedVehicles.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 117, -1, -1));

        jLabel18.setText("Heavy Vehicle");
        pnParkedVehicles.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 154, -1, -1));

        lbTotalHeavyVehicle.setText("lbHeavyVehicleFee");
        pnParkedVehicles.add(lbTotalHeavyVehicle, new org.netbeans.lib.awtextra.AbsoluteConstraints(124, 154, -1, -1));

        lbTotalCar.setText("lbCarFee");
        pnParkedVehicles.add(lbTotalCar, new org.netbeans.lib.awtextra.AbsoluteConstraints(124, 117, -1, -1));

        lbTotalRickshaw.setText("lbTotalRickshaw");
        pnParkedVehicles.add(lbTotalRickshaw, new org.netbeans.lib.awtextra.AbsoluteConstraints(124, 80, -1, -1));

        lbTotalBike.setText("lbTotalBike");
        pnParkedVehicles.add(lbTotalBike, new org.netbeans.lib.awtextra.AbsoluteConstraints(124, 43, -1, -1));

        pnHome.add(pnParkedVehicles, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 230, -1, 180));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setText("Vehicle Capacities");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 6, -1, -1));

        jLabel6.setText("Bike Capacity");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 43, -1, -1));

        jLabel7.setText("Rickshaw Capacity");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 68, -1, -1));

        jLabel8.setText("Car Capacity");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 105, -1, -1));

        jLabel9.setText("Heavy Vehicle Capacity");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 142, -1, -1));

        lbHeavyVehicleCapacity.setText("lbHeavyVehicleCapacity");
        jPanel1.add(lbHeavyVehicleCapacity, new org.netbeans.lib.awtextra.AbsoluteConstraints(172, 142, -1, -1));

        lbCarCapacity.setText("lbCarCapacity");
        jPanel1.add(lbCarCapacity, new org.netbeans.lib.awtextra.AbsoluteConstraints(172, 105, -1, -1));

        lbRickshawCapacity.setText("lbRickshawCapacity");
        jPanel1.add(lbRickshawCapacity, new org.netbeans.lib.awtextra.AbsoluteConstraints(172, 68, -1, -1));

        lbBikeCapacity.setText("lbBikeCapacity");
        jPanel1.add(lbBikeCapacity, new org.netbeans.lib.awtextra.AbsoluteConstraints(172, 43, -1, -1));

        pnHome.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 20, -1, -1));

        pnParkingLot.setBackground(new java.awt.Color(255, 255, 255));
        pnParkingLot.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setText("Location");
        pnParkingLot.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 80, -1, -1));

        lbPLlocation.setText("lbPLlocation");
        pnParkingLot.add(lbPLlocation, new org.netbeans.lib.awtextra.AbsoluteConstraints(95, 80, -1, -1));

        jLabel2.setText("Parking Lot");
        pnParkingLot.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, -1, -1));

        lbPLName.setText("lbPLName");
        pnParkingLot.add(lbPLName, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 20, -1, -1));

        pnHome.add(pnParkingLot, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, -1));

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

        javax.swing.GroupLayout pnMenuLayout = new javax.swing.GroupLayout(pnMenu);
        pnMenu.setLayout(pnMenuLayout);
        pnMenuLayout.setHorizontalGroup(
            pnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbHome, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
                    .addComponent(lbParkVehicle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnMenuLayout.setVerticalGroup(
            pnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnMenuLayout.createSequentialGroup()
                .addGap(96, 96, 96)
                .addComponent(lbHome, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbParkVehicle, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(400, Short.MAX_VALUE))
        );

        pnMain.add(pnMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pnTitle.setBackground(new java.awt.Color(255, 255, 255));
        pnTitle.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Noto Sans", 1, 36)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 102));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Parking System");
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 300, 70));

        pnTitle.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 710, 70));

        jLabel1.setText("Parking Lot");
        pnTitle.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, -1));

        cbSelectedParkingLot.setMaximumRowCount(100);
        cbSelectedParkingLot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbSelectedParkingLotActionPerformed(evt);
            }
        });
        pnTitle.add(cbSelectedParkingLot, new org.netbeans.lib.awtextra.AbsoluteConstraints(103, 80, 130, -1));

        pnMain.add(pnTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, 710, 120));

        pnParkVehicle.setBackground(new java.awt.Color(255, 255, 255));

        jLabel20.setText("Vehicle Details");

        jLabel21.setText("Registration Number");

        jLabel22.setText("Vehicle Name");

        jLabel23.setText("Owner Name");

        jLabel24.setText("Vehicle Type");

        cbRegNumber.setEditable(true);
        cbRegNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbRegNumberActionPerformed(evt);
            }
        });
        cbRegNumber.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cbRegNumberKeyReleased(evt);
            }
        });

        cbVehicleName.setEditable(true);

        btnParkVehicle.setText("Go");
        btnParkVehicle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnParkVehicleActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnParkVehicleLayout = new javax.swing.GroupLayout(pnParkVehicle);
        pnParkVehicle.setLayout(pnParkVehicleLayout);
        pnParkVehicleLayout.setHorizontalGroup(
            pnParkVehicleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnParkVehicleLayout.createSequentialGroup()
                .addGroup(pnParkVehicleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnParkVehicle)
                    .addGroup(pnParkVehicleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnParkVehicleLayout.createSequentialGroup()
                            .addGap(267, 267, 267)
                            .addComponent(jLabel20))
                        .addGroup(pnParkVehicleLayout.createSequentialGroup()
                            .addGap(48, 48, 48)
                            .addGroup(pnParkVehicleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(pnParkVehicleLayout.createSequentialGroup()
                                    .addGroup(pnParkVehicleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel21)
                                        .addComponent(jLabel23)
                                        .addComponent(jLabel22))
                                    .addGroup(pnParkVehicleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(pnParkVehicleLayout.createSequentialGroup()
                                            .addGap(57, 57, 57)
                                            .addGroup(pnParkVehicleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(tfOwnerName, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(cbRegNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnParkVehicleLayout.createSequentialGroup()
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(cbVehicleName, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(pnParkVehicleLayout.createSequentialGroup()
                                    .addComponent(jLabel24)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbVehicleType, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(315, Short.MAX_VALUE))
        );
        pnParkVehicleLayout.setVerticalGroup(
            pnParkVehicleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnParkVehicleLayout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(jLabel20)
                .addGap(18, 18, 18)
                .addGroup(pnParkVehicleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(cbRegNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnParkVehicleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(cbVehicleName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnParkVehicleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(cbVehicleType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnParkVehicleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfOwnerName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23))
                .addGap(50, 50, 50)
                .addComponent(btnParkVehicle)
                .addContainerGap(133, Short.MAX_VALUE))
        );

        pnMain.add(pnParkVehicle, new org.netbeans.lib.awtextra.AbsoluteConstraints(187, 124, 700, 450));

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
    }//GEN-LAST:event_lbHomeMouseClicked

    private void lbParkVehicleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbParkVehicleMouseClicked
        pnHome.setVisible(false);
        pnParkVehicle.setVisible(true);
    }//GEN-LAST:event_lbParkVehicleMouseClicked

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
                    DisplayMessage.displayInfo("Parked Successfully!");
                    updateFrame();
                    FormUtility.clearFields(pnParkVehicle);
                } else {
                    DisplayMessage.displayError("Operation Failed");
                }
            }
        }

    }//GEN-LAST:event_btnParkVehicleActionPerformed

    private void cbRegNumberKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbRegNumberKeyReleased

    }//GEN-LAST:event_cbRegNumberKeyReleased

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
    private javax.swing.JButton btnParkVehicle;
    private javax.swing.JComboBox<String> cbRegNumber;
    private javax.swing.JComboBox<String> cbSelectedParkingLot;
    private javax.swing.JComboBox<String> cbVehicleName;
    private javax.swing.JComboBox<String> cbVehicleType;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lbBikeCapacity;
    private javax.swing.JLabel lbBikeFee;
    private javax.swing.JLabel lbCarCapacity;
    private javax.swing.JLabel lbCarFee;
    private javax.swing.JLabel lbHeavyVehicleCapacity;
    private javax.swing.JLabel lbHeavyVehicleFee;
    private javax.swing.JLabel lbHome;
    private javax.swing.JLabel lbPLName;
    private javax.swing.JLabel lbPLlocation;
    private javax.swing.JLabel lbParkVehicle;
    private javax.swing.JLabel lbRickshawCapacity;
    private javax.swing.JLabel lbRickshawFee;
    private javax.swing.JLabel lbTotalBike;
    private javax.swing.JLabel lbTotalCar;
    private javax.swing.JLabel lbTotalHeavyVehicle;
    private javax.swing.JLabel lbTotalRickshaw;
    private javax.swing.JPanel pnHome;
    private javax.swing.JPanel pnMain;
    private javax.swing.JPanel pnMenu;
    private javax.swing.JPanel pnParkVehicle;
    private javax.swing.JPanel pnParkedVehicles;
    private javax.swing.JPanel pnParkingFee;
    private javax.swing.JPanel pnParkingLot;
    private javax.swing.JPanel pnTitle;
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

    }
}
