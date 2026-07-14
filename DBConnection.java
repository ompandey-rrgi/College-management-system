package collegemanagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/college_management";
    private static final String USER = "root";
    private static final String PASS = ""; // put your MySQL password here

    public static Connection getConnection(String url, String user, String pass) throws SQLException {
        return DriverManager.getConnection(url, user, pass);
    }

    public static Connection getConnection() throws SQLException {
        return getConnection(URL, USER, PASS);
    }
}
