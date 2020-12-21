package parkingsystem.Enums;

import java.io.UTFDataFormatException;
import java.util.ArrayList;

public enum UserTypes {
    OWNER("Owner",0), WORKER("Worker", 1);

    private final String name;
    private final int value;

    private UserTypes(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public int getValue() {
        return value;
    }
    public String getName(){ return name; }

    public static int getValueFromName(String name){
        for(UserTypes userType: UserTypes.values()){
            if(userType.name == name){
                return userType.value;
            }
        }
        return -1;
    }

    public static String getNameFromValue(int value){
        for(UserTypes userType: UserTypes.values()){
            if(userType.value == value){
                return userType.name;
            }
        }
        return null;
    }

    public static ArrayList<String> getAllNames(){
        ArrayList<String> allNames = new ArrayList<>();
        for(UserTypes userType: UserTypes.values()){
            allNames.add(userType.name);
        }
        return allNames;
    }

}
