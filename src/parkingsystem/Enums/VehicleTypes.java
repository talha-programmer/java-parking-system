package parkingsystem.Enums;

import java.util.ArrayList;

public enum VehicleTypes {
    BIKE (0, "Bike"),
    RICKSHAW (1, "Rickshaw"),
    CAR (2, "Car"),
    HEAVY_VEHICLE (3, "Heavy Vehicle");

    private final int value;
    private final String name;
    private VehicleTypes(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public static int getValueFromName(String name){
        for(VehicleTypes vehicleType: VehicleTypes.values()){
            if(vehicleType.name == name){
                return vehicleType.value;
            }
        }
        return -1;
    }

    public static String getNameFromValue(int value){
        for(VehicleTypes vehicleType: VehicleTypes.values()){
            if(vehicleType.value == value){
                return vehicleType.name;
            }
        }
        return null;
    }

    public static ArrayList<String> getAllNames(){
        ArrayList<String> allNames = new ArrayList<>();
        for(VehicleTypes vehicleType: VehicleTypes.values()){
            allNames.add(vehicleType.name);
        }
        return allNames;
    }
}
