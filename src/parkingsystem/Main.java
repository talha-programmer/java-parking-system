/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parkingsystem;

import parkingsystem.BusinessLogic.User;
import parkingsystem.Database.Database;
import parkingsystem.Utility.PasswordAuthentication;

/**
 *
 * @author talha
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        User user = new User();

        System.out.println(user.registerUser("M Talha", "talha", "pass", "mail", 1));

    }
    
}
