package main.java.org.example.week03.ThePointExercise;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();
        EntityManager em = emf.createEntityManager();
        PointDAO pointDAO = new PointDAO();

        // Store 1000 Point objects in the database:
        em.getTransaction().begin();
        for (int i = 0; i < 1000; i++) {
            Point p = new Point(i, i);
            em.persist(p);
        }
        em.getTransaction().commit();

        // Find the number of Point objects in the database:
        int count = pointDAO.findNumberOfPoints(em);
        System.out.println("Total Points: " + count );

        // Find the average X value:
        int avgX = pointDAO.findAverageX(em);
        System.out.println("Average X: " + avgX);

        // Retrieve all the Point objects from the database:
        List<Point> results = pointDAO.getAllPoints(em);
        for (Point p : results) {
            System.out.println(p);
        }

        // Close the database connection:
        em.close();
        emf.close();
    }


}

