package com.greenfox.seed0forever.caloriecounter.model.rest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class MealStatsRestMessage implements RestMessage {

  @JsonProperty("number of meals")
  private long numberOfMeals;
  @JsonProperty("total calories")
  private long totalCalories;

}
