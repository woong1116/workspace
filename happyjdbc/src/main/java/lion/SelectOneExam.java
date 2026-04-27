package lion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SelectOneExam {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/liondb";
        String user = "lion";
        String password = "lion1234";

        String sql = "SELECT id, name, email,password FROM member WHERE id=?";


        try(Connection conn = DriverManager.getConnection(url,user,password);
            PreparedStatement ps = conn.prepareStatement(sql);
        ){
            ps.setInt(1,11);

            try(ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    System.out.println(rs.getInt("id"));
                    System.out.println(rs.getString("name"));
                    System.out.println(rs.getString("email"));
                    System.out.println(rs.getString("password"));
                }
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
