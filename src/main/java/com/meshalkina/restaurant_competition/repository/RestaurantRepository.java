package com.meshalkina.restaurant_competition.repository;

import com.meshalkina.restaurant_competition.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    Optional<Restaurant> findById(Long id);
}
