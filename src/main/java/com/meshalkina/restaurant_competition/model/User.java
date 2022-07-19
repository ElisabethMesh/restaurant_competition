package com.meshalkina.restaurant_competition.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @JsonManagedReference(value = "user-vote")
    @OneToOne(mappedBy = "user", cascade = CascadeType.REMOVE)
    private Vote vote;

    //for the test
    public User(Long id, LocalDateTime created, LocalDateTime updated, String username, String password,
                String firstname, String lastname, Role role, Status status) {
        super(id, created, updated);
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.role = role;
        this.status = status;
    }
}
