package main.java.org.example.week04.DolphinExercise;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;


public class DolphinDAO<T> {
    private EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();

    public void save(T t) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(t);
        em.getTransaction().commit();
        em.close();

    }

    public T findById(int id, Class<T> t) {
        EntityManager em = emf.createEntityManager();
        T foundT = em.find(t, id);
        em.close();
        return foundT;
    }

    public void delete(T t) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.remove(t);
        em.getTransaction().commit();
        em.close();
    }

    public void update(T t) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(t);
        em.getTransaction().commit();
        em.close();
    }

    public void getAllPayments(Person p) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.createQuery("SELECT f FROM Fee f WHERE f.person = :person", Fee.class).setParameter("person", p).getResultList();
        em.getTransaction().commit();
        em.close();
    }

    public void getAllNotesFromPerson(Person p) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.createQuery("SELECT n FROM Note n WHERE n.person = :person", Note.class).setParameter("person", p).getResultList();
        em.getTransaction().commit();
        em.close();
    }

    public void getAllNotesPlusNameAndAgeFromPerson(Person p) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.createQuery("SELECT n, p.name, pD.age " +
                "FROM Note n " +
                "JOIN n.person p " +
                "JOIN p.personDetail pD " +
                "WHERE n.person = :person", Note.class).setParameter("person", p).getResultList();
        em.getTransaction().commit();
        em.close();
    }



}
