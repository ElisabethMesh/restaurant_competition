package com.meshalkina.restaurant_competition.service;

import com.meshalkina.restaurant_competition.model.Restaurant;
import com.meshalkina.restaurant_competition.repository.RestaurantRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public Restaurant createRestaurant(Restaurant restaurant) {
        LocalDateTime dateTime = LocalDateTime.now();
        restaurant.setCreated(dateTime);
        restaurant.setUpdated(dateTime);

        Restaurant newRestaurant = restaurantRepository.save(restaurant);

        log.info("IN createRestaurant - created new restaurant with name {}", newRestaurant.getName());
        return newRestaurant;
    }

    public List<Restaurant> getAllRestaurants() {
        List<Restaurant> result = restaurantRepository.findAll();

        log.info("IN getAllRestaurants - {} restaurants found", result.size());
        return result;
    }

    public Restaurant getByIdRestaurant(Long restaurant_id) {
        Restaurant restaurant = restaurantRepository.findById(restaurant_id).orElse(null);

        log.info("IN getByIdRestaurant - found restaurant with id {} and name {}", restaurant_id, restaurant.getName());
        return restaurant;
    }

    public Restaurant updateRestaurant(Restaurant restaurant) {
        Restaurant fromDB = restaurantRepository.findById(restaurant.getId()).orElse(null);

        restaurant.setCreated(fromDB.getCreated());
        restaurant.setUpdated(LocalDateTime.now());

        log.info("IN updateRestaurant - the restaurant with id {} has been changed", restaurant.getId());
        return restaurantRepository.save(restaurant);
    }

    public void deleteRestaurant(Long restaurant_id) {
        restaurantRepository.deleteById(restaurant_id);
        log.info("IN deleteRestaurant - the restaurant with id {} has been deleted", restaurant_id);
    }
}
