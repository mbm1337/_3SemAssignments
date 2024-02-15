package org.example.week03.ThePointExercise;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class PointDAOTest {
    static EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();
    static EntityManager em = emf.createEntityManager();


    @BeforeAll
    static void setUp() {

        PointDAO pointDAO = new PointDAO();
        em.getTransaction().begin();


    }

    @AfterAll
    static void tearDown() {
        em.close();
        emf.close();

    }


    @Test
    void findNumberOfPoints() {

    }

    @Test
    void findAverageX() {
    }

    @Test
    void getAllPoints() {
    }
}