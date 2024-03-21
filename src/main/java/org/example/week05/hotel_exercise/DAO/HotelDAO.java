package main.java.org.example.week05.hotel_exercise.DAO;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import main.java.org.example.week05.hotel_exercise.ressources.Hotel;

import java.util.Collection;
import java.util.List;

public class HotelDAO extends DAO<Hotel> {

    public HotelDAO(EntityManagerFactory emf) {
        super(emf);
    }

    @Override
    public List<Hotel> getAll() {
        try(var em = emf.createEntityManager()){
            TypedQuery<Hotel> q = em.createQuery("FROM Hotel h", Hotel.class);
            List<Hotel> hotels = q.getResultList();
            // hotels.forEach(h -> {
            //     TypedQuery<Room> roomQuery = em.createQuery("SELECT r From Hotel h JOIN h.rooms r WHERE h.id = :id", Room.class);
            //     roomQuery.setParameter("id", h.getId());
            //     roomQuery.getResultList().forEach(r -> h.addRoom(r));
            // });

            return hotels;
        }
    }

    @Override
    public Hotel getById(int id) {
        try(var em = emf.createEntityManager()){
            TypedQuery<Hotel> q = em.createQuery("FROM Hotel h WHERE h.id = :id", Hotel.class);
            q.setParameter("id", id);
            return q.getSingleResult();
        }
    }

    @Override
    public Hotel update(Hotel hotel) {
        try(var em = emf.createEntityManager()){
            em.getTransaction().begin();
            em.merge(hotel);
            em.getTransaction().commit();
        }
        return hotel;
    }

    public Collection<Object> getHotelRooms(int id) {
        try(var em = emf.createEntityManager()){
            TypedQuery<Object> q = em.createQuery("SELECT r From Hotel h JOIN h.rooms r WHERE h.id = :id", Object.class);
            q.setParameter("id", id);
            return q.getResultList();
        }
    }
}