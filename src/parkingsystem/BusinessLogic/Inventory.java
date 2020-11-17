package parkingsystem.BusinessLogic;

import parkingsystem.Database.InventoryDB;

import java.sql.Timestamp;

public class Inventory {
    private int id, parkingLotId, vehicleId;
    private Timestamp timeEntrance, timeExit;
    private float totalFee;
    private static InventoryDB db = new InventoryDB();

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

    public Timestamp getTimeEntrance() {
        return timeEntrance;
    }

    public void setTimeEntrance(Timestamp timeEntrance) {
        this.timeEntrance = timeEntrance;
    }

    public Timestamp getTimeExit() {
        return timeExit;
    }

    public void setTimeExit(Timestamp timeExit) {
        this.timeExit = timeExit;
    }

    public float getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(float totalFee) {
        this.totalFee = totalFee;
    }

    public boolean saveInventory(){
        int id = db.saveInventory(this);
        if(id > 0){
            this.id = id;
            return true;
        }
        return false;
    }
}
