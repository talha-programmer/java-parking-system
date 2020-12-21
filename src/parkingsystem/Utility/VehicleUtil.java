package parkingsystem.Utility;

import parkingsystem.BusinessLogic.Vehicle;

import java.util.ArrayList;
import java.util.HashMap;

public class VehicleUtil {
    private Vehicle vehicle = null;
    private HashMap<Integer, Vehicle> allVehicleWithIds = null;

    public VehicleUtil(){
        vehicle = new Vehicle();
        allVehicleWithIds = new HashMap<>();

        ArrayList<Vehicle> allVehicles = vehicle.getAllVehicle();
        for(Vehicle tmpVehicle: allVehicles){
            allVehicleWithIds.put(tmpVehicle.getId(), tmpVehicle);
        }
    }

    public Vehicle getVehicleWithId(int vehicleId){
        return allVehicleWithIds.getOrDefault(vehicleId, null);
    }
}
