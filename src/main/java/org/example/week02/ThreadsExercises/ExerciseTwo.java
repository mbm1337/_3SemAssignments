package org.example.week02.ThreadsExercises;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExerciseTwo {
    public static void main(String[] args) {
        // Create a new Counter object
        Counter counter = new Counter();
        ExecutorService executor = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 50; i++) {
            executor.submit(new Runnable() {
                @Override
                public void run() {
                    counter.increment(1);
                }
            });
            System.out.println("Current count: " + counter.getCount());
        }
        executor.shutdown();
    }
    private static class Counter {
        private int count = 0;

        // Method to increment the count, synchronized to ensure thread safety
        public synchronized void increment(int value) {
            this.count += value;
        }

        // Method to retrieve the current count value
        public int getCount() {
            return count;
        }
    }
}
