package com.meshalkina.restaurant_competition.service;

import com.meshalkina.restaurant_competition.model.Meal;
import com.meshalkina.restaurant_competition.model.Restaurant;
import com.meshalkina.restaurant_competition.repository.MealRepository;
import com.meshalkina.restaurant_competition.repository.RestaurantRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class MealService {

    private final MealRepository mealRepository;
    private final RestaurantRepository restaurantRepository;

    public MealService(MealRepository mealRepository, RestaurantRepository restaurantRepository) {
        this.mealRepository = mealRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public Meal createMeal(Meal meal, Long restaurant_id) {
        Restaurant restaurant = restaurantRepository.findById(restaurant_id).orElse(null);
        if (restaurant == null) {
            return null;
        }
        meal.setRestaurant(restaurant);

        LocalDateTime dateTime = LocalDateTime.now();
        meal.setCreated(dateTime);
        meal.setUpdated(dateTime);

        log.info("IN createMeal - added a meal with a name {} for the restaurant {}",
                meal.getName(), restaurant.getName());
        return mealRepository.save(meal);
    }

    public Meal getByIdMeal(Long meal_id) {
        Meal meal = mealRepository.findById(meal_id).orElse(null);

        log.info("IN getByIdMeal - found a meal with id {} and name {}", meal_id, meal.getName());
        return meal;
    }

    public Meal updateMeal(Meal meal) {
        Meal fromDB = mealRepository.findById(meal.getId()).orElse(null);

        meal.setCreated(fromDB.getCreated());
        meal.setUpdated(LocalDateTime.now());

        if (fromDB != null) {
            Restaurant currentRestaurant = restaurantRepository.findById(fromDB.getRestaurant().getId())
                    .orElse(null);
            meal.setRestaurant(currentRestaurant);
            return mealRepository.save(meal);
        }

        log.info("IN updateMeal - the meal with id {} has been changed", meal.getId());
        return meal;
    }

    public void deleteMeal(Long meal_id) {
        mealRepository.deleteById(meal_id);
        log.info("IN deleteMeal - the meal with id {} has been deleted", meal_id);
    }
}
