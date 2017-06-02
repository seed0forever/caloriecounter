package com.greenfox.seed0forever.caloriecounter.controller;

import com.greenfox.seed0forever.caloriecounter.model.Meal;
import com.greenfox.seed0forever.caloriecounter.service.MealService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    model.addAttribute("totalCalories", mealService.calculateTotalCalories(allMeals));
    return "index";
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
  public String addMeal(
          Model model,
          @Valid Meal meal,
          BindingResult bindingResult) {

    if (bindingResult.hasErrors()) {
      model.addAttribute("mealTypes", MealService.MEAL_TYPES);
      return "add-or-edit";
    }

    mealService.save(meal);
    return "redirect:/";
  }

}
