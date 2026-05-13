package threadexam;

public class DeadlockExample {
    private final Object lock1 = new Object();
    private final Object lock2 = new Object();

    public void method1() {
        synchronized(lock1) {
            System.out.println(Thread.currentThread().getName() + ": lock1 획득");

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}

            synchronized(lock2) {
                System.out.println(Thread.currentThread().getName() + ": lock2 획득");
            }
        }
    }

    public void method2() {
        synchronized(lock1) {
            System.out.println(Thread.currentThread().getName() + ": lock2 획득");

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}

            synchronized(lock2) {
                System.out.println(Thread.currentThread().getName() + ": lock1 획득");
            }
        }
    }

    public static void main(String[] args) {
        DeadlockExample deadlock = new DeadlockExample();

        Thread thread1 = new Thread(()->{
            deadlock.method1();
        });

        Thread thread2 = new Thread(()->{
            deadlock.method2();
        });

        thread1.start();
        thread2.start();

    }
}