package com.greenfox.seed0forever.caloriecounter.model.rest;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class StatusOkRestMessage implements RestMessage {

  private final String status;

  public StatusOkRestMessage() {
    this.status = "ok";
  }

}
