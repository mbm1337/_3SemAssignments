package main.java.org.example.week05.hotel_exercise.DAO;

import jakarta.persistence.EntityManagerFactory;

import java.util.HashMap;
import java.util.Map;

public abstract class DAO<T> implements IDAO<T>{
    protected EntityManagerFactory emf;
    protected Map<Integer,T> entityMap;

    public DAO(EntityManagerFactory emf) {
        this.emf = emf;
        entityMap = new HashMap<>();
    }

    @Override
    public T create(T t) {
        try(var em = emf.createEntityManager()){
            em.getTransaction().begin();
            em.persist(t);
            em.getTransaction().commit();
        }
        return t;
    }

    @Override
    public void delete(T t) {
        try(var em = emf.createEntityManager()){
            em.getTransaction().begin();
            em.remove(t);
            em.getTransaction().commit();
        }
    }
}
