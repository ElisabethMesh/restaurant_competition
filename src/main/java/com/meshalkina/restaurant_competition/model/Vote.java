package com.meshalkina.restaurant_competition.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "votes")
public class Vote extends BaseEntity {

    @JsonBackReference(value = "restaurant-vote")
    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @JsonBackReference(value = "user-vote")
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

}
