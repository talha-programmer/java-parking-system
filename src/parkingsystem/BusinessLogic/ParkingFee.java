package parkingsystem.BusinessLogic;

public class ParkingFee {
    private int parkingLotId;
    private int vehicleType;
    private float fee;

    public ParkingFee(int parkingLotId, int vehicleType, float fee) {
        this.parkingLotId = parkingLotId;
        this.vehicleType = vehicleType;
        this.fee = fee;
    }

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

}
