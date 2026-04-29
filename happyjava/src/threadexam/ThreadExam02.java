package threadexam;
class MyRunnable implements Runnable{
    @Override
    public void run() {
        for(int i=0;i<=10;i++){
            System.out.println("안녕");
        }
    }
}
public class ThreadExam02 {
    public static void main(String[] args) {
        System.out.println("main 시작");

        MyRunnable myRunnable = new MyRunnable();
        Thread thread1 = new Thread(myRunnable);
        thread1.start();

        MyThread myThread = new MyThread("myThread");
        myThread.start();



        for (int i = 0; i < 10; i++) {
            System.out.println("main work!");
        }
    }
}