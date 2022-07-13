package com.meshalkina.restaurant_competition.repository;

import com.meshalkina.restaurant_competition.model.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MealRepository extends JpaRepository<Meal, Long> {
    Optional<Meal> findById(Long id);
}