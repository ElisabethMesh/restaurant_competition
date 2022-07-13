package com.meshalkina.restaurant_competition.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.meshalkina.restaurant_competition.model.Role;
import com.meshalkina.restaurant_competition.model.User;
import com.meshalkina.restaurant_competition.util.DateTimeFormatterUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;
    private String firstname;
    private String lastname;
    private Role role;
    private String created;
    private String updated;


    public static UserDTO fromUser(User user) {
        UserDTO userDTO = new UserDTO();

        userDTO.setId(user.getId());
        userDTO.setFirstname(user.getFirstname());
        userDTO.setLastname(user.getLastname());
        userDTO.setRole(user.getRole());
        userDTO.setCreated(DateTimeFormatterUtil.format(user.getCreated()));
        userDTO.setUpdated(DateTimeFormatterUtil.format(user.getUpdated()));

        return userDTO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) { this.created = created; }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }
}
