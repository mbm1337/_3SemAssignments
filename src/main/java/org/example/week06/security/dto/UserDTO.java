package org.example.week06.security.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.example.week06.security.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@ToString
@NoArgsConstructor
public class UserDTO {

    private String username;
    private Set<String> roles;

    public UserDTO(String username, String[] roles) {
        this.username = username;
        this.roles = Set.of(roles);
    }

    public UserDTO(User user) {
        this.username = user.getUsername();
        this.roles = user.getRolesAsStrings();
    }



    public String getUsername() {
        return username;
    }

    public Set<String> getRoles() {
        return roles;
    }

}
