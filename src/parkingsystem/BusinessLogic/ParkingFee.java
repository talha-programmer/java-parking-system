package parkingsystem.BusinessLogic;

import parkingsystem.Database.ParkingFeeDB;

import java.util.HashMap;

public class ParkingFee {
    private int parkingLotId;
    private int vehicleType;
    private float fee;
    private static ParkingFeeDB db = new ParkingFeeDB();

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

    public float getFee() {
        return fee;
    }

    public void setFee(float fee) {
        this.fee = fee;
    }

    public boolean saveParkingFee() {
        return db.saveParkingFee(this);
    }

    public HashMap<Integer, Float> getParkingFee(int parkingLotId){
        return db.getParkingFee(parkingLotId);
    }
}
