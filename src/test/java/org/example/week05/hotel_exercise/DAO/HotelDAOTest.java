package org.example.week05.hotel_exercise.DAO;

import jakarta.persistence.EntityManagerFactory;
import org.example.week05.hotel_exercise.config.HibernateConfig;
import org.example.week05.hotel_exercise.ressources.Hotel;
import org.example.week05.hotel_exercise.ressources.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HotelDAOTest {

    EntityManagerFactory emf;
    HotelDAO hotelDAO;

    @BeforeEach
    void setUp() {
        emf = HibernateConfig.getEntityManagerFactoryConfig("hoteldb", true);
        hotelDAO = new HotelDAO(emf);
    }

    @Test
    void testAddAndRetrieveHotel() {
        Hotel hotel = new Hotel();
        // Set hotel properties here
        hotelDAO.create(hotel);

        Hotel retrievedHotel = hotelDAO.getById(hotel.getId());
        assertEquals(hotel, retrievedHotel);
    }

    @Test
    void testUpdateHotel() {
        Hotel hotel = new Hotel();
        hotel.setName("Hotel 1");
        hotel.setAddress("Street 1");
        hotelDAO.create(hotel);

        hotel.setName("Hotel 2");
        hotel.setAddress("Street 2");

        hotelDAO.update(hotel);
        Hotel retrievedHotel = hotelDAO.getById(hotel.getId());
        assertEquals(hotel, retrievedHotel);


    }

    @Test
    void testDeleteHotel() {
        Hotel hotel = new Hotel();
        hotel.setName("Hotel 1");
        hotel.setAddress("Street 1");
        hotelDAO.create(hotel);


        hotelDAO.delete(hotel);
        assertNull(hotelDAO.getById(hotel.getId()));


    }

    @Test
    void testGetAllHotels() {
        Hotel hotel1 = new Hotel();
        hotel1.setName("Hotel 1");
        hotel1.setAddress("Street 1");
        hotelDAO.create(hotel1);

        Hotel hotel2 = new Hotel();
        hotel2.setName("Hotel 2");
        hotel2.setAddress("Street 2");
        hotelDAO.create(hotel2);

        assertEquals(2, hotelDAO.getAll().size());
    }

    @Test
    void testGetHotelRooms() {
        Hotel hotel = new Hotel();
        hotel.setName("Hotel 1");
        hotel.setAddress("Street 1");
        hotelDAO.create(hotel);


        Room room1 = new Room();
        room1.setRoomNumber(101);
        room1.setPrice(100.0f);
        hotel.addRoom(room1);
        Room room2 = new Room();
        room2.setRoomNumber(102);
        room2.setPrice(200.0f);
        hotel.addRoom(room2);
        hotelDAO.update(hotel);

        assertEquals(2, hotelDAO.getHotelRooms(hotel.getId()).size());

    }


    @Test
    void testGetHotelRoomsEmpty() {
        Hotel hotel = new Hotel();
        hotel.setName("Hotel 1");
        hotel.setAddress("Street 1");
        hotelDAO.create(hotel);

        assertEquals(0, hotelDAO.getHotelRooms(hotel.getId()).size());

    }

    @Test
    void testGetHotelRoomsNoHotel() {
        assertEquals(0, hotelDAO.getHotelRooms(1).size());

    }





}