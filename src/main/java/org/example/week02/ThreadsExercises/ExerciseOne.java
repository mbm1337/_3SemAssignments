package org.example.week02.ThreadsExercises;


import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


class Task implements Callable<String>   {
    private char c;

    public Task(char c) {
        this.c = c;
    }

    public String call() throws Exception {
        return c + "" + c + "" + c;
    }

}

public class ExerciseOne {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(4);
        char c = 'A';
        for (int i = 0; i < 26; i++) {
            Future<String> future = executor.submit(new Task(c));
            try {
                System.out.println(future.get());
            } catch (Exception e) {
                e.printStackTrace();
            }
            c++;
        }
        executor.shutdown();

    }

}