package org.example.week07.hotel_exercise_security.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.week07.hotel_exercise_security.persistence.User;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {
    private String username;
    private String password;
    private Set<String> roles;

    public UserDTO(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.roles = user.getRolesAsStrings();

    }


    public UserDTO(String username, Set<String> rolesSet) {
        this.username = username;
        this.roles = rolesSet;
    }
}