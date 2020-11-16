package parkingsystem.BusinessLogic;

import parkingsystem.Database.VehicleDB;

import java.util.ArrayList;
import java.util.HashMap;

public class Vehicle {
    private int id = -1;
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

    public int saveVehicle(){
        // Update the vehicle if already saved, otherwise add new vehicle
        if(id > 0){
            db.updateVehicle(this);
            return id;
        } else {
            return db.saveVehicle(this);
        }
    }

    public ArrayList<Vehicle> getAllVehicle(){
        return db.getAllVehicle();
    }

    public HashMap<String, Integer> getRegNumbersAndIds(String matchText){
        return db.getRegNumbersAndIds(matchText);
    }

    public ArrayList<String> getMatchedVehicleNames(String matchText){
        return db.getMatchedVehicleNames(matchText);
    }


}
