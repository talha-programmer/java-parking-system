package parkingsystem.BusinessLogic;

import parkingsystem.Database.ParkingLotAllocationDB;

import java.util.ArrayList;
import java.util.HashMap;

public class ParkingLotAllocation {
    private int parkingLotId;
    private int userId;
    private static ParkingLotAllocationDB db = new ParkingLotAllocationDB();

    public int getParkingLotId() {
        return parkingLotId;
    }

    public void setParkingLotId(int parkingLotId) {
        this.parkingLotId = parkingLotId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public boolean saveParkingLotAllocation(){
        return db.saveParkingLotAllocation(this);
    }

    public ArrayList<ParkingLotAllocation> getAllPLAllocation(){
        return db.getAllPLAllocation();
    }

    public boolean deleteParkingLotAllocation(){
        return db.deleteParkingLotAllocation(this);
    }
}
