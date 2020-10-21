package parkingsystem.Database;

import parkingsystem.BusinessLogic.ParkingLot;
import parkingsystem.Utility.DisplayMessage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ParkingLotDB extends Database{

    public boolean updateParkingLot(ParkingLot parkingLot){
        int id = parkingLot.getId();
        String location = parkingLot.getLocation();
        String name = parkingLot.getName();

        String query = "UPDATE parking_lot ";
        query += "SET name = ?, location = ? ";
        query += "WHERE id = ?";

        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, name);
            statement.setString(2, location);
            statement.setInt(3, id);

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

    public ArrayList<ParkingLot> getAllParkingLots(){
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();

        String query = "SELET * FROM parking_lot;";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                ParkingLot parkingLot = new ParkingLot();
                parkingLot.setId(resultSet.getInt("id"));
                parkingLot.setName(resultSet.getString("name"));
                parkingLot.setLocation(resultSet.getString("location"));

                parkingLots.add(parkingLot);
            }
        } catch (SQLException exception) {
            String message = exception.getMessage();
            DisplayMessage.displayError(message);
        }
        return parkingLots;
    }

    boolean deleteParkingLot(int id){
        String query = "DELETE FROM parking_lot ";
        query += "WHERE id = ? ";

        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);

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
