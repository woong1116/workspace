package threadexam.exam;

public class CounterApp {

    static class IncrementCounter implements Runnable {
        @Override
        public void run() {
            for (int i = 1; i <= 100; i++) {
                System.out.println("Increment: " + i);
                try {
                    Thread.sleep((int) (Math.random() * 11));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class DecrementCounter implements Runnable {
        @Override
        public void run() {
            for (int i = 100; i >= 1; i--) {
                System.out.println("Decrement: " + i);
                try {
                    Thread.sleep((int) (Math.random() * 11));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread incrementThread = new Thread(new IncrementCounter());
        Thread decrementThread = new Thread(new DecrementCounter());

        incrementThread.start();
        decrementThread.start();
    }
}