package com.greenfox.seed0forever.caloriecounter.model.rest.status;

import com.greenfox.seed0forever.caloriecounter.model.rest.RestMessage;
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
