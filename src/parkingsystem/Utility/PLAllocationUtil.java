package parkingsystem.Utility;

import parkingsystem.BusinessLogic.ParkingLotAllocation;
import parkingsystem.BusinessLogic.Worker;

import java.util.ArrayList;
import java.util.HashMap;

public class PLAllocationUtil {
    private ParkingLotAllocation plAllocation = null;
    private Worker worker = null;
    private HashMap<String, ArrayList<String>> plAllocationWithUsername = null;

    public PLAllocationUtil() {
        worker = new Worker();
        plAllocation = new ParkingLotAllocation();
        plAllocationWithUsername = new HashMap<>();
        ParkingLotUtil plUtil = new ParkingLotUtil();

        ArrayList<Worker> allWorkers = worker.getAllWorker();
        ArrayList<ParkingLotAllocation> allPLAllocation = plAllocation.getAllPLAllocation();

        for (Worker tmpWorker : allWorkers) {
            ArrayList<String> parkingLotNames = new ArrayList<>();
            for (ParkingLotAllocation parkingLotAllocation : allPLAllocation) {
                int userId = tmpWorker.getId();
                int plUserId = parkingLotAllocation.getUserId();
                int parkingLotId = parkingLotAllocation.getParkingLotId();
                if (userId == plUserId) {
                    String currentPLName = plUtil.getPLNameFromId(parkingLotId);
                    parkingLotNames.add(currentPLName);
                }

            }
            if (parkingLotNames.size() > 0) {
                plAllocationWithUsername.put(tmpWorker.getUsername(), parkingLotNames);
            }
        }


    }

    public ArrayList<String> getPLNamesWithUsername(String username){
        return plAllocationWithUsername.get(username);
    }

    public HashMap<String, ArrayList<String>> getAllPLAllocationWithUsername() {
        return plAllocationWithUsername;
    }
}
