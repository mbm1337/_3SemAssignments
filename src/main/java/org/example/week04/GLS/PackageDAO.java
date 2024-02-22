package org.example.week04.GLS;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;


import java.util.List;

public class PackageDAO {
    private EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();



    public void save(org.example.week04.GLS.Package aPackage) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(aPackage);
        em.getTransaction().commit();
        em.close();
    }

    //TODO: FIX!
    public org.example.week04.GLS.Package findByTrackingNumber(String trackingNumber) {
        EntityManager em = emf.createEntityManager();
        org.example.week04.GLS.Package foundPackage = em.find(org.example.week04.GLS.Package.class, trackingNumber);
        em.close();
        return foundPackage;
    }

    public void update(org.example.week04.GLS.Package aPackage) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(aPackage);
        em.getTransaction().commit();
        em.close();
    }

    public void delete(String trackingNumber) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        org.example.week04.GLS.Package aPackage = findByTrackingNumber(trackingNumber);
        if (aPackage != null) {
            em.remove(aPackage);
        }
        em.getTransaction().commit();
        em.close();
    }


    public List<org.example.week04.GLS.Package> findAll() {
        EntityManager em = emf.createEntityManager();
        List<org.example.week04.GLS.Package> packages = em.createQuery("SELECT p FROM Package p", org.example.week04.GLS.Package.class).getResultList();
        em.close();
        return packages;
    }
}
