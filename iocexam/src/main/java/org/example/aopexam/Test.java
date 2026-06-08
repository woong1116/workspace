package org.example.aopexam;

import java.sql.Connection;

public class Test {

    public void joinUser() throws Exception{
        Connection conn = null;

        conn.setAutoCommit(false);

        try {
//        비지니스 코드가 실행!!!


//            DAO 가 실행된다면..  dao.insertUser(conn, );

        }catch (Exception e){
//            실행중에 오류가 발생되었다면...
            conn.rollback();
        }

//        예외가 발생하지 않고, 이 메서드가 끝까지 잘 실행되었다면
        conn.commit();;
    }
//    이런 코드를 직접 사용하셨나요??


    public void updateUser() throws Exception{
        Connection conn = null;

        conn.setAutoCommit(false);

        try {
//        비지니스 코드가 실행!!!
//            수정하는 비지니스 코드 실행

//            DAO 가 실행된다면..  dao.updatetUser(conn, );

        }catch (Exception e){
//            실행중에 오류가 발생되었다면...
            conn.rollback();
        }

//        예외가 발생하지 않고, 이 메서드가 끝까지 잘 실행되었다면
        conn.commit();;
    }

    public void updateUser2(){
//        비지니스 코드가 실행!!!
//            수정하는 비지니스 코드 실행

//            DAO 가 실행된다면..  dao.updatetUser(conn, );

    }


}
