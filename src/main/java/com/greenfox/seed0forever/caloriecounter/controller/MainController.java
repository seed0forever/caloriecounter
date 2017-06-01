package com.greenfox.seed0forever.caloriecounter.controller;

import com.greenfox.seed0forever.caloriecounter.model.Meal;
import com.greenfox.seed0forever.caloriecounter.service.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

  private final MealService mealService;

  @Autowired
  public MainController(MealService mealService) {
    this.mealService = mealService;
  }

  @GetMapping("/")
  public String showMainPage() {
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
  public String addMeal(Meal meal) {
    mealService.save(meal);
    return "redirect:/";
  }

}
