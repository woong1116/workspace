package lion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class InsertExam {
    //접근제한자 리턴타입 메소드명(매개변수들..) {}
    public boolean insertMember(String name, String email, String pw) throws Exception{
        boolean resultFlag = false;
        //1. 접속
        Connection conn = null;
        String url = "jdbc:mysql://localhost:3306/liondb";
        String user = "woong";
        String password = "lion1234";
        conn = DriverManager.getConnection(url,user,password);

//        conn.setAutoCommit(false);   -- 기본 설정은 true


        //2. 쿼리문 작성 = 쿼리문이 추상화된 객체 Statement
//        String sql = "insert into member(name,email, password) values('kkk','kang@gmail.com','1111')";
        String sql = "insert into member(name,email, password) values(?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,name);
        ps.setString(2,email);
        ps.setString(3,pw);

        //3. 쿼리 실행
        int resultCount = ps.executeUpdate();
        if(resultCount==1)
            return true;
        return  false;
    }



    public static void main(String[] args) {
        InsertExam exam = new InsertExam();
        try {
            boolean result = exam.insertMember("강경미", "ccc@gmail.com", "1234");

            if (result)
                System.out.println("입력성공@@");
            else
                System.out.println("입력실패");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


//        //1. 접속
//        Connection conn = null;
//        String url = "jdbc:mysql://localhost:3306/liondb";
//        String user = "lion";
//        String password = "lion1234";
//        conn = DriverManager.getConnection(url,user,password);
//
////        conn.setAutoCommit(false);   -- 기본 설정은 true
//
//
//        //2. 쿼리문 작성 = 쿼리문이 추상화된 객체 Statement
////        String sql = "insert into member(name,email, password) values('kkk','kang@gmail.com','1111')";
//        String sql = "insert into member(name,email, password) values(?,?,?)";
//        PreparedStatement ps = conn.prepareStatement(sql);
//        ps.setString(1,"kkk");
//        ps.setString(2,"kkk@gmail.com");
//        ps.setString(3,"1234");
//
//        //3. 쿼리 실행
//        int resultCount = ps.executeUpdate();
//
////        conn.commit();
//        System.out.println(resultCount + "건 입력됨!!");
    }
}