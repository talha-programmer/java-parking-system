package parkingsystem.BusinessLogic;

import parkingsystem.Enums.UserTypes;

public class Owner extends User {


    public boolean registerUser(String fullName, String username, char[] password, String email) {
        int userType = UserTypes.OWNER.getValue();
        return super.registerUser(fullName, username, password, email, userType);
    }
}
