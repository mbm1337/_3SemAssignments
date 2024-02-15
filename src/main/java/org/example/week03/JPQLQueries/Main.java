package org.example.week03.JPQLQueries;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();
        var em = emf.createEntityManager();

        Query query = em.createQuery("INSERT INTO Employee (id, firstName, lastName, department, salary, email)\n" +
                "VALUES (1, 'John', 'Doe', 'HR', 50000, 'john.doe@example.com'),\n" +
                "       (2, 'Jane', 'Smith', 'Finance', 60000, 'jane.smith@example.com'),\n" +
                "       (3, 'Michael', 'Johnson', 'IT', 70000, 'michael.johnson@example.com'),\n" +
                "       (4, 'Emily', 'Williams', 'Sales', 55000, 'emily.williams@example.com'),\n" +
                "       (5, 'Christopher', 'Brown', 'Marketing', 65000, 'christopher.brown@example.com'),\n" +
                "       (6, 'Amanda', 'Jones', 'HR', 48000, 'amanda.jones@example.com'),\n" +
                "       (7, 'David', 'Miller', 'IT', 72000, 'david.miller@example.com'),\n" +
                "       (8, 'Sarah', 'Wilson', 'Finance', 62000, 'sarah.wilson@example.com'),\n" +
                "       (9, 'Matthew', 'Taylor', 'Sales', 58000, 'matthew.taylor@example.com'),\n" +
                "       (10, 'Jennifer', 'Anderson', 'Marketing', 67000, 'jennifer.anderson@example.com')");
        em.getTransaction().begin();
        query.executeUpdate();
        em.getTransaction().commit();
        em.close();

        Query query1 = em.createQuery("SELECT e FROM Employee e");
        List<Employee> allEmployees = query1.getResultList();

        Query query2 = em.createQuery("SELECT e FROM Employee e WHERE e.salary > 60000");
        List<Employee> employeesWithSalaryOver60k = query2.getResultList();

        Query query3 = em.createQuery("SELECT e FROM Employee e WHERE e.department = 'HR'");
        List<Employee> hrDepartmentEmployees = query3.getResultList();

        Query query4 = em.createQuery("SELECT e FROM Employee e WHERE e.firstName LIKE 'J%'");
        List<Employee> employeesWithFirstNameStartingWithJ = query4.getResultList();

        Query query5 = em.createQuery("UPDATE Employee e SET e.salary = e.salary + 10000 WHERE e.department = 'IT'");
        em.getTransaction().begin();
        query5.executeUpdate();
        em.getTransaction().commit();
        em.close();

        Query query6 = em.createQuery("UPDATE Employee e SET e.department = 'SALES' WHERE e.firstName = 'Jane'");
        em.getTransaction().begin();
        query6.executeUpdate();
        em.getTransaction().commit();
        em.close();

        Query query7 = em.createQuery("SELECT AVG(e.salary) FROM Employee e");
        int averageSalary = (int) query7.getSingleResult();

        Query query8 = em.createQuery("SELECT SUM(e.salary) FROM Employee e");
        int totalSalary = (int) query8.getSingleResult();



    }
}
