package com.greenfox.seed0forever.caloriecounter.controller;

import com.greenfox.seed0forever.caloriecounter.model.Meal;
import com.greenfox.seed0forever.caloriecounter.model.rest.RestMessage;
import com.greenfox.seed0forever.caloriecounter.model.rest.StatusOkRestMessage;
import com.greenfox.seed0forever.caloriecounter.service.MealService;
import com.greenfox.seed0forever.caloriecounter.service.ValidationResponseService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestApiController {

  private final MealService mealService;
  private final StatusOkRestMessage okMessage;
  private final ValidationResponseService validationService;

  @Autowired
  public RestApiController(
          MealService mealService,
          StatusOkRestMessage okMessage,
          ValidationResponseService validationService) {
    this.mealService = mealService;
    this.okMessage = okMessage;
    this.validationService = validationService;
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
  public ResponseEntity<RestMessage> receiveNewMeal(
          @Valid @RequestBody Meal meal,
          BindingResult bindingResult) {

    if (bindingResult.hasErrors()) {
      RestMessage errorMessage =
              validationService.createErrorMessage(bindingResult);

      return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    mealService.saveAsNewEntity(meal);
    return new ResponseEntity<>(okMessage, HttpStatus.OK);
  }

}
