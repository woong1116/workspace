package org.example.aopexam.beforeaop;

public class UserDao {
    LogBean logBean = new LogBean();
    TransactionBean transactionBean = new TransactionBean();
    public void addUser(){

//        로그를 위한 코드
//        System.out.println("로그를 남깁니다. ");
        logBean.logging();


//        트랜잭션 처리를 위한 코드
//        System.out.println("트랜잭션 코드 추가 ");
//        transactionBean.startTransaction();

        //보안관련 코드 추가!!
        System.out.println("보안관련 코드 추가!! ");

        System.out.println("user를 추가하기위한 코드1");
        System.out.println("user를 추가하기위한 코드2");
        System.out.println("user를 추가하기위한 코드3");
        System.out.println("user를 추가하기위한 코드4");
        System.out.println("user를 추가하기위한 코드5");


//        System.out.println("트랜잭션 코드 추가 ");
        transactionBean.endTransaction();

    }

    public void updateUser(){

//        logBean.logging();

        transactionBean.startTransaction();

        System.out.println("user를 수정하기위한 코드1");
        System.out.println("user를 수정하기위한 코드2");
        System.out.println("user를 수정하기위한 코드3");
        System.out.println("user를 수정하기위한 코드4");


        transactionBean.endTransaction();

    }
}
