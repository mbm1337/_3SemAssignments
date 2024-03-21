package main.java.org.example.week02.ThreadsExercises;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExerciseFour {
    public static void main(String[] args) {
        int numCores = Runtime.getRuntime().availableProcessors();
        ExecutorService workingJack = Executors.newFixedThreadPool(numCores);
            System.out.println("Main starts");
            IntegerList integerList = new IntegerList();
            for (int count = 0; count < 1000; count++) {
                workingJack.submit(new TaskToAddCount(integerList, count));
            }
            System.out.println("Main is done");
            workingJack.shutdown();

        }

        private static class IntegerList {
            //If you use copyOnWriteArrayList,it is possible to not have to use synchronized
            //This is because the list is copied every time it is modified, so the original list is never modified
            private static List<Integer> list = new CopyOnWriteArrayList<>();
            public void addCount(int count) {
                list.add(count);
                System.out.println("Task: " + count + ": List size = " + list.size());
            }
        }
        private static class TaskToAddCount implements Runnable {
            // Gets a reference to the shared list and the count to add
            private IntegerList integerList;
            private int count;

            TaskToAddCount(IntegerList integerList, int count) {
                this.integerList = integerList;
                this.count = count;
            }

            @Override
            public void run() {
                try {
                    Thread.sleep((int) Math.random()*800+200);
                    integerList.addCount(count);
                } catch (InterruptedException ex) {
                    System.out.println("Thread was interrupted");
                }
            }
        }

    }


