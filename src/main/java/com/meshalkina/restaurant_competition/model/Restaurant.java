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

    @JsonManagedReference(value = "menu-restaurant")
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<Meal> menu;

    @JsonManagedReference(value = "restaurant-vote")
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<Vote> votes;

//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public List<Meal> getMenu() {
//        return menu;
//    }
//
//    public void setMenu(List<Meal> menu) {
//        this.menu = menu;
//    }
//
//    public List<Vote> getVotes() {
//        return votes;
//    }
//
//    public void setVotes(List<Vote> votes) {
//        this.votes = votes;
//    }
//
//    public Restaurant() {
//    }
//
//    public Restaurant(Long id, String name, List<Meal> menu, List<Vote> votes) {
//        this.id = id;
//        this.name = name;
//        this.menu = menu;
//        this.votes = votes;
//    }

}
