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

  public static final String ERROR_ID_NOT_EXISTS = "cannot update: received ID does not exist in database";

  private final MealRepository mealRepository;

  @Autowired
  public MealService(
          MealRepository mealRepository) {
    this.mealRepository = mealRepository;
  }

  public List<Meal> listAllMeals() {
    return mealRepository.findAll();
  }

  public boolean existsById(Meal meal) {
    if (meal == null) {
      return false;
    }
    return mealRepository.exists(meal.getId());
  }

  public long calculateTotalCalories() {
    List<Meal> allMeals = mealRepository.findAll();
    return calculateTotalCalories(allMeals);
  }

  public long calculateTotalCalories(List<Meal> allMeals) {
    if (allMeals == null || allMeals.size() == 0) {
      return 0L;
    }

    long sumCalories = 0L;
    for (Meal meal : allMeals) {
      long calories =
              (meal.getCalories() != null)
                      ? meal.getCalories().longValue()
                      : 0L;
      sumCalories += calories;
    }
    return sumCalories;
  }

  public void save(Meal meal) {
    mealRepository.save(meal);
  }

  public void saveAsNewEntity(Meal meal) {
    Meal mealToSave = new Meal();

    mealToSave.setDate(meal.getDate());
    mealToSave.setType(meal.getType());
    mealToSave.setDescription(meal.getDescription());
    mealToSave.setCalories(meal.getCalories());

    mealRepository.save(mealToSave);
  }

}
