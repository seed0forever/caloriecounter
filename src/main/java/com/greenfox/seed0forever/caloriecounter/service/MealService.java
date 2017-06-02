package com.greenfox.seed0forever.caloriecounter.service;

import com.greenfox.seed0forever.caloriecounter.model.Meal;
import com.greenfox.seed0forever.caloriecounter.repository.MealRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MealService {

  public static final List<String> MEAL_TYPES =
          new ArrayList<>(Arrays.asList(
                  "Breakfast", "Elevenses", "Lunch", "Snack", "Dinner", "Midnight Snack"));

  private final MealRepository mealRepository;

  @Autowired
  public MealService(
          MealRepository mealRepository) {
    this.mealRepository = mealRepository;
  }

  public void save(Meal meal) {
    mealRepository.save(meal);
  }

  public List<Meal> listAllMeals() {
    return mealRepository.findAll();
  }

  private long calculateTotalCalories() {
    List<Meal> allMeals = mealRepository.findAll();
    long totalCalories = 0L;

    for (Meal meal : allMeals) {
      long calories =
              (meal.getCalories() != null)
                      ? meal.getCalories().longValue()
                      : 0L;
      totalCalories += calories;
    }

    return totalCalories;
  }
}
