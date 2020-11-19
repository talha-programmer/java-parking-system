package parkingsystem.Utility;

import parkingsystem.BusinessLogic.ParkingLot;

import java.util.ArrayList;
import java.util.HashMap;

public class ParkingLotUtil {
    private ParkingLot parkingLot = null;
    private HashMap<Integer, ParkingLot> parkingLotWithIds = null;
    private HashMap<String, ParkingLot> parkingLotWithNames = null;


    public ParkingLotUtil(){
        parkingLot = new ParkingLot();
        parkingLotWithIds = new HashMap<>();
        parkingLotWithNames = new HashMap<>();
        ArrayList<ParkingLot> allParkingLots = parkingLot.getAllParkingLot();
        for(ParkingLot pl:allParkingLots){
            parkingLotWithIds.put(pl.getId(), pl);
            parkingLotWithNames.put(pl.getName(), pl);
        }

    }

    public ParkingLot getParkingLotFromId(int id){
        return parkingLotWithIds.get(id);
    }

    public ParkingLot getParkingLotFromName(String name){
        return parkingLotWithNames.get(name);
    }

    public String getPLNameFromId(int id){
        ParkingLot pl = parkingLotWithIds.getOrDefault(id, null);
        return pl == null ? null: pl.getName();
    }

    public int getPLIdFromName(String name){
        return parkingLotWithNames.get(name).getId();
    }

    public HashMap<Integer, ParkingLot> getAllParkingLotWithIds() {
        return parkingLotWithIds;
    }

    public HashMap<String, ParkingLot> getAllParkingLotWithNames() {
        return parkingLotWithNames;
    }

    public ArrayList<String> getAllPLNames(){
        ArrayList<String> plNames = new ArrayList<>();
        for(int key:parkingLotWithIds.keySet()){
            plNames.add(parkingLotWithIds.get(key).getName());
        }

        return plNames;
    }

}
