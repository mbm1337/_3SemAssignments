package org.example.week05.hotel_exercise.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.javalin.http.Handler;
import org.example.week05.hotel_exercise.DAO.RoomDAO;


public class RoomController implements IController{
        private RoomDAO roomDAO;

        private final ObjectMapper objectMapper = new ObjectMapper();

        public RoomController(RoomDAO roomDAO){
            this.roomDAO = roomDAO;
        }

        @Override
        public Handler getAll() {
            throw new UnsupportedOperationException("Unimplemented method 'getAll'");
        }

        @Override
        public Handler getById() {

            throw new UnsupportedOperationException("Unimplemented method 'getById'");
        }

        @Override
        public Handler create() {
            throw new UnsupportedOperationException("Unimplemented method 'create'");
        }

        @Override
        public Handler delete() {
            throw new UnsupportedOperationException("Unimplemented method 'delete'");
        }

        @Override
        public Handler update() {
            throw new UnsupportedOperationException("Unimplemented method 'update'");
        }

    }

