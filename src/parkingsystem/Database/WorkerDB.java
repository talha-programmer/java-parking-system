package parkingsystem.Database;

import parkingsystem.BusinessLogic.User;
import parkingsystem.BusinessLogic.Worker;
import parkingsystem.Enums.UserTypes;
import parkingsystem.Utility.DisplayMessage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class WorkerDB extends Database{

    public ArrayList<Worker> getAllWorker(){
        ArrayList<Worker> workers = new ArrayList<>();
        String query = "SELECT * FROM users WHERE user_type = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, UserTypes.WORKER.getValue());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Worker worker = new Worker();
                worker.setId(resultSet.getInt("id"));
                worker.setUserType(resultSet.getInt("user_type"));
                worker.setUsername(resultSet.getString("username"));
                worker.setEmail(resultSet.getString("email"));
                worker.setFullName(resultSet.getString("full_name"));
                workers.add(worker);
            }
        } catch (SQLException exception) {
            String message = exception.getMessage();
            DisplayMessage.displayError(message);
        }
        return workers;
    }

    public ArrayList<String> getAllUsernames(){
        ArrayList<String> usernames = new ArrayList<>();
        String query = "SELECT username FROM users WHERE user_type = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, UserTypes.WORKER.getValue());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                usernames.add(resultSet.getString("username"));
            }
        } catch (SQLException exception) {
            String message = exception.getMessage();
            DisplayMessage.displayError(message);
        }

        return usernames;
    }
}
