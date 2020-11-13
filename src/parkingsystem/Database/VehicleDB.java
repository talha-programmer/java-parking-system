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
}
