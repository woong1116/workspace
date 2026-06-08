package org.example.aopexam.beforeaop;

public class TransactionBean {
    public void startTransaction(){
        System.out.println("트랜잭션처리를 시작합니다.");
    }

    public void endTransaction(){
        System.out.println("트랜잭션처리를 종료합니다.");
    }

}
