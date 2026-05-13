package threadexam;
class Counter{
    private int count = 0;

    public /*synchronized*/ void increment(){
        synchronized (this) {
            count++;
        }
    }
    public synchronized int getCount(){
        return count;
    }
}
public class ThreadExam03 {
    public static void main(String[] args){
        Counter counter = new Counter();

        Thread[] threads = new Thread[10];
        for(int i=0;i<10;i++){
            threads[i] = new Thread(new Runnable(){
                public void run(){
                    for(int i=0;i<1000;i++){
                        counter.increment();  //
//                        System.out.println(counter.getCount());
                    }
//                    System.out.println("thread 종료!!");
                }
            });
            threads[i].start();
        }

        for(Thread t:threads){
            try {
                t.join();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }


//        메인 스레드가 너무 빨리 끝나서...
        System.out.println("main count::::"+counter.getCount());
    }
}