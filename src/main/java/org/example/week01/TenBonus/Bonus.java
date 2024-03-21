package main.java.org.example.week01.TenBonus;

import java.util.List;
import java.util.stream.Collectors;

class Employee {
    private String name;
    private int age;
    private String department;
    private double salary;

    public Employee(String name, int age, String department, double salary) {
        this.name = name;
        this.age = age;
        this.department = department;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }


    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age + '\'' +
                ", department=" + department + '\'' +
                ", salary=" + salary +
                '}';
    }
}

interface EmployeeFilter {
    boolean filter(Employee employee);
}

public class Bonus {
    public static void main(String[] args) {
        List<Employee> employees = List.of(
                new Employee("Ava", 30, "Sales", 3000),
                new Employee("Jane", 25, "Marketing", 2500),
                new Employee("Jack", 40, "Sales", 3500),
                new Employee("Jill", 35, "Marketing", 4000),
                new Employee("John", 50, "Sales", 4500),
                new Employee("Joe", 45, "Marketing", 5000),
                new Employee("Kim", 30, "Sales", 5500)

        );

        System.out.println("Average age of employees: ");
        employees.stream()
                .mapToInt(Employee::getAge)
                .average()
                .ifPresent(System.out::println);

        System.out.println("Highest salary:");
        employees.stream()
                .max((e1, e2) -> (int) (e1.getSalary() - e2.getSalary()))
                .ifPresent(System.out::println);

        System.out.println("Calculate average salary of employees in each department:");
        employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getSalary)))
                .forEach((department, avgSalary) -> System.out.println(department + " : " + avgSalary));

        System.out.println("count of employees in each department:");
        employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()))
                .forEach((department, count) -> System.out.println(department + " has " + count + " employees"));

        System.out.println("Three oldest employees:");
        employees.stream()
                .sorted((e1, e2) -> e2.getAge() - e1.getAge())
                .limit(3)
                .forEach(System.out::println);

        System.out.println("Salery above 4000:");
        employees.stream()
                .filter(employee -> employee.getSalary() > 4000)
                .forEach(System.out::println);

        System.out.println("Sorted by age:");
        employees.stream()
                .sorted((e1, e2) -> e1.getAge() - e2.getAge())
                .forEach(System.out::println);

        System.out.println("Sorted by salary:");
        employees.stream()
                .sorted((e1, e2) -> (int) (e1.getSalary() - e2.getSalary()))
                .forEach(System.out::println);

        System.out.println("Sorted by name:");
        employees.stream()
                .sorted((e1, e2) -> e1.getName().compareTo(e2.getName()))
                .forEach(System.out::println);


        EmployeeFilter highSalaryFilter = employee -> employee.getSalary() > 4000;
        EmployeeFilter ageFilter = employee -> employee.getAge() > 40;
        EmployeeFilter salesDepartmentFilter = employee -> employee.getDepartment().equals("Sales");


        System.out.println("Employees with salary above 4000:");
        employees.stream()
                .filter(highSalaryFilter::filter)
                .forEach(System.out::println);

        System.out.println("Employees with age above 40:");
        employees.stream()
                .filter(ageFilter::filter)
                .forEach(System.out::println);

        System.out.println("Employees in sales department:");
        employees.stream()
                .filter(salesDepartmentFilter::filter)
                .forEach(System.out::println);







    }



}
