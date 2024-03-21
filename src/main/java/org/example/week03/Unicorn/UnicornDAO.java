package main.java.org.example.week03.Unicorn;

import jakarta.persistence.*;

import java.util.List;

public class UnicornDAO {
    private EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();

    public Unicorn save(Unicorn unicorn)
    {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(unicorn);
        em.getTransaction().commit();
        em.close();
        return unicorn;
    }

    public Unicorn update(Unicorn unicorn)
    {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Unicorn updatedUnicorn = em.merge(unicorn);
        em.getTransaction().commit();
        em.close();
        return updatedUnicorn;
    }

    public void delete(int id)
    {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Unicorn unicorn = findById(id);
        if (unicorn != null)
        {
            em.remove(unicorn);
        }
        em.getTransaction().commit();
        em.close();
    }

    public Unicorn findById(int id)
    {
        EntityManager em = emf.createEntityManager();
        Unicorn foundUnicorn = em.find(Unicorn.class, id);
        em.close();
        return foundUnicorn;
    }

    public List<Unicorn> findAll()
    {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Unicorn> query = em.createQuery("SELECT u FROM Unicorn u", Unicorn.class);
        List<Unicorn> unicorns = query.getResultList();
        return unicorns;
    }

    public void close()
    {
        emf.close();
    }
}