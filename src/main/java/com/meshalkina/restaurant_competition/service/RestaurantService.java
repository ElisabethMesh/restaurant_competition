package com.meshalkina.restaurant_competition.service;

import com.meshalkina.restaurant_competition.model.Restaurant;
import com.meshalkina.restaurant_competition.model.User;
import com.meshalkina.restaurant_competition.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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
        return newRestaurant;
    }

    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    public Restaurant getByIdRestaurant(Long restaurant_id) {
        return restaurantRepository.findById(restaurant_id).orElse(null);
    }

    public Restaurant updateRestaurant(Restaurant restaurant) {
        Restaurant fromDB = restaurantRepository.findById(restaurant.getId()).orElse(null);

        restaurant.setCreated(fromDB.getCreated());
        restaurant.setUpdated(LocalDateTime.now());

        return restaurantRepository.save(restaurant);
    }

    public void deleteRestaurant(Long restaurant_id) {
        restaurantRepository.deleteById(restaurant_id);
    }
}
