package parkingsystem.BusinessLogic;

public class ParkingLotCapacity {
    int parkingLotId;
    int vehicleType;
    int capacity;

    public ParkingLotCapacity(int parkingLotId, int vehicleType, int capacity) {
        this.parkingLotId = parkingLotId;
        this.vehicleType = vehicleType;
        this.capacity = capacity;
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

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
