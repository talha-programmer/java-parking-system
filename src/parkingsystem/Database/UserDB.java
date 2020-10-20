package parkingsystem.Database;

import parkingsystem.BusinessLogic.User;

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
            exception.printStackTrace();
        }

        return insertedId;
    }

    public String getPassword(String username){
        String password = "";
        String query = "SELECT password FROM users WHERE username = ? ";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()){
                password = resultSet.getString("password");
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return password;
    }

    public User getUser(String username){
        User user = new User();
        String query = "SELECT * FROM users WHERE username = ? LIMIT 1";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()){
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setFullName(resultSet.getString("full_name"));
                user.setEmail(resultSet.getString("email"));
                user.setUserType(resultSet.getInt("user_type"));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
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
            exception.printStackTrace();
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
            exception.printStackTrace();
        }
        return false;
    }

    public ArrayList<String> getAllUsernames(){
        ArrayList<String> usernames = new ArrayList<>();
        String query = "SELET username FROM users;";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                usernames.add(resultSet.getString("username"));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return usernames;
    }


}
