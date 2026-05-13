package threadexam;
class SumThread extends Thread{
    @Override
    public void run() {
        //복잡한 일을 하는 스레드
        try{
            sleep(3000);
        }catch(InterruptedException e){
            System.out.println(e.getMessage());
        }
        System.out.println("계산 종료!!");
    }
}
public class JoinExam {
    public static void main(String[] args) {
        System.out.println("main 시작!!");

        SumThread t1 = new SumThread();
        t1.start();

        System.out.println("sumThread 가 계산 하는 동안에 main 할일 실행!!!");

        try {
            t1.join(); //sumThread가 끝날때까지 기다려!!!
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("sumThread 가 계산한 결과 값을 이용해서 뭔가 일을 한다..");
    }
}