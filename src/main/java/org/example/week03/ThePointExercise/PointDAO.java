package org.example.week03.ThePointExercise;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class PointDAO {
    private EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();

    public Integer findNumberOfPoints(EntityManager em)
    {
        // Find the number of Point objects in the database:
        Query q1 = em.createQuery("SELECT COUNT(p) FROM Point p");;
        return (Integer) q1.getSingleResult();
    }

    public Integer findAverageX(EntityManager em)
    {
        // Find the average X value:
        Query q2 = em.createQuery("SELECT AVG(p.x) FROM Point p");
        return (Integer) q2.getSingleResult();
    }

    public List<Point> getAllPoints(EntityManager em)
    {
        TypedQuery<Point> query = em.createQuery("SELECT p FROM Point p", Point.class);
        List<Point> results = query.getResultList();
        return results;
    }



}
