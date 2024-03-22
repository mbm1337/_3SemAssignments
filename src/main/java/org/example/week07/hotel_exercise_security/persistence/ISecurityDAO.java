package org.example.week07.hotel_exercise_security.persistence;


import org.example.week07.hotel_exercise_security.exceptions.EntityNotFoundException;

public interface ISecurityDAO {
    User createUser(String username, String password);
    Role createRole(String role);
    User addRoleToUser(String username, String role);

    User verifyUser(String username, String password) throws EntityNotFoundException;

}
