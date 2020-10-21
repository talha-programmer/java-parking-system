package parkingsystem.Database;

import parkingsystem.Utility.DisplayMessage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private final String HOST = "localhost";
    private final String DBNAME = "parking_system";
    private final String USERNAME = "root";
    private final String PASSWORD = "";

    /*private final String HOST = "ziailighting.com";
    private final String DBNAME = "ziailigh_parking_system";
    private final String USERNAME = "ziailigh_admin";
    private final String PASSWORD = "DB.password789";*/
    protected Connection conn = null;

    public Database() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url1 = "jdbc:mysql://" + HOST + "/" + DBNAME;
            conn = DriverManager.getConnection(url1, USERNAME, PASSWORD);
            if (conn != null) {
                System.out.println("Connected to the database test1");
            }
        } catch (SQLException | ClassNotFoundException ex) {
            String message = "Error occurred while connecting to database!\n";
            message += ex.getMessage();
            DisplayMessage.displayError(message);
        }
    }
}
