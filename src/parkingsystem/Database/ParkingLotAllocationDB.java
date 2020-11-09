package parkingsystem.Database;

import parkingsystem.BusinessLogic.ParkingLotAllocation;
import parkingsystem.Utility.DisplayMessage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ParkingLotAllocationDB extends Database{

    public boolean saveParkingLotAllocation(ParkingLotAllocation parkingLotAllocation){
        int parkingLotId = parkingLotAllocation.getParkingLotId();
        int userId = parkingLotAllocation.getUserId();

        String query = "INSERT INTO parking_lot_allocation ";
        query += "(parking_lot_id, user_id) ";
        query += "VALUES (?, ?) ";

        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, parkingLotId);
            statement.setInt(2, userId);

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

    public ArrayList<ParkingLotAllocation> getAllPLAllocation(){
        ArrayList<ParkingLotAllocation> allPLAllocation = new ArrayList<>();
        String query = "SELECT * FROM parking_lot_allocation ";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                ParkingLotAllocation parkingLotAllocation = new ParkingLotAllocation();
                int userId = resultSet.getInt("user_id");
                int parkingLotId = resultSet.getInt("parking_lot_id");

                parkingLotAllocation.setUserId(userId);
                parkingLotAllocation.setParkingLotId(parkingLotId);

                allPLAllocation.add(parkingLotAllocation);
            }
        } catch (SQLException exception) {
            String message = exception.getMessage();
            DisplayMessage.displayError(message);
        }

        return allPLAllocation;
    }
}
