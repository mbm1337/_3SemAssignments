package org.example.week01.FourTimeAPI;
import java.util.Date;
import java.util.Calendar;


public class Employee {
    private String name;
    private Date dateOfBirth;


    public Employee(String name, int age, Date dateOfBirth) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }

    public String getName() {
        return name;
    }



    public Date getDateOfBirth() {
        return dateOfBirth;
    }
}
