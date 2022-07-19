package com.meshalkina.restaurant_competition.util;

import com.meshalkina.restaurant_competition.dto.RestaurantDTO;
import com.meshalkina.restaurant_competition.model.Meal;
import com.meshalkina.restaurant_competition.model.Restaurant;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TestRestaurantAndMealUtil {

    private static Restaurant restaurant1 = new Restaurant(1L, "Panda", new ArrayList<>());
    private static Restaurant restaurant2 = new Restaurant(2L, "Burger King", new ArrayList<>());
    private static Restaurant restaurant3 = new Restaurant(3L, "Donuts", new ArrayList<>());
    public static Restaurant restaurantForCreate = new Restaurant(4L, "At grandma's", new ArrayList<>());
    public static Restaurant restaurantForUpdate = new Restaurant(3L, "AWarmPlace", new ArrayList<>());


    private static final Meal meal1 = new Meal(1L, "Chicken with oranges", 700);
    private static final Meal meal2 = new Meal(2L, "Green tea", 150);
    private static final Meal meal3 = new Meal(3L, "Burger", 250);
    private static final Meal meal4 = new Meal(4L, "French fries", 150);
    private static final Meal meal5 = new Meal(5L, "Coffee", 70);
    private static final Meal meal6 = new Meal(6L, "Donut", 120);
    public static final Meal mealForCreate = new Meal(7L, "Pies", 120);
    public static final Meal mealForUpdate = new Meal(4L, "Coca Cola", 150);

    public static List<Meal> getFirstMenu() {
        List<Meal> menu = new ArrayList<>();
        menu.add(meal1);
        menu.add(meal2);
        return menu;
    }

    public static List<Meal> getSecondMenu() {
        List<Meal> menu = new ArrayList<>();
        menu.add(meal3);
        menu.add(meal4);
        return menu;
    }

    public static List<Meal> getThirdMenu() {
        List<Meal> menu = new ArrayList<>();
        menu.add(meal5);
        menu.add(meal6);
        return menu;
    }

    public static List<RestaurantDTO> getListRestaurantDTO() {
        restaurant1.setMenu(getFirstMenu());
        restaurant2.setMenu(getSecondMenu());
        restaurant3.setMenu(getThirdMenu());

        List<Restaurant> restaurantList = new ArrayList<>();
        restaurantList.add(restaurant1);
        restaurantList.add(restaurant2);
        restaurantList.add(restaurant3);

        List<RestaurantDTO> allRestaurantsDTO = restaurantList
                .stream()
                .map(RestaurantDTO::fromRestaurant)
                .collect(Collectors.toList());


        return allRestaurantsDTO;
    }
}
