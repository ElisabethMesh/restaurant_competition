package com.meshalkina.restaurant_competition.rest;

import com.meshalkina.restaurant_competition.dto.MealDTO;
import com.meshalkina.restaurant_competition.model.Meal;
import com.meshalkina.restaurant_competition.service.MealService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/meals")
@Api("Controller for menu")
public class MealRestController {

    private final MealService mealService;

    public MealRestController(MealService mealService) {
        this.mealService = mealService;
    }

    @ApiOperation("Create new meal")
    @PostMapping("/{restaurant_id}")
    @PreAuthorize("hasAuthority('users:write')")
    public ResponseEntity<MealDTO> createMeal(@RequestBody Meal meal, @PathVariable Long restaurant_id) {
        Meal newMeal = mealService.createMeal(meal, restaurant_id);
        if (newMeal == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        MealDTO mealDTO = MealDTO.fromMeal(newMeal);
        return new ResponseEntity<>(mealDTO, HttpStatus.OK);
    }

    @ApiOperation("Get a meal by id")
    @GetMapping("/{meal_id}")
    @PreAuthorize("hasAuthority('users:read')")
    public ResponseEntity<MealDTO> getMealById(@PathVariable Long meal_id) {
        Meal meal = mealService.getByIdMeal(meal_id);
        if (meal != null) {
            MealDTO mealDTO = MealDTO.fromMeal(meal);
            return new ResponseEntity<>(mealDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ApiOperation("Update the meal")
    @PutMapping
    @PreAuthorize("hasAuthority('users:write')")
    public ResponseEntity<MealDTO> updateMeal(@RequestBody Meal meal) {
        Meal updateMeal = mealService.updateMeal(meal);
        if (updateMeal == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        MealDTO mealDTO = MealDTO.fromMeal(updateMeal);
        return new ResponseEntity<>(mealDTO, HttpStatus.OK);
    }

    @ApiOperation("Delete the meal")
    @DeleteMapping("/{meal_id}")
    @PreAuthorize("hasAuthority('users:write')")
    public void deleteMeal(@PathVariable Long meal_id) {
        mealService.deleteMeal(meal_id);
    }
}
