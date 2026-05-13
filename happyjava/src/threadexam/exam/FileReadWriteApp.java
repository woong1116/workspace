package threadexam.exam;

public class FileReadWriteApp {

    static class FileReaderTask implements Runnable {

        @Override
        public void run() {

        }
    }

    static class FileWriterTask implements Runnable {

        @Override
        public void run() {
            
        }
    }

        public static void main(String[] args) {
        Thread readerThread = new Thread(new FileReaderTask());
        Thread writerThread = new Thread(new FileWriterTask());

        readerThread.start();
        writerThread.start();
    }
}
