package parkingsystem.BusinessLogic;

import parkingsystem.Database.ParkingLotDB;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private int id = -1;
    private String name;
    private String location;
    private HashMap<Integer, Integer> vehicleCapacity;      // key represents vehicle type and value represents capacity
    private static ParkingLotDB db = new ParkingLotDB();
    private ParkingLotCapacity parkingLotCapacity = null;
    public ParkingLot(){
        vehicleCapacity = new HashMap<>();
        parkingLotCapacity = new ParkingLotCapacity();

    }

    public void setVehicleCapacity(int vehicleType, int capacity){
        vehicleCapacity.put(vehicleType, capacity);
    }

    public int getSingleVehicleCapacity(int vehicleType){
        return vehicleCapacity.get(vehicleType);
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

            // Delete all vehicle's capacity and add them again in case of update parking lot
            parkingLotCapacity.deleteAllVehicleCapacities(this.id);
        }else {
            insertedId = db.saveParkingLot(this);
        }
        if(insertedId > 0){
            this.id = insertedId;

            for(int key : vehicleCapacity.keySet()){
                parkingLotCapacity = new ParkingLotCapacity();
                parkingLotCapacity.setParkingLotId(this.id);
                parkingLotCapacity.setVehicleType(key);
                parkingLotCapacity.setCapacity(this.vehicleCapacity.get(key));

                if(!parkingLotCapacity.saveParkingLotCapacity()){
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
        }

        return parkingLots;
    }

    public boolean deleteParkingLot(int id){
        // Delete the data of parking lot vehicle's capacity before deleting the parking lot
        if(parkingLotCapacity.deleteAllVehicleCapacities(id)){
            if(db.deleteParkingLot(id)){
                return true;
            }
        }
         return false;
    }

}
