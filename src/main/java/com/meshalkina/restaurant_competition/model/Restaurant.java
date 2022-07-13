package com.meshalkina.restaurant_competition.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "restaurants")
@NoArgsConstructor
public class Restaurant extends BaseEntity {
    @Column(name = "name")
    private String name;

    @JsonManagedReference
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<Meal> menu;

}
