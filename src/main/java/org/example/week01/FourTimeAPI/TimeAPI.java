package org.example.week01.FourTimeAPI;

import java.util.*;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class TimeAPI {


    public static void main(String[] args) {

        List<String> employeeNames = Arrays.asList("John", "Jane", "Jack", "Joe", "Jill");
        Supplier<Employee> employeeSupplier = () -> {
            Random random = new Random();
            int randomindex = random.nextInt(employeeNames.size());
            int randomAge = random.nextInt(30)+14;
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, 2021-randomAge);
            calendar.set(Calendar.MONTH, random.nextInt(12));
            calendar.set(Calendar.DAY_OF_MONTH, random.nextInt(28));
            Date dateOfBirth = calendar.getTime();

            String name = employeeNames.get(randomindex);
            return new Employee(name, randomAge, dateOfBirth);
        };
        List<Employee> employees = createEmployees(20, employeeSupplier);

        calculateAgeOfEmployee(employees);
        calculateAvgAgeOfEmployees(employees);

        Predicate<Employee> birthdayInFebruary = (Employee e) ->
        {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(e.getDateOfBirth());
            int month = calendar.get(Calendar.MONTH);
            return month == 1;
        };
        List<Employee> birthdayEmployeesInFebruary = filterEmployees(employees, birthdayInFebruary);
        System.out.println("---------------------------Employees with birthday in February: ---------------------");
        birthdayEmployeesInFebruary.forEach(System.out::println);

        //Group employees by birth month and display the count of employees in each group.
        Map<Integer, Integer> employeesByBirthMonth = new HashMap<>();
        for (Employee employee : employees) {
            Date dateOfBirth = employee.getDateOfBirth();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dateOfBirth);
            int month = calendar.get(Calendar.MONTH);
            if (employeesByBirthMonth.containsKey(month)) {
                employeesByBirthMonth.put(month, employeesByBirthMonth.get(month) + 1);
            } else {
                employeesByBirthMonth.put(month, 1);
            }
        }
        System.out.println("---------------------------Employees by birth month: ---------------------");
        for (Map.Entry<Integer, Integer> entry : employeesByBirthMonth.entrySet()) {
            System.out.println("Month: " + (entry.getKey()+1) + " Count: " + entry.getValue());
        }








        Predicate<Employee> birthdayInCurrentMonth = (Employee e) -> {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(e.getDateOfBirth());
            int month = calendar.get(Calendar.MONTH);
            Calendar today = Calendar.getInstance();
            int currentMonth = today.get(Calendar.MONTH);
            return month == currentMonth;
        };
        List<Employee> birthdayEmployees = filterEmployees(employees, birthdayInCurrentMonth);
        System.out.println("---------------------------Employees with birthday in current month: ---------------------");
        birthdayEmployees.forEach(System.out::println);

    }

    private static List<Employee> filterEmployees(List<Employee> employees, Predicate<Employee> birthday) {
        List<Employee> result = new ArrayList<>();
        for (Employee e : employees) {
            if (birthday.test(e)) {
                result.add(e);
            }
        }
        return result;
    }

    public static List<Employee> createEmployees(int numEmployee, Supplier<Employee> supplier) {
        List<Employee> employees = new ArrayList<>();
        for (int i = 0; i < numEmployee; i++) {
            employees.add(supplier.get());
        }
        return employees;
    }

    public static void calculateAgeOfEmployee(List<Employee> employees){
        System.out.println("---------------------------Age of employees: ---------------------");
        for (Employee employee : employees) {
            Date dateOfBirth = employee.getDateOfBirth();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dateOfBirth);
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            Calendar today = Calendar.getInstance();
            int currentYear = today.get(Calendar.YEAR);
            int currentMonth = today.get(Calendar.MONTH);
            int currentDay = today.get(Calendar.DAY_OF_MONTH);

            int age = currentYear - year;
            if (currentMonth < month) {
                age--;
            } else if (currentMonth == month && currentDay < day) {
                age--;
            }
            System.out.println(employee.getName() + " is " + age + " years old.");
        }
    }

    public static void calculateAvgAgeOfEmployees(List<Employee> employees){
        System.out.println("---------------------------Average age of employees: ---------------------");
        int sum = 0;
        for (Employee employee : employees) {
            Date dateOfBirth = employee.getDateOfBirth();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dateOfBirth);
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            Calendar today = Calendar.getInstance();
            int currentYear = today.get(Calendar.YEAR);
            int currentMonth = today.get(Calendar.MONTH);
            int currentDay = today.get(Calendar.DAY_OF_MONTH);

            int age = currentYear - year;
            if (currentMonth < month) {
                age--;
            } else if (currentMonth == month && currentDay < day) {
                age--;
            }
            sum += age;
        }
        System.out.println("Average age of employees is " + sum/employees.size());
    }

}
