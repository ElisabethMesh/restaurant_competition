package com.meshalkina.restaurant_competition.rest;

import com.meshalkina.restaurant_competition.dto.RestaurantDTO;
import com.meshalkina.restaurant_competition.model.Restaurant;
import com.meshalkina.restaurant_competition.service.RestaurantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantRestController {

    private final RestaurantService restaurantService;

    public RestaurantRestController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('users:write')")
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody Restaurant restaurant) {
        Restaurant newRestaurant = restaurantService.createRestaurant(restaurant);
        if (newRestaurant == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(newRestaurant, HttpStatus.OK);
    }

    @GetMapping()
    @PreAuthorize("hasAuthority('users:read')")
    public ResponseEntity<List<RestaurantDTO>> getAllRestaurants() {
        List<RestaurantDTO> allRestaurantsDTO = restaurantService.getAllRestaurants()
                .stream()
                .map(RestaurantDTO::fromRestaurant)
                .collect(Collectors.toList());
        return new ResponseEntity<>(allRestaurantsDTO, HttpStatus.OK);
    }

    @GetMapping("/{restaurant_id}")
    @PreAuthorize("hasAuthority('users:read')")
    public ResponseEntity<RestaurantDTO> getRestaurantById(@PathVariable Long restaurant_id) {
        Restaurant restaurant = restaurantService.getByIdRestaurant(restaurant_id);
        if (restaurant != null) {
            RestaurantDTO restaurantDTO = RestaurantDTO.fromRestaurant(restaurant);
            return new ResponseEntity<>(restaurantDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('users:write')")
    public ResponseEntity<Restaurant> updateRestaurant(@RequestBody Restaurant restaurant) {
        Restaurant updateRestaurant = restaurantService.updateRestaurant(restaurant);
        if (updateRestaurant == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(updateRestaurant, HttpStatus.OK);
    }

    @DeleteMapping("/{restaurant_id}")
    @PreAuthorize("hasAuthority('users:write')")
    public void deleteRestaurant(@PathVariable Long restaurant_id) {
        restaurantService.deleteRestaurant(restaurant_id);
    }

}
