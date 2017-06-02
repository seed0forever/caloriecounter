package com.greenfox.seed0forever.caloriecounter.controller;

import com.greenfox.seed0forever.caloriecounter.model.Meal;
import com.greenfox.seed0forever.caloriecounter.service.MealService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestApiController {

  private final MealService mealService;

  @Autowired
  public RestApiController(MealService mealService) {
    this.mealService = mealService;
  }

  @GetMapping(value = "/getmeals", produces = "application/json")
  public List<Meal> respondWithAllMeals() {
    return mealService.listAllMeals();
  }

  @GetMapping(value = "/getstats", produces = "application/json")
  public Map<String, Long> respondWithMealStats() {
    Map<String, Long> mealStats = new HashMap<>();
    List<Meal> allMeals = mealService.listAllMeals();

    mealStats.put("number of meals", (long) allMeals.size());
    mealStats.put("total calories", mealService.calculateTotalCalories(allMeals));

    return mealStats;
  }

}
