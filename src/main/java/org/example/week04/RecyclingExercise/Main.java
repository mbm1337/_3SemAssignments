package org.example.week04.RecyclingExercise;

import org.example.week04.RecyclingExercise.config.HibernateConfig;
import org.example.week04.RecyclingExercise.dao.*;
import org.example.week04.RecyclingExercise.model.*;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.math.BigDecimal;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();
        EntityManager em = emf.createEntityManager();

        DriverDAOImpl driverDAO = new DriverDAOImpl();

        Driver driver = driverDAO.getDriverById("200821-FR-892L");
        driver.setSalary(new BigDecimal(30000));
        driverDAO.updateDriver(driver);

        List<Driver> drivers = driverDAO.findAllDriversEmployedAtTheSameYear("2021");
        drivers.forEach(System.out::println);






        WasteTruckDAOImpl wasteTruckDAO = new WasteTruckDAOImpl();










    }
}
