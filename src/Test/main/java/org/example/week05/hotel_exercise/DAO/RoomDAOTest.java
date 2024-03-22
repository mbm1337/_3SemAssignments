package main.java.org.example.week05.hotel_exercise.DAO;


import jakarta.persistence.EntityManagerFactory;

import org.example.week05.hotel_exercise.DAO.RoomDAO;
import org.example.week05.hotel_exercise.config.HibernateConfig;
import org.example.week05.hotel_exercise.ressources.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RoomDAOTest {

        RoomDAO roomDAO;
        EntityManagerFactory emf;

        @BeforeEach
        void setUp() {
            emf = HibernateConfig.getEntityManagerFactoryConfig("hoteldb", true);
            roomDAO = new RoomDAO(emf);
        }

        @Test
        void testAddAndRetrieveRoom() {
            Room room = new Room();
            // Set room properties here
            roomDAO.create(room);

            Room retrievedRoom = roomDAO.getById(room.getId());
            assertEquals(room, retrievedRoom);

        }
}


