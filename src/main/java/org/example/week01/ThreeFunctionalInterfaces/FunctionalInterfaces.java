package main.java.org.example.week01.ThreeFunctionalInterfaces;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.function.Function;

public class FunctionalInterfaces {

        public static void main(String[] args) {
            int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

            Predicate<Integer> divisibleByThree = (Integer a) -> a % 3 == 0;

            for (int i : filter(arr, divisibleByThree)) {
                if (i != 0) {
                    System.out.println(i);
                }
            }


            List<String> employeeNames = Arrays.asList("John", "Jane", "Jack", "Joe", "Jill");
            Supplier<Employee> employeeSupplier = () -> {
                Random random = new Random();
                int randomindex = random.nextInt(employeeNames.size());
                int randomAge = random.nextInt(30)+14;
                String name = employeeNames.get(randomindex);
                return new Employee(name, randomAge);
            };
            List<Employee> employees = createEmployees(employeeNames.size(), employeeSupplier);

            Consumer<Employee> printEmployee = (Employee e) -> System.out.println(e);
            employees.forEach(printEmployee);

            Function<Employee, String> getEmployeeName = (Employee e) -> e.getName();
            List<String> employeeNamesConvert = convertEmployeeList(employees, getEmployeeName);
            employeeNamesConvert.forEach(System.out::println);

            Predicate<Employee> isAdult = (Employee e) -> e.getAge() >= 18;
            List<Employee> adults = filterEmployees(employees, isAdult);
            adults.forEach(System.out::println);

        }

    private static List<Employee> filterEmployees(List<Employee> employees, Predicate<Employee> filter) {
        List<Employee> result = new ArrayList<>();
        for (Employee e : employees) {
            if (filter.test(e)) {
                result.add(e);
            }
        }
        return result;

    }

    public static List<String> convertEmployeeList(List<Employee> employees, Function<Employee, String> op) {
        List<String> result = new ArrayList<>();
        for (Employee e : employees) {
            result.add(op.apply(e));
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

        public static int[] filter(int[] arr, Predicate<Integer> op) {
            int[] result = new int[arr.length];
            for (int i = 0; i < arr.length; i++) {
                if (op.test(arr[i])) {
                    result[i] = arr[i];
                } else {
                    result[i] = 0;

                }
            }
            return result;
        }
}
