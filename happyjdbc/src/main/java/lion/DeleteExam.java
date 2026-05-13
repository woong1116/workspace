package lion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DeleteExam {
    public static void main(String[] args) throws Exception {
        String url = "jdbc:mysql://localhost:3306/liondb";
        String user = "woong";
        String password = "lion1234";

        Connection conn = DriverManager.getConnection(url, user, password);

        String sql = "DELETE FROM member WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setInt(1, 3);

        int resultCount = ps.executeUpdate();

        System.out.println(resultCount + "건 삭제");

        conn.close();
    }
}