package org.example.week02.JsonDTOexercise;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import lombok.*;




public class JsonDTOexercise {

    /*
    What does JSON stand for?
         - JavaScript Object Notation

    What is the difference between JSON and XML?
        - JSON is a lightweight data-interchange format, while XML is a markup language.

    For what is JSON generally used for?
        - JSON is generally used for transmitting data between a server and web application.

    Write down the 6 data types in JSON.
        - string, number, object, array, boolean, null

    Write down the 4 JSON syntax rules.
        - Data is in name/value pairs
        - Data is separated by commas
        - Curly braces hold objects
        - Square brackets hold arrays

     */


    Gson gson = new GsonBuilder().setPrettyPrinting().create();


    public static void main(String[] args) {
        JsonDTOexercise j = new JsonDTOexercise();
        String path = "src/main/java/org/example/week02/JsonDTOexercise/account.json";
        ResultDTO[] resultDTO =  j.getResultDTO(path);
        Arrays.stream(resultDTO).forEach(System.out::println);
    }

    public ResultDTO[] getResultDTO(String path) {
        JsonReader reader = null;
        try {
            reader = new JsonReader(new FileReader(path));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return gson.fromJson(reader, ResultDTO[].class);
    }

    public ResultDTO accountToDTO(String firstName, String lastName, String birthDate, Address address,  Account account) {
         return new ResultDTO(firstName, lastName, birthDate, address, account);
    }

    public ResultDTO[] accountArrToDTOArr(Object[] accounts) {
        return Arrays.stream(accounts).map(account -> {
            return gson.fromJson(gson.toJson(account), ResultDTO.class);
        }).toArray(ResultDTO[]::new);

    }



    @Getter
    @Setter
    @ToString
    class Address{
        private String street;
        private String city;
        private int zip;
    }

    @Getter
    @Setter
    @ToString
    class Account{
        private String id;
        private String balance;
        private boolean isActive;
    }

    @Getter
    @Setter
    @ToString
    class ResultDTO{
        String firstName;
        String lastName;
        String birthDate;
        Address address;
        Account account;

        public ResultDTO(String firstName, String lastName, String birthDate, Address address, Account account) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.birthDate = birthDate;
            this.address = address;
            this.account = account;
        }
    }

}
