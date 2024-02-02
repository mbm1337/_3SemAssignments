package org.example.week01.EightGenerics;

import java.io.*;
import java.lang.reflect.Type;

public class Generics  {

    interface DataStorage<T> {
        String store(T data); // return a unique ID for the stored data or the filename
        T retrieve(String source); // retrieve data from the specified source (like a file or database table or ID)
    }

    static class MemoryStorage<T> implements DataStorage<T> {
        private static int id;
        private T data;

        @Override
        public String store(T data) {
            this.data = data;
            return "id: " + this.id++;
        }

        @Override
        public T retrieve(String source) {
            return data;
        }
    }

    static class FileStorage<T> implements DataStorage<T> {
        private String filename;
        private T data;

        @Override
        public String store(T data) {
            Type typeOf = data.getClass();
            String fileName = typeOf.toString();
            String fileSuffix = (java.time.LocalDateTime.now()).format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
            fileName = fileName + fileSuffix + ".ser";
            try {
                File file = new File(fileName);
                FileOutputStream fos = new FileOutputStream(file);
                ObjectOutputStream out = new ObjectOutputStream(fos);
                out.writeObject(data);
                out.close();
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return fileName;
        }

        @Override
        public T retrieve(String fileName) {
            try{
                FileInputStream fis = new FileInputStream(fileName);
                ObjectInputStream in = new ObjectInputStream(fis);
                T obj = (T) in.readObject();
                in.close();
                fis.close();
                return obj;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    public static void main(String[] args) {
        DataStorage<String> memoryStorage = new MemoryStorage<>();
        memoryStorage.store("Hello, world!");
        String retrievedString = memoryStorage.retrieve("id: 0");
        System.out.println(retrievedString);


        DataStorage<Employee> fileStorage = new FileStorage<>();
        String filename = fileStorage.store(new Employee("John", 30));
        Employee retrievedInt = fileStorage.retrieve(filename);
        System.out.println(retrievedInt);



    }

}
