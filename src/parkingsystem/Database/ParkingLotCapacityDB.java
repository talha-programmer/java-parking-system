package parkingsystem.Database;

import parkingsystem.BusinessLogic.ParkingLotCapacity;
import parkingsystem.Utility.DisplayMessage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class ParkingLotCapacityDB extends Database{

    public boolean saveParkingLotCapacity(ParkingLotCapacity parkingLotCapacity){
        int parkingLotId = parkingLotCapacity.getParkingLotId();
        int vehicleType = parkingLotCapacity.getVehicleType();
        int capacity = parkingLotCapacity.getCapacity();

        String query = "INSERT INTO parking_lot_capacity ";
        query += "(parking_lot_id, vehicle_type, capacity) ";
        query += "VALUES (?, ?, ?) ";

        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, parkingLotId);
            statement.setInt(2, vehicleType);
            statement.setInt(3, capacity);

            int rowCount = statement.executeUpdate();
            if(rowCount > 0){
                return true;
            }
        } catch (SQLException exception) {
            String message = exception.getMessage();
            DisplayMessage.displayError(message);
        }
        return false;
    }

    public HashMap<Integer, Integer> getVehicleCapacity(int parkingLotId){
        HashMap<Integer, Integer> parkingLotCapacity = new HashMap<>();
        String query = "SELECT * FROM parking_lot_capacity WHERE parking_lot_id = ? ";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, parkingLotId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                int vehicleType = resultSet.getInt("vehicle_type");
                int capacity = resultSet.getInt("capacity");

                parkingLotCapacity.put(vehicleType, capacity);
            }
        } catch (SQLException exception) {
            String message = exception.getMessage();
            DisplayMessage.displayError(message);
        }

        return parkingLotCapacity;
    }

}
