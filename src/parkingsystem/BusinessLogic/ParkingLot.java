package parkingsystem.BusinessLogic;

import parkingsystem.Database.ParkingLotDB;

import java.util.ArrayList;
import java.util.HashMap;

public class ParkingLot {
    private int id = -1;
    private String name;
    private String location;
    private HashMap<Integer, Integer> vehicleCapacity;      // key represents vehicle type and value represents capacity
    private HashMap<Integer, Float> parkingFeeMap;
    private static ParkingLotDB db = new ParkingLotDB();
    private ParkingLotCapacity parkingLotCapacity = null;
    private ParkingFee parkingFee = null;


    public ParkingLot(){
        vehicleCapacity = new HashMap<>();
        parkingLotCapacity = new ParkingLotCapacity();
        parkingFeeMap = new HashMap<>();
        parkingFee = new ParkingFee();

    }

    public void setVehicleCapacity(int vehicleType, int capacity){
        vehicleCapacity.put(vehicleType, capacity);
    }

    public int getSingleVehicleCapacity(int vehicleType){
        return vehicleCapacity.getOrDefault(vehicleType, null);
    }

    public void setParkingFee(int vehicleType, float feePerHour){
        parkingFeeMap.put(vehicleType, feePerHour);
    }

    public float getSingleParkingFee(int vehicleType){
        return parkingFeeMap.getOrDefault(vehicleType, null);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean saveParkingLot(){
        int insertedId = -1;

        // Update existing Parking Lot if id is set already
        if(this.id > 0){
            if(db.updateParkingLot(this) == false){
                return false;
            }
            insertedId = this.id;

            // Delete all vehicle's capacity and parking fee and add them again in case of update parking lot
            parkingLotCapacity.deleteAllVehicleCapacities(this.id);
            parkingFee.deleteAllParkingFee(this.id);
        }else {
            insertedId = db.saveParkingLot(this);
        }
        if(insertedId > 0){
            this.id = insertedId;

            for(int key : vehicleCapacity.keySet()){
                parkingLotCapacity = new ParkingLotCapacity();
                parkingLotCapacity.setParkingLotId(this.id);
                parkingLotCapacity.setVehicleType(key);
                parkingLotCapacity.setCapacity(this.vehicleCapacity.getOrDefault(key, null));

                if(!parkingLotCapacity.saveParkingLotCapacity()){
                    return false;
                }
            }
            for(int key : parkingFeeMap.keySet()){
                parkingFee = new ParkingFee();
                parkingFee.setParkingLotId(this.id);
                parkingFee.setVehicleType(key);
                parkingFee.setFee(this.parkingFeeMap.getOrDefault(key, null));

                if(!parkingFee.saveParkingFee()){
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public ArrayList<ParkingLot> getAllParkingLot(){
        ArrayList<ParkingLot> parkingLots = db.getAllParkingLots();
        for(ParkingLot parkingLot: parkingLots){
            parkingLot.vehicleCapacity = parkingLotCapacity.getVehicleCapacity(parkingLot.id);
            parkingLot.parkingFeeMap = parkingFee.getParkingFee(parkingLot.id);
        }

        return parkingLots;
    }

    public boolean deleteParkingLot(int id){
        // Delete the data of parking lot vehicle's capacity, parking fee and
        // parking lot allocation before deleting the parking lot
        parkingLotCapacity.deleteAllVehicleCapacities(id);
        parkingFee.deleteAllParkingFee(id);
        new ParkingLotAllocation().deletePLAllocationWithPLid(id);

        return db.deleteParkingLot(id);
    }

}
