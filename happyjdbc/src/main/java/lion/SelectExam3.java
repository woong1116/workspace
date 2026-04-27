package lion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SelectExam3 {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/liondb";
        String user = "woong";
        String password = "lion1234";


        String sql = "SELECT id, name, email, password FROM member";

        try(
                Connection conn = DriverManager.getConnection(url,user,password);
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
        ){
            while (rs.next()){
                System.out.println(rs.getInt("id"));
                System.out.println(rs.getString("name"));
                System.out.println(rs.getString("email"));
                System.out.println(rs.getString("password"));
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}