package com.meshalkina.restaurant_competition.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "restaurants")
@NoArgsConstructor
public class Restaurant extends BaseEntity {
    @Column(name = "name")
    private String name;

    @JsonManagedReference(value = "menu-restaurant")
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<Meal> menu;

    @JsonManagedReference(value = "restaurant-vote")
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<Vote> votes;

    //for the test
    public Restaurant(Long id, String name, List<Meal> menu) {
        this.id = id;
        this.name = name;
        this.menu = menu;
    }
}
