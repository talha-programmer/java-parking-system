package parkingsystem.Database;


import parkingsystem.BusinessLogic.Inventory;
import parkingsystem.Utility.DisplayMessage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
}
