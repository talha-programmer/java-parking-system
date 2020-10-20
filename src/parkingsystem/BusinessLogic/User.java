package parkingsystem.BusinessLogic;

import parkingsystem.Database.UserDB;
import parkingsystem.Utility.PasswordAuthentication;

import java.util.ArrayList;

public class User {
    private int id;
    private String fullName;
    private String username;
    private String password;
    private String email;
    private int userType;
    private final UserDB db = new UserDB();

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
    * Register the user in the database after password hashing and return true if succeed
    * Otherwise return false
    * */
    public boolean registerUser(String fullName, String username, String password, String email, int userType){
        PasswordAuthentication authentication = new PasswordAuthentication();
        String hashedPassword = authentication.hash(password);
        this.fullName = fullName;
        this.username = username;
        this.password = hashedPassword;
        this.email = email;
        this.userType = userType;
        int insertedId = db.registerUser(this);
        this.id = insertedId;
        return insertedId > 0;
    }


    /**
    * Authenticate by checking the password and then get whole information of the user from the DB
     * and then replace the self object with the object came through database class
    * */
    public boolean login(String username, String password){
        String storedPassword =  db.getPassword(username);

        if(new PasswordAuthentication().authenticate(password, storedPassword)){
            User user = db.getUser(username);
            if(user != null){
                this.username = user.getUsername();
                this.password = user.getPassword();
                this.fullName = user.getFullName();
                this.email = user.getEmail();
                this.userType = user.getUserType();
                this.id = user.getId();
                return true;
            }
        }
        return false;
    }

    /**
    *
    * */
    public boolean updatePassword(String currentPassword, String newPassword){
        if(new PasswordAuthentication().authenticate(currentPassword, this.password)){
            return db.updatePassword(this.username, newPassword);
        }

        return false;
    }

    /**
     *
     * */
    public boolean updateProfile(String newFullName, String newEmail){
        return db.updateProfile(this.username, newFullName, newEmail);
    }

    ArrayList<String> getAllUsernames(){
        return db.getAllUsernames();
    }
}