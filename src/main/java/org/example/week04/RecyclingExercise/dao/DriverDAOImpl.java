package main.java.org.example.week04.RecyclingExercise.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import main.java.org.example.week04.RecyclingExercise.config.HibernateConfig;
import main.java.org.example.week04.RecyclingExercise.model.Driver;


import java.math.BigDecimal;
import java.util.List;

public class DriverDAOImpl implements IDriverDAO {

    private EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();


    @Override
    public String saveDriver(String name, String surname, BigDecimal salary) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(new Driver(name, surname, salary));
        em.getTransaction().commit();
        em.close();
        return "Driver saved successfully";
    }


    @Override
    public Driver getDriverById(String id) {
        EntityManager em = emf.createEntityManager();
        Driver driver = em.find(Driver.class, id);
        em.close();
        return driver;
    }

    @Override
    public Driver updateDriver(Driver driver) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(driver);
        em.getTransaction().commit();
        em.close();
        return driver;
    }

    @Override
    public void deleteDriver(String id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.remove(em.find(Driver.class, id));
        em.getTransaction().commit();
        em.close();

    }

    @Override
    public List<Driver> findAllDriversEmployedAtTheSameYear(String year) {
        EntityManager em = emf.createEntityManager();
        List<Driver> drivers = em.createQuery("SELECT d FROM Driver d WHERE YEAR(d.employmentDate) = :year", Driver.class).setParameter("year", year).getResultList();
        em.close();
        return drivers;

    }

    @Override
    public List<Driver> fetchAllDriversWithSalaryGreaterThan10000() {
        EntityManager em = emf.createEntityManager();
        List<Driver> drivers = em.createQuery("SELECT d FROM Driver d WHERE d.salary > 10000", Driver.class).getResultList();
        em.close();
        return drivers;

    }

    @Override
    public double fetchHighestSalary() {
        EntityManager em = emf.createEntityManager();
        double highestSalary = (double) em.createQuery("SELECT MAX(d.salary) FROM Driver d").getSingleResult();
        em.close();
        return highestSalary;
    }

    @Override
    public List<String> fetchFirstNameOfAllDrivers() {
        EntityManager em = emf.createEntityManager();
        List<String> firstNames = em.createQuery("SELECT d.name FROM Driver d", String.class).getResultList();
        em.close();
        return firstNames;
    }

    @Override
    public long calculateNumberOfDrivers() {
        EntityManager em = emf.createEntityManager();
        long numberOfDrivers = (long) em.createQuery("SELECT COUNT(d) FROM Driver d").getSingleResult();
        em.close();
        return numberOfDrivers;
    }

    @Override
    public Driver fetchDriverWithHighestSalary() {
        EntityManager em = emf.createEntityManager();
        Driver driver = (Driver) em.createQuery("SELECT d FROM Driver d WHERE d.salary = (SELECT MAX(d.salary) FROM Driver d)", Driver.class).getSingleResult();
        em.close();
        return driver;
    }
}
