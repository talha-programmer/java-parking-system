package parkingsystem.BusinessLogic;

import parkingsystem.Database.ParkingLotCapacityDB;
import parkingsystem.Utility.DisplayMessage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class ParkingLotCapacity {
    private int parkingLotId;
    private int vehicleType;
    private int capacity;
    private static ParkingLotCapacityDB db = new ParkingLotCapacityDB();

    public int getParkingLotId() {
        return parkingLotId;
    }

    public void setParkingLotId(int parkingLotId) {
        this.parkingLotId = parkingLotId;
    }

    public int getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(int vehicleType) {
        this.vehicleType = vehicleType;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean saveParkingLotCapacity(){
        return db.saveParkingLotCapacity(this);
    }

    public HashMap<Integer, Integer> getVehicleCapacity(int parkingLotId){
        return db.getVehicleCapacity(parkingLotId);
    }

    public boolean deleteAllVehicleCapacities(int parkingLotId) {
        return db.deleteAllVehicleCapacities(parkingLotId);
    }
}
