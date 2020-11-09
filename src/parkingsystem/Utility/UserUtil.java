package parkingsystem.Utility;

import parkingsystem.BusinessLogic.User;

import java.util.ArrayList;
import java.util.HashMap;

public class UserUtil {
    private User user = null;
    private HashMap<Integer, User> usersWithId = null;
    private HashMap<String, User> usersWithUsername = null;

    public UserUtil(){
        user = new User();
        usersWithId = new HashMap<>();

        ArrayList<User> allUsers = user.getAllUsers();
        for(User tmpUser:allUsers){
            usersWithId.put(tmpUser.getId(), tmpUser);
            usersWithUsername.put(tmpUser.getUsername(), tmpUser);
        }
    }

    public String getUsernameFromId(int id){
        return usersWithId.get(id).getUsername();
    }

    public int getIdFromUsername(String username){
        return usersWithUsername.get(username).getId();
    }

    public User getUserFromId(int id){
        return usersWithId.get(id);
    }

    public User getUserFromUsername(String username){
        return usersWithUsername.get(username);
    }
}
