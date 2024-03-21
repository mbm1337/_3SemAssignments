package main.java.org.example.week05.hotel_exercise.DAO;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import main.java.org.example.week05.hotel_exercise.ressources.Room;

import java.util.List;

public class RoomDAO extends DAO<Room>{


    public RoomDAO(EntityManagerFactory emf) {
        super(emf);
    }

    @Override
    public List<Room> getAll() {
        try(var em = emf.createEntityManager()){
            TypedQuery<Room> q = em.createNamedQuery("FROM room", Room.class);
            return q.getResultList();
        }
    }

    @Override
    public Room getById(int id) {
        try(var em = emf.createEntityManager()){
            TypedQuery<Room> q = em.createNamedQuery("FROM Room WHERE id = :id", Room.class);
            q.setParameter("id", id);
            return q.getSingleResult();
        }
    }

    @Override
    public Room update(Room room) {
        try(var em = emf.createEntityManager()){
            em.getTransaction().begin();
            em.merge(room);
            em.getTransaction().commit();
        }
        return room;
    }
}
