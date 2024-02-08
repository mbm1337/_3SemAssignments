package org.example.week02.ThreadsExercises;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ExerciseOne {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(4);
        for (char c = 'A'; c <= 'Z'; c++) {
            char currentChar = c;
            executor.submit(() -> System.out.println(currentChar + "" + currentChar + "" + currentChar));
        }
        executor.shutdown();
    }

}