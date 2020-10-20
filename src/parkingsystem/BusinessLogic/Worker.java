package parkingsystem.BusinessLogic;

import parkingsystem.Enums.UserTypes;

public class Worker extends User{

    public boolean registerUser(String fullName, String username, String password, String email) {
        int userType = UserTypes.WORKER.getValue();
        return super.registerUser(fullName, username, password, email, userType);
    }
}
