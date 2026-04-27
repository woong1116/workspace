package lion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SelectExam2 {
    public static void main(String[] args) throws Exception{

        String url = "jdbc:mysql://localhost:3306/liondb";
        String user = "woong";
        String password = "lion1234";
        //접속, 쿼리생성, 쿼리 실행, 결과값꺼내기

        Connection conn = DriverManager.getConnection(url, user, password);
        String sql = "SELECT id, name, email, password FROM member";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            System.out.print(rs.getInt(1)+"\t");
            System.out.print(rs.getString(2)+"\t");
            System.out.print(rs.getString(3)+"\t");
            System.out.println(rs.getString(4));
        }


        rs.close();
        ps.close();
        conn.close();
    }
}