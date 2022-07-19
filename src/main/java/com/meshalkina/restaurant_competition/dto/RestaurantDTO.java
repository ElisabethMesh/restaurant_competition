package com.meshalkina.restaurant_competition.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.meshalkina.restaurant_competition.model.Restaurant;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RestaurantDTO {

    private Long id;
    private String name;
    private List<MealDTO> menu;
    private int rating;

    public static RestaurantDTO fromRestaurant(Restaurant restaurant) {
        RestaurantDTO restaurantDTO = new RestaurantDTO();
        restaurantDTO.setId(restaurant.getId());
        restaurantDTO.setName(restaurant.getName());

        List<MealDTO> menuDTO = restaurant.getMenu()
                .stream()
                .map(MealDTO::fromMeal)
                .collect(Collectors.toList());

        restaurantDTO.setMenu(menuDTO);

        if(restaurant.getVotes() != null) {
            restaurantDTO.setRating(restaurant.getVotes().size());
        } else {
            restaurantDTO.setRating(0);
        }

        return restaurantDTO;
    }
}
