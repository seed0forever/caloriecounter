package com.greenfox.seed0forever.caloriecounter.controller;

import com.greenfox.seed0forever.caloriecounter.model.Meal;
import com.greenfox.seed0forever.caloriecounter.service.MealService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

  @PostMapping(value = "/meal")
  public ResponseEntity<?> receiveNewMeal(@Valid @RequestBody Meal meal,
          BindingResult bindingResult) {
    Map<String, String> responseMessage = new HashMap<>();

    if (bindingResult.hasErrors()) {
      responseMessage.put("status", "error");

      String errorMessage = "validation error with field(s): ";

      for (FieldError fieldError : bindingResult.getFieldErrors()) {
        errorMessage += fieldError.getField() + ", ";
      }
      errorMessage = errorMessage.substring(0, errorMessage.length() - 2);

      responseMessage.put("message", errorMessage);
      return new ResponseEntity<>(responseMessage, HttpStatus.BAD_REQUEST);
    }

    mealService.save(meal);
    responseMessage.put("status", "ok");
    return new ResponseEntity<>(responseMessage, HttpStatus.BAD_REQUEST);
  }

}
