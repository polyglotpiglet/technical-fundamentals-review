package threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class Main {

    private static final AtomicInteger COUNTER = new AtomicInteger();
    private static final int THRESHOLD = 5;
    private static final AtomicBoolean RESULT_AVAILABLE = new AtomicBoolean(false);
    private static final AtomicInteger RESULT = new AtomicInteger();

    public static void main(String[] args) {

        ExecutorService es = Executors.newFixedThreadPool(6);
        es.submit(new Consumer());
        IntStream.range(0, THRESHOLD)
                .mapToObj(index -> new Publisher())
                .forEach(es::submit);

        es.shutdown();
    }

    private static class Publisher implements Runnable {

        @Override
        public void run() {
            int currentValue = COUNTER.incrementAndGet();
            while (!RESULT_AVAILABLE.get()) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("The result is " + RESULT.get());
        }
    }

    private static class Consumer implements Runnable {

        @Override
        public void run() {
            while (COUNTER.get() < THRESHOLD) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("I am seeting the result!");
            RESULT.set(100);
            RESULT_AVAILABLE.set(true);
        }
    }
}