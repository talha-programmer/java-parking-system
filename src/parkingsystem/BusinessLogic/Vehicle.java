package parkingsystem.BusinessLogic;

import parkingsystem.Database.VehicleDB;

import java.util.ArrayList;

public class Vehicle {
    private int id;
    private String regNumber;
    private String ownerName = null;        // Owner name is not mandatory
    private String vehicleName;
    private int vehicleType;

    private static VehicleDB db = new VehicleDB();

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public int getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(int vehicleType) {
        this.vehicleType = vehicleType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean saveVehicle(){
        int id = db.saveVehicle(this);
        if(id > 0){
            this.id = id;
            return true;
        }
        return false;
    }

    public ArrayList<Vehicle> getAllVehicle(){
        return db.getAllVehicle();
    }
}
