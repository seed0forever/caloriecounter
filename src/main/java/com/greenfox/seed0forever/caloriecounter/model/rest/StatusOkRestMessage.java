package com.greenfox.seed0forever.caloriecounter.model.rest;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class StatusOkRestMessage implements RestMessage {

  private final String status;

  public StatusOkRestMessage() {
    this.status = "ok";
  }

}
