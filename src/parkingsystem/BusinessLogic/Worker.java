package parkingsystem.BusinessLogic;

import parkingsystem.Enums.UserTypes;

public class Worker extends User{

    public boolean registerUser(String fullName, String username, char[] password, String email) {
        int userType = UserTypes.WORKER.getValue();
        return super.registerUser(fullName, username, password, email, userType);
    }
}
