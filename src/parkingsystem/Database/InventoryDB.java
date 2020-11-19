package parkingsystem.Database;


import parkingsystem.BusinessLogic.Inventory;
import parkingsystem.Utility.DisplayMessage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class InventoryDB extends Database {
    public int saveInventory(Inventory inventory){
        int insertedId = -1;
        String query = "INSERT INTO inventory ";
        query += "(vehicle_id, parking_lot_id, time_entrance, time_exit, total_fee) ";
        query += "VALUES (?, ?, ?, ?, ?) ";

        try {
            String[] key = {"id"};
            PreparedStatement statement = conn.prepareStatement(query, key);

            statement.setInt(1, inventory.getVehicleId());
            statement.setInt(2, inventory.getParkingLotId());
            statement.setTimestamp(3, inventory.getTimeEntrance());
            statement.setTimestamp(4, inventory.getTimeExit());
            statement.setFloat(5, inventory.getTotalFee());

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

    public ArrayList<Inventory> getInventoryWithDates(int parkingLotId, Timestamp dateFrom, Timestamp dateTo) {
        ArrayList<Inventory> allInventory = new ArrayList<>();

        String query = "SELECT * FROM inventory WHERE time_entrance >= ? AND time_entrance <= ? AND parking_lot_id = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setTimestamp(1, dateFrom);
            statement.setTimestamp(2, dateTo);
            statement.setInt(3, parkingLotId);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Inventory inventory = new Inventory();
                inventory.setId(resultSet.getInt("id"));
                inventory.setVehicleId(resultSet.getInt("vehicle_id"));
                inventory.setParkingLotId(resultSet.getInt("parking_lot_id"));
                inventory.setTotalFee(resultSet.getFloat("total_fee"));
                inventory.setTimeEntrance(resultSet.getTimestamp("time_entrance"));
                inventory.setTimeExit(resultSet.getTimestamp("time_exit"));

                allInventory.add(inventory);
            }
        } catch (SQLException exception) {
            String message = exception.getMessage();
            DisplayMessage.displayError(message);
        }

        return allInventory;
    }
}
