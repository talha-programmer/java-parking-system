package parkingsystem.Database;

import parkingsystem.BusinessLogic.ParkedVehicle;
import parkingsystem.BusinessLogic.User;
import parkingsystem.Utility.DisplayMessage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ParkedVehicleDB extends Database{

    public ArrayList<ParkedVehicle> getAllParkedVehicle(){
        ArrayList<ParkedVehicle> parkedVehicles = new ArrayList<>();
        String query = "SELECT * FROM parked_vehicle";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                ParkedVehicle parkedVehicle = new ParkedVehicle();
                parkedVehicle.setId(resultSet.getInt("id"));
                parkedVehicle.setParkingLotId(resultSet.getInt("parking_lot_id"));
                parkedVehicle.setParkTime(resultSet.getTimestamp("time_parked"));
                parkedVehicles.add(parkedVehicle);
            }
        } catch (SQLException exception) {
            String message = exception.getMessage();
            DisplayMessage.displayError(message);
        }

        return parkedVehicles;
    }

    public ArrayList<ParkedVehicle> getParkedVehicleWithPLId(int parkingLotId) {
        ArrayList<ParkedVehicle> parkedVehicles = new ArrayList<>();
        String query = "SELECT * FROM parked_vehicle WHERE parking_lot_id = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, parkingLotId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                ParkedVehicle parkedVehicle = new ParkedVehicle();
                parkedVehicle.setId(resultSet.getInt("id"));
                parkedVehicle.setParkingLotId(resultSet.getInt("parking_lot_id"));
                parkedVehicle.setParkTime(resultSet.getTimestamp("time_parked"));
                parkedVehicles.add(parkedVehicle);
            }
        } catch (SQLException exception) {
            String message = exception.getMessage();
            DisplayMessage.displayError(message);
        }

        return parkedVehicles;
    }
}
