package com.meshalkina.restaurant_competition.model;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    private Status status;

//    public User() {
//    }
//
//    public User(Long id, String username, String password, String firstname, String lastname,
//                Role role, Status status, LocalDateTime created, LocalDateTime updated) {
//        this.id = id;
//        this.username = username;
//        this.password = password;
//        this.firstname = firstname;
//        this.lastname = lastname;
//        this.role = role;
//        this.status = status;
//        this.created = created;
//        this.updated = updated;
//    }
}
