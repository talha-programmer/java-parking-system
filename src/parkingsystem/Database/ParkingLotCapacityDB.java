package parkingsystem.Database;

import parkingsystem.BusinessLogic.ParkingLotCapacity;
import parkingsystem.Utility.DisplayMessage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

}
