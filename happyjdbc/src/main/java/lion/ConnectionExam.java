package lion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLOutput;

public class ConnectionExam {
    public static void main(String[] args) throws Exception {
        Connection conn = null;

        String url = "jdbc:mysql://localhost:3306/liondb";
        String user = "woong";
        String password = "lion1234";

        conn = DriverManager.getConnection(url, user, password);

        if (conn != null)
            System.out.println("^^");
        else
            System.out.println("-_-;;");

        conn.close();
    }
}
