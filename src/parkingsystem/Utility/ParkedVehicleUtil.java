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
    private HashMap<Integer, ParkedVehicle> allParkedVehicles = new HashMap<>();

    public ParkedVehicleUtil(){
        ArrayList<ParkedVehicle> parkedVehicles =   parkedVehicle.getAllParkedVehicle();
        for(ParkedVehicle parkedVehicle: parkedVehicles){
            allParkedVehicles.put(parkedVehicle.getId(), parkedVehicle);
        }
    }

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

    public ParkedVehicle getParkedVehicleWithId(int pvId){
        return allParkedVehicles.getOrDefault(pvId, null);
    }

    public ParkedVehicle getParkedVehicleWithPLId(int parkingLotId, int parkedVehicleId){
        ArrayList<ParkedVehicle> pvInCurrentPL = parkedVehicle.getParkedVehicleWithPLId(parkingLotId);
        for(ParkedVehicle pv: pvInCurrentPL){
            if(pv.getId() == parkedVehicleId){
                return pv;
            }
        }
        return null;
    }

    public ParkedVehicle getParkedVehicleWithVehicleId(int parkingLotId, int vehicleId) {
        ArrayList<ParkedVehicle> pvInCurrentPL = parkedVehicle.getParkedVehicleWithPLId(parkingLotId);
        for(ParkedVehicle pv: pvInCurrentPL){
            if(pv.getVehicleId() == vehicleId){
                return pv;
            }
        }
        return null;
    }
}
