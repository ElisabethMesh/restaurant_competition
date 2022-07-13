package com.meshalkina.restaurant_competition.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.meshalkina.restaurant_competition.model.Meal;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MealDTO {

    private String name;
    private double price;

    public static MealDTO fromMeal(Meal meal) {
        MealDTO mealDTO = new MealDTO();
        mealDTO.setName(meal.getName());
        mealDTO.setPrice(meal.getPrice());

        return mealDTO;
    }
}
