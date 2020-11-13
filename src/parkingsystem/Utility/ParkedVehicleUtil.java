package parkingsystem.Utility;

import parkingsystem.BusinessLogic.ParkedVehicle;
import parkingsystem.BusinessLogic.Vehicle;
import parkingsystem.Enums.VehicleTypes;

import java.util.ArrayList;
import java.util.HashMap;

public class ParkedVehicleUtil {
    private ParkedVehicle parkedVehicle = new ParkedVehicle();
    private Vehicle vehicle = new Vehicle();
    private VehicleUtil vehicleUtil = new VehicleUtil();

    public HashMap<Integer, Integer> getTotalParkedVehicles(int parkingLotId){
        // Key represents Vehicle Type and value represents total vehicles parked
        HashMap<Integer, Integer> totalVehicles = new HashMap<>();
        ArrayList<ParkedVehicle> allParkedVehicles = parkedVehicle.getParkedVehicleWithPLId(parkingLotId);

        // Initialize the hashmap with zero value for all parking types
        totalVehicles.put(VehicleTypes.BIKE.getValue(), 0);
        totalVehicles.put(VehicleTypes.RICKSHAW.getValue(), 0);
        totalVehicles.put(VehicleTypes.CAR.getValue(), 0);
        totalVehicles.put(VehicleTypes.HEAVY_VEHICLE.getValue(), 0);

        for(ParkedVehicle tmpParkedVehicle: allParkedVehicles){
            int vehicleId = tmpParkedVehicle.getVehicleId();
            int vehicleType = vehicleUtil.getVehicleWithId(vehicleId).getVehicleType();
            totalVehicles.put(vehicleType, totalVehicles.get(vehicleType) + 1);
        }

        return totalVehicles;
    }
}
