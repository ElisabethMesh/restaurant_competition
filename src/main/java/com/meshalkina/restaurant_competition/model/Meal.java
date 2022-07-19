package com.meshalkina.restaurant_competition.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "meals")
@NoArgsConstructor
public class Meal extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private double price;

    @JsonBackReference(value = "menu-restaurant")
    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    //for the test

    public Meal(Long id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}
