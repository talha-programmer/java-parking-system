package parkingsystem.Database;

import parkingsystem.BusinessLogic.ParkingFee;
import parkingsystem.Utility.DisplayMessage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class ParkingFeeDB extends Database{

    public boolean saveParkingFee(ParkingFee parkingFee){
        int parkingLotId = parkingFee.getParkingLotId();
        int vehicleType = parkingFee.getVehicleType();
        float fee = parkingFee.getFee();

        String query = "INSERT INTO parking_fee ";
        query += "(parking_lot_id, vehicle_type, parking_fee) ";
        query += "VALUES (?, ?, ?) ";

        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, parkingLotId);
            statement.setInt(2, vehicleType);
            statement.setFloat(3, fee);

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

    public HashMap<Integer, Float> getParkingFee(int parkingLotId){
        HashMap<Integer, Float> parkingLotCapacity = new HashMap<>();
        String query = "SELECT * FROM parking_fee WHERE parking_lot_id = ? ";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, parkingLotId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                int vehicleType = resultSet.getInt("vehicle_type");
                float fee = resultSet.getFloat("parking_fee");

                parkingLotCapacity.put(vehicleType, fee);
            }
        } catch (SQLException exception) {
            String message = exception.getMessage();
            DisplayMessage.displayError(message);
        }

        return parkingLotCapacity;
    }

    /**
     * Delete the data of all vehicle's capacity of a parking lot through
     * parking lot id. To delete a parking lot, we must delete all data
     * of parking vehicle's capacity from the database
     * */
    public boolean deleteAllVehicleCapacities(int parkingLotId) {
        String query = "DELETE FROM parking_fee ";
        query += "WHERE parking_lot_id = ? ";

        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, parkingLotId);
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
