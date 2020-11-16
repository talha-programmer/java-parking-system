package parkingsystem.Database;

import parkingsystem.BusinessLogic.ParkedVehicle;
import parkingsystem.BusinessLogic.ParkingLot;
import parkingsystem.BusinessLogic.Vehicle;
import parkingsystem.Utility.DisplayMessage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;

public class VehicleDB extends Database{

    public int saveVehicle(Vehicle vehicle){
        int insertedId = -1;

        String regNumber = vehicle.getRegNumber();
        String vehicleName = vehicle.getVehicleName();
        String ownerName = vehicle.getOwnerName();
        int vehicleType = vehicle.getVehicleType();

        String query = "INSERT INTO vehicle ";
        query += "(reg_number, vehicle_name, vehicle_type, owner_name) ";
        query += "VALUES (?, ?, ?, ?)";


        try {
            String[] key = {"id"};
            PreparedStatement statement = conn.prepareStatement(query, key);
            statement.setString(1, regNumber);
            statement.setString(2, vehicleName);
            statement.setInt(3, vehicleType);
            statement.setString(4, ownerName);
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if(resultSet.next()){
                insertedId = resultSet.getInt(1);
                return insertedId;
            }

        } catch (SQLException exception) {
            String message = exception.getMessage();
            DisplayMessage.displayError(message);
        }
        return insertedId;
    }

    public ArrayList<Vehicle> getAllVehicle(){
        ArrayList<Vehicle> allVehicles = new ArrayList<>();
        String query = "SELECT * FROM vehicle";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Vehicle vehicle = new Vehicle();
                vehicle.setId(resultSet.getInt("id"));
                vehicle.setRegNumber(resultSet.getString("reg_number"));
                vehicle.setOwnerName(resultSet.getString("owner_name"));
                vehicle.setVehicleName(resultSet.getString("vehicle_name"));
                vehicle.setVehicleType(resultSet.getInt("vehicle_type"));
                allVehicles.add(vehicle);
            }
        } catch (SQLException exception) {
            String message = exception.getMessage();
            DisplayMessage.displayError(message);
        }


        return allVehicles;
    }

    /**
    * Get Registration Numbers and IDs of the vehicles where Registration Number matches
     * to the text provided in the parameter
    * */
    public HashMap<String, Integer> getRegNumbersAndIds(String matchText) {
        HashMap<String, Integer> regNumbersAndIds = new HashMap<>();
        String query = "SELECT reg_number, id FROM vehicle WHERE " +
                "reg_number LIKE '%" + matchText + "%'";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                String regNumber = resultSet.getString("reg_number");
                int id = resultSet.getInt("id");
                regNumbersAndIds.put(regNumber, id);
            }
        } catch (SQLException exception) {
            String message = exception.getMessage();
            DisplayMessage.displayError(message);
        }

        return regNumbersAndIds;
    }

    /**
     * Get matched vehicle names from the database from the text provided
     * Used to suggest vehicle names in combo box
     * */
    public ArrayList<String> getMatchedVehicleNames(String matchText) {
        ArrayList<String> matchedVehicleNames = new ArrayList<>();
        String query = "SELECT DISTINCT vehicle_name FROM vehicle WHERE " +
                "vehicle_name LIKE '%" + matchText + "%'";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                String vehicleName = resultSet.getString("vehicle_name");
                matchedVehicleNames.add(vehicleName);
            }
        } catch (SQLException exception) {
            String message = exception.getMessage();
            DisplayMessage.displayError(message);
        }

        return matchedVehicleNames;
    }

    public boolean updateVehicle(Vehicle vehicle){
        String query = "UPDATE vehicle ";
        query += "SET reg_number = ?, vehicle_name = ?, vehicle_type = ?, owner_name = ? ";
        query += "WHERE id = ?";

        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, vehicle.getRegNumber());
            statement.setString(2, vehicle.getVehicleName());
            statement.setInt(3, vehicle.getVehicleType());
            statement.setString(4, vehicle.getOwnerName());
            statement.setInt(5, vehicle.getId());

            int rowsEffected = statement.executeUpdate();
            if(rowsEffected > 0){
                return true;
            }

        } catch (SQLException exception) {
            String message = exception.getMessage();
            DisplayMessage.displayError(message);
        }
        return false;
    }
}
