package lion;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {
    public static Connection getConnection() throws Exception{
        String url = "jdbc:mysql://localhost:3306/liondb";
        String user = "woong";
        String password = "lion1234";

        return DriverManager.getConnection(url,user,password);
    }

    public static Connection getConnection(String user, String password) throws Exception{
        String url = "jdbc:mysql://localhost:3306/liondb";
        return DriverManager.getConnection(url,user,password);
    }

    public static Connection getConnection(String url, String user, String password) throws Exception{
        return DriverManager.getConnection(url,user,password);
    }
}
