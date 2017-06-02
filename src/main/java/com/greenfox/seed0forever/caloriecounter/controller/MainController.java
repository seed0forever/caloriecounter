package com.greenfox.seed0forever.caloriecounter.controller;

import com.greenfox.seed0forever.caloriecounter.model.Meal;
import com.greenfox.seed0forever.caloriecounter.service.MealService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

  private final MealService mealService;

  @Autowired
  public MainController(MealService mealService) {
    this.mealService = mealService;
  }

  @GetMapping("/")
  public String showMainPage(Model model) {
    List<Meal> allMeals = mealService.listAllMeals();

    model.addAttribute("allMeals", allMeals);
    model.addAttribute("totalCalories", calculateTotalCalories(allMeals));
    return "index";
  }

  private long calculateTotalCalories(List<Meal> allMeals) {
    long sumCalories = allMeals.stream().mapToLong(meal -> meal.getCalories().longValue()).sum();
    return sumCalories;
  }

  @GetMapping("/add")
  public String showAddMealPage(
          Meal meal,
          Model model) {
    model.addAttribute("meal", meal);
    model.addAttribute("mealTypes", MealService.MEAL_TYPES);
    return "add-or-edit";
  }

  @PostMapping("/add")
  public String addMeal(Meal meal) {
    mealService.save(meal);
    return "redirect:/";
  }

}
