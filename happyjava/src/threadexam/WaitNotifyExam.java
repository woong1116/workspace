package threadexam;

public class WaitNotifyExam {
    private static Object lock = new Object();
    private static boolean itemsAvailable = false;

    static class Producer extends Thread{
        @Override
        public void run() {
            System.out.println("생산자 시작!!");
            synchronized (lock){
                System.out.println("생산자가 item 생성중!!!");
                itemsAvailable = true;
                lock.notify(); //생산이 끝났음을 알림. (잠들어 있는 소비자를 깨움)
                System.out.println("생산자가 알림을 보냄!!");
            }
        }
    }

    static class Consumer extends Thread{
        @Override
        public void run() {
            System.out.println("소비자 소비 시작!!");
            synchronized (lock){
                while(!itemsAvailable){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

                System.out.println("아이템을 소비함!!!");
                itemsAvailable = false;
            }
        }
    }

    public static void main(String[] args) {
        Producer p = new Producer();
        Consumer c = new Consumer();

        c.start(); //소비자부터 실행했을 때!!!

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        p.start();
    }
}