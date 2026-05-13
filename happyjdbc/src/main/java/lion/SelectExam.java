package lion;

import java.sql.*;

public class SelectExam {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/liondb";
        String user = "woong";
        String password = "lion1234";

        //1. 접속
        //2. 쿼리문작성
        //3. 쿼리실행
        //4. 결과값을 얻어오기.

        //프로그래밍 순서!!
        // 1. 필요한 객체를 먼저 선언!!
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            //3. 접속
            conn = DriverManager.getConnection(url, user, password);
            //4. 쿼리작성
            String sql = "SELECT id, name, email, password FROM member";
            ps = conn.prepareStatement(sql);

            //5. 쿼리 실행
            rs = ps.executeQuery();

            while(rs.next()){
                System.out.println("test");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            //2. 선언한 객체는 모두 닫아준다!!  객체가 열린 반대 순서대로 닫아준다.
            if(rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if(ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if(conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}