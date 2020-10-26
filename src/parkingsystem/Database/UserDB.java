package parkingsystem.Database;

import parkingsystem.BusinessLogic.User;
import parkingsystem.Utility.DisplayMessage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDB extends Database{


    public int registerUser(User user){
        int insertedId = -1;

        String fullName = user.getFullName();
        String username = user.getUsername();
        String password = user.getPassword();
        String email = user.getEmail();
        int userType = user.getUserType();
        String query = "INSERT INTO users ";
        query += "(username, password, full_name, email, user_type) ";
        query += "VALUES (?, ?, ?, ?, ?)";

        try {
            String[] key = {"id"};
            PreparedStatement statement = conn.prepareStatement(query, key);
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, fullName);
            statement.setString(4, email);
            statement.setInt(5, userType);
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

    public String getPassword(String username){
        String password = "";
        String query = "SELECT password FROM users WHERE username = ? ";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                password = resultSet.getString("password");
            }
        } catch (SQLException exception) {
            String message = exception.getMessage();
            DisplayMessage.displayError(message);
        }

        return password;
    }

    public User getUser(String username){
        User user = new User();
        String query = "SELECT * FROM users WHERE username = ? LIMIT 1";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setFullName(resultSet.getString("full_name"));
                user.setEmail(resultSet.getString("email"));
                user.setUserType(resultSet.getInt("user_type"));
            }
        } catch (SQLException exception) {
            String message = exception.getMessage();
            DisplayMessage.displayError(message);
        }
        return  user;
    }

    public boolean updatePassword(String username, String newPassword) {
        String query = "UPDATE users ";
        query += "SET password = ? ";
        query += "WHERE username = ?";

        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, newPassword);
            statement.setString(2, username);

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

    public boolean updateProfile(String username, String newFullName, String newEmail) {
        String query = "UPDATE users ";
        query += "SET full_name = ?, email = ? ";
        query += "WHERE username = ?";

        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, newFullName);
            statement.setString(2, newEmail);
            statement.setString(3, username);

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

    public ArrayList<String> getAllUsernames(){
        ArrayList<String> usernames = new ArrayList<>();
        String query = "SELECT username FROM users;";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
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

    public boolean deleteUser(String username){
        String query = "DELETE FROM users ";
        query += "WHERE username = ? ";

        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, username);

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

    public ArrayList<User> getAllUsers(){
        ArrayList<User> users = new ArrayList<>();
        String query = "SELECT * FROM users";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUserType(resultSet.getInt("user_type"));
                user.setUsername(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                user.setFullName(resultSet.getString("full_name"));
                users.add(user);
            }
        } catch (SQLException exception) {
            String message = exception.getMessage();
            DisplayMessage.displayError(message);
        }
        return users;
    }
}
