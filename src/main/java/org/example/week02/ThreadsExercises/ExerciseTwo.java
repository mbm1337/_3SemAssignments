package main.java.org.example.week02.ThreadsExercises;

import java.util.concurrent.*;

public class ExerciseTwo {


    public static void main(String[] args) {
        // Create a new Counter object
        Counter counter = new Counter();
        ExecutorService executor = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 50; i++) {

            Future future = executor.submit(counter);
            try {
                System.out.println("Current count: " + future.get());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
        executor.shutdown();
    }
    private static class Counter implements Callable<Integer> {
        private int count = 0;

        // Method to increment the count, synchronized to ensure thread safety
        public synchronized void increment(int value) {
            this.count += value;
        }

        // Method to retrieve the current count value
        public int getCount() {
            return count;
        }

        @Override
        public Integer call() throws Exception {
            increment(1);
            return getCount();
        }
    }
}
