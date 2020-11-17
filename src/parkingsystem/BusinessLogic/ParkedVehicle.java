package parkingsystem.BusinessLogic;

import parkingsystem.Database.ParkedVehicleDB;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Manage currently parked vehicles in a parking lot with this class
 * */
public class ParkedVehicle {
    private int id, parkingLotId, vehicleId;
    private Timestamp parkTime;
    private static ParkedVehicleDB db = new ParkedVehicleDB();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParkingLotId() {
        return parkingLotId;
    }

    public void setParkingLotId(int parkingLotId) {
        this.parkingLotId = parkingLotId;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Timestamp getParkTime() {
        return parkTime;
    }

    public void setParkTime(Timestamp parkTime) {
        this.parkTime = parkTime;
    }

    public ArrayList<ParkedVehicle> getAllParkedVehicle(){
        return db.getAllParkedVehicle();
    }

    public ArrayList<ParkedVehicle> getParkedVehicleWithPLId(int parkingLotId){
        return db.getParkedVehicleWithPLId(parkingLotId);
    }

    public int saveParkedVehicle(){
        this.id = db.saveParkedVehicle(this);
        return this.id;
    }

    public ArrayList<Integer> getMatchedIds(String matchText, int parkingLotId){
        return db.getMatchedIds(matchText, parkingLotId);
    }


    public boolean deleteParkedVehicle(int parkedVehicleId) {
        return db.deleteParkedVehicle(parkedVehicleId);
    }
}
