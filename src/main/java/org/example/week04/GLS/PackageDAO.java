package main.java.org.example.week04.GLS;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;


import java.util.List;

public class PackageDAO {
    private EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();



    public void save(Package aPackage) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(aPackage);
        em.getTransaction().commit();
        em.close();
    }

    //TODO: FIX!
    public Package findByTrackingNumber(String trackingNumber) {
        EntityManager em = emf.createEntityManager();
        Package foundPackage = em.find(Package.class, trackingNumber);
        em.close();
        return foundPackage;
    }

    public void update(Package aPackage) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(aPackage);
        em.getTransaction().commit();
        em.close();
    }

    public void delete(String trackingNumber) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Package aPackage = findByTrackingNumber(trackingNumber);
        if (aPackage != null) {
            em.remove(aPackage);
        }
        em.getTransaction().commit();
        em.close();
    }


    public List<Package> findAll() {
        EntityManager em = emf.createEntityManager();
        List<Package> packages = em.createQuery("SELECT p FROM Package p", Package.class).getResultList();
        em.close();
        return packages;
    }
}
